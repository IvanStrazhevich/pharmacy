package by.epam.pharmacy.service.impl;

import by.epam.pharmacy.command.AttributeEnum;
import by.epam.pharmacy.controller.SessionRequestContent;
import by.epam.pharmacy.dao.impl.OrderDao;
import by.epam.pharmacy.dao.impl.RecipeDao;
import by.epam.pharmacy.entity.OrderHasMedicine;
import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.OrderService;
import by.epam.pharmacy.service.RecipeService;
import by.epam.pharmacy.service.UserService;
import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

public class RecipeServiceImpl implements RecipeService {
    private static final String MESSAGE = "message.recipeRequested";
    private static Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    @Override
    public void createRecipe(SessionRequestContent sessionRequestContent) throws ServiceException {
        int orderId = Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.ORDER_ID.getAttribute()));
        int medicineId = Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_ID.getAttribute()));
        int medicineQuantity = Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_QUANTITY.getAttribute()));
        BigDecimal dosage = new BigDecimal(sessionRequestContent.getRequestParameters().get(AttributeEnum.DOSAGE.getAttribute()));
        int clientId = findUserId(orderId);
        Recipe recipe = new Recipe();
        recipe.setMedicineId(medicineId);
        recipe.setMedicineQuantity(medicineQuantity);
        recipe.setDosage(dosage);
        recipe.setClientId(clientId);
        recipe.setDoctorId(userService.findDefaultDoctor().getUserId());
        logger.info(recipe);
        createOrUpdateRecipe(recipe);
        sessionRequestContent.getRequestAttributes().put(AttributeEnum.RECIPE_REQUESTED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE));
    }

    public void showRecipes(SessionRequestContent sessionRequestContent) throws ServiceException {
        try (RecipeDao recipeDao = new RecipeDao()) {
            ArrayList<Recipe> recipes = recipeDao.findAllWithDetails();
            logger.info(recipes);
            sessionRequestContent.getRequestAttributes().put(AttributeEnum.RECIPES.getAttribute(), recipes);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void showRecipe(SessionRequestContent sessionRequestContent) throws ServiceException {
        int recipeId = Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.RECIPE_ID.getAttribute()));
        try (RecipeDao recipeDao = new RecipeDao()) {
            Recipe recipe = recipeDao.findEntityByIdWithDetails(recipeId);
            logger.info(recipe);
            sessionRequestContent.getRequestAttributes().put(AttributeEnum.RECIPE.getAttribute(), recipe);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void approveRecipe(SessionRequestContent sessionRequestContent) throws ServiceException {
        int recipeId = Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.RECIPE_ID.getAttribute()));
        int medicineQuantity = Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_QUANTITY.getAttribute()));
        Timestamp validTill = Timestamp.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.VALID_TILL.getAttribute()));
        logger.info(validTill);
        boolean approved = Boolean.parseBoolean(sessionRequestContent.getRequestParameters().get(AttributeEnum.APPROVED.getAttribute()));
        try (RecipeDao recipeDao = new RecipeDao()) {
            Recipe recipeDB = recipeDao.findEntityById(recipeId);
            recipeDB.setMedicineQuantity(medicineQuantity);
            recipeDB.setValidTill(validTill);
            recipeDB.setApproved(approved);
            recipeDao.update(recipeDB);
            int medicineId = recipeDB.getMedicineId();
            int clientId = recipeDB.getClientId();
            int orderId = orderService.findCurrentOrderIdByUserId(clientId);
            OrderHasMedicine orderHasMedicine = orderService.findOrderHasMedicine(orderId, medicineId);
            orderHasMedicine.setRecipeId(recipeDB.getRecipeId());
            orderHasMedicine.setMedicineQuantity(recipeDB.getMedicineQuantity());
            orderService.updateRecipeAtOrderHasMedicine(orderHasMedicine);
            orderService.changeQuantityFromRecipe(orderId,medicineId,medicineQuantity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void createOrUpdateRecipe(Recipe recipe) throws ServiceException {
        int clientId = recipe.getClientId();
        int medicineId = recipe.getMedicineId();
        int quantity = recipe.getMedicineQuantity();
        try (RecipeDao recipeDao = new RecipeDao()) {
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

    private int findUserId(Integer orderId) throws ServiceException {
        try (OrderDao orderDao = new OrderDao()) {
            return orderDao.findEntityById(orderId).getClientId();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
