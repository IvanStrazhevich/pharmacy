package by.epam.pharmacy.service.impl;

import by.epam.pharmacy.command.AttributeName;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.dao.impl.OrderDaoImpl;
import by.epam.pharmacy.dao.impl.RecipeDaoImpl;
import by.epam.pharmacy.entity.OrderHasMedicine;
import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.OrderService;
import by.epam.pharmacy.service.RecipeService;
import by.epam.pharmacy.service.UserService;
import by.epam.pharmacy.util.InputValidator;
import by.epam.pharmacy.util.InputValidatorImpl;
import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 */
public class RecipeServiceImpl implements RecipeService {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.recipeRequested";
    private static final String MESSAGE_DELETED = "message.recipeDeleted";
    private static final String MESSAGE_VALIDATION = "message.validationError";
    private UserService userService = new UserServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private InputValidator validator = new InputValidatorImpl();

    @Override
    public boolean validateForCreateRecipe(SessionRequestContent content) throws ServiceException {
        boolean validated = false;
        int orderId = 0;
        if (validator.validateInteger(content.getRequestParameters().get(AttributeName.ORDER_ID.getAttribute()))
                && validator.validateInteger(content.getRequestParameters().get(AttributeName.MEDICINE_ID.getAttribute()))
                && validator.validateInteger(content.getRequestParameters().get(AttributeName.MEDICINE_QUANTITY.getAttribute()))
                && validator.validateDecimal(content.getRequestParameters().get(AttributeName.DOSAGE.getAttribute()))) {
            validated = true;
        } else {
            content.getRequestAttributes().put(AttributeName.ORDER_ID.getAttribute(), orderId);
            content.getRequestAttributes().put(AttributeName.VALIDATION_ERROR.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_VALIDATION));
        }
        return validated;
    }

    /**
     * @param content
     */
    @Override
    public void createRecipe(SessionRequestContent content) throws ServiceException {
        int orderId = Integer.valueOf(content.getRequestParameters().get(AttributeName.ORDER_ID.getAttribute()));
        int medicineId = Integer.valueOf(content.getRequestParameters().get(AttributeName.MEDICINE_ID.getAttribute()));
        int medicineQuantity = Integer.valueOf(content.getRequestParameters().get(AttributeName.MEDICINE_QUANTITY.getAttribute()));
        BigDecimal dosage = new BigDecimal(content.getRequestParameters().get(AttributeName.DOSAGE.getAttribute()));
        int clientId = findUserId(orderId);
        Recipe recipe = new Recipe();
        recipe.setMedicineId(medicineId);
        recipe.setMedicineQuantity(medicineQuantity);
        recipe.setDosage(dosage);
        recipe.setClientId(clientId);
        recipe.setDoctorId(userService.findDefaultDoctor().getUserId());
        logger.info(recipe);
        createOrUpdateRecipe(recipe);
        content.getRequestAttributes().put(AttributeName.RECIPE_REQUESTED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE));
    }


    /**
     * @param content
     */
    public void showRecipes(SessionRequestContent content) throws ServiceException {
        try (RecipeDaoImpl recipeDao = new RecipeDaoImpl()) {
            ArrayList<Recipe> recipes = recipeDao.findAllWithDetails();
            logger.info(recipes);
            content.getRequestAttributes().put(AttributeName.RECIPES.getAttribute(), recipes);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param content
     */
    @Override
    public void showRecipe(SessionRequestContent content) throws ServiceException {
        int recipeId = Integer.valueOf(content.getRequestParameters().get(AttributeName.RECIPE_ID.getAttribute()));
        try (RecipeDaoImpl recipeDao = new RecipeDaoImpl()) {
            Recipe recipe = recipeDao.findEntityByIdWithDetails(recipeId);
            logger.info(recipe);
            content.getRequestAttributes().put(AttributeName.RECIPE.getAttribute(), recipe);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean validateForApproveRecipe(SessionRequestContent content) throws ServiceException {
        boolean validated = false;
        int recipeId = 0;
        if (validator.validateInteger(content.getRequestParameters().get(AttributeName.RECIPE_ID.getAttribute()))
                && validator.validateInteger(content.getRequestParameters().get(AttributeName.MEDICINE_QUANTITY.getAttribute()))
                && validator.validateTimeStamp(content.getRequestParameters().get(AttributeName.VALID_TILL.getAttribute()))) {
            validated = true;
        } else {
            content.getRequestAttributes().put(AttributeName.RECIPE_ID.getAttribute(), recipeId);
            content.getRequestAttributes().put(AttributeName.VALIDATION_ERROR.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_VALIDATION));
        }
        return validated;

    }

    /**
     * @param content
     */
    @Override
    public void approveRecipe(SessionRequestContent content) throws ServiceException {
            int recipeId = Integer.valueOf(content.getRequestParameters().get(AttributeName.RECIPE_ID.getAttribute()));
            int medicineQuantity = Integer.valueOf(content.getRequestParameters().get(AttributeName.MEDICINE_QUANTITY.getAttribute()));
            int clientId = Integer.valueOf(content.getRequestParameters().get(AttributeName.USER_ID.getAttribute()));
            Timestamp validTill = Timestamp.valueOf(content.getRequestParameters().get(AttributeName.VALID_TILL.getAttribute()));
            boolean approved = Boolean.parseBoolean(content.getRequestParameters().get(AttributeName.APPROVED.getAttribute()));
            try (RecipeDaoImpl recipeDao = new RecipeDaoImpl()) {
                Recipe recipeDB = recipeDao.findEntityById(recipeId);
                recipeDB.setClientId(clientId);
                recipeDB.setMedicineQuantity(medicineQuantity);
                recipeDB.setValidTill(validTill);
                recipeDB.setApproved(approved);
                recipeDao.update(recipeDB);
                int medicineId = recipeDB.getMedicineId();
                int orderId = orderService.findCurrentOrderIdByUserId(clientId);
                OrderHasMedicine orderHasMedicine = orderService.findOrderHasMedicine(orderId, medicineId);
                orderHasMedicine.setRecipeId(recipeDB.getRecipeId());
                orderHasMedicine.setMedicineQuantity(medicineQuantity);
                orderService.updateRecipeAtOrderHasMedicine(orderHasMedicine);
                orderService.changeQuantityFromRecipe(orderId, medicineId, medicineQuantity);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }

    /**
     * @param content
     */
    @Override
    public void deleteRecipe(SessionRequestContent content) throws ServiceException {
        int recipeId = Integer.valueOf(content.getRequestParameters().get(AttributeName.RECIPE_ID.getAttribute()));
        try (RecipeDaoImpl recipeDao = new RecipeDaoImpl()) {
            recipeDao.deleteById(recipeId);
            content.getRequestAttributes().put(AttributeName.RECIPE_DELETED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_DELETED));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param recipe
     */
    private void createOrUpdateRecipe(Recipe recipe) throws ServiceException {
        int clientId = recipe.getClientId();
        int medicineId = recipe.getMedicineId();
        int quantity = recipe.getMedicineQuantity();
        try (RecipeDaoImpl recipeDao = new RecipeDaoImpl()) {
            Recipe recipeDB = recipeDao.findRecipeByClientMedicineQuantity(clientId, medicineId, quantity);
            if (recipeDB.getRecipeId() == 0) {
                logger.info("creating");
                recipeDao.create(recipe);
                recipe.setRecipeId(recipeDao.findLastInsertId());
            } else {
                logger.info("updating");
                recipe.setRecipeId(recipeDB.getRecipeId());
                recipeDao.update(recipe);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param orderId
     */
    private int findUserId(Integer orderId) throws ServiceException {
        try (OrderDaoImpl orderDao = new OrderDaoImpl()) {
            return orderDao.findEntityById(orderId).getClientId();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param userService
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param orderService
     */
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * @param validator
     */
    public void setValidator(InputValidator validator) {
        this.validator = validator;
    }
}

