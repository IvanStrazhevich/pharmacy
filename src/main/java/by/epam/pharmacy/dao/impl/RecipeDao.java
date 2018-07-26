package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.dao.AbstractRecipeDao;
import by.epam.pharmacy.entity.ClientDetail;
import by.epam.pharmacy.entity.Medicine;
import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeDao extends AbstractDaoImpl<Recipe> implements AbstractRecipeDao<Recipe>{
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_PSTM = "select rec_id, rec_doctor_user_id, rec_medicine_mdc_id, rec_client_user_id, rec_meds_quantity, rec_dosage, rec_date_valid_till, res_approved from recipe";
    private static final String SELECT_ALL_WITH_DETAILS_PSTM = "select r.rec_id, r.rec_doctor_user_id, r.rec_medicine_mdc_id, r.rec_client_user_id, r.rec_meds_quantity, r.rec_dosage, r.rec_date_valid_till, r.res_approved, cd.cl_name, cd.cl_lastname, m.mdc_name from recipe as r LEFT JOIN medicine as m on r.rec_medicine_mdc_id=m.mdc_id LEFT JOIN client_detail cd ON r.rec_client_user_id = cd.user_id";
    private static final String SELECT_BY_ID_WITH_DETAILS_PSTM = "select r.rec_id, r.rec_doctor_user_id, r.rec_medicine_mdc_id, r.rec_client_user_id, r.rec_meds_quantity, r.rec_dosage, r.rec_date_valid_till, r.res_approved, cd.cl_name, cd.cl_lastname, m.mdc_name from recipe as r LEFT JOIN medicine as m on r.rec_medicine_mdc_id=m.mdc_id LEFT JOIN client_detail cd ON r.rec_client_user_id = cd.user_id WHERE r.rec_id = ?";

    private static final String SELECT_BY_ID_PSTM = "select rec_id, rec_doctor_user_id, rec_medicine_mdc_id, rec_client_user_id, rec_meds_quantity, rec_dosage, rec_date_valid_till, res_approved  from recipe where rec_id = ?";
    private static final String SELECT_BY_CLIENT_MED_QUANT_PSTM = "select rec_id, rec_doctor_user_id, rec_medicine_mdc_id, rec_client_user_id, rec_meds_quantity, rec_dosage, rec_date_valid_till, res_approved  from recipe where rec_client_user_id=? and rec_medicine_mdc_id = ? and rec_meds_quantity=?";
    private static final String INSERT_PSTM = "insert into recipe(rec_doctor_user_id, rec_medicine_mdc_id, rec_client_user_id, rec_meds_quantity, rec_dosage, rec_date_valid_till, res_approved ) values(?,?,?,?,?,?,?)";
    private static final String DELETE_PSTM = "delete from recipe where rec_id = ?";
    private static final String UPDATE_PSTM = "update recipe set rec_doctor_user_id=?, rec_medicine_mdc_id=?, rec_client_user_id=?, rec_meds_quantity=?, rec_dosage=?, rec_date_valid_till=?, res_approved=?  where rec_id = ?";
    private ProxyConnection proxyConnection;


    public RecipeDao() throws DaoException {
        proxyConnection = super.proxyConnection;
    }
    /**
     * Finds Users all existed
     *
     * @return ArrayList<Recipe>
     * @throws DaoException
     */
    @Override
    public ArrayList<Recipe> findAll() throws DaoException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_ALL_PSTM)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setRecipeId(resultSet.getInt(1));
                recipe.setDoctorId(resultSet.getInt(2));
                recipe.setMedicineId(resultSet.getInt(3));
                recipe.setClientId(resultSet.getInt(4));
                recipe.setMedicineQuantity(resultSet.getInt(5));
                recipe.setDosage(resultSet.getBigDecimal(6));
                recipe.setValidTill(resultSet.getTimestamp(7));
                recipe.setApproved(resultSet.getBoolean(8));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception on recipe find all", e);
        }
        return recipes;
    }

    @Override
    public ArrayList<Recipe> findAllWithDetails() throws DaoException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_ALL_WITH_DETAILS_PSTM)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                ClientDetail clientDetail = new ClientDetail();
                Medicine medicine = new Medicine();
                recipe.setRecipeId(resultSet.getInt(1));
                recipe.setDoctorId(resultSet.getInt(2));
                recipe.setMedicineId(resultSet.getInt(3));
                recipe.setClientId(resultSet.getInt(4));
                recipe.setMedicineQuantity(resultSet.getInt(5));
                recipe.setDosage(resultSet.getBigDecimal(6));
                recipe.setValidTill(resultSet.getTimestamp(7));
                recipe.setApproved(resultSet.getBoolean(8));
                clientDetail.setName(resultSet.getString(9));
                clientDetail.setLastname(resultSet.getString(10));
                medicine.setMedicineName(resultSet.getString(11));
                recipe.setClientDetail(clientDetail);
                recipe.setMedicine(medicine);
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception on recipe find all with details", e);
        }
        return recipes;

    }


    /**
     * Finds User by its id
     *
     * @param id type Integer
     * @return Recipe
     * @throws DaoException
     */
    @Override
    public Recipe findEntityById(Integer id) throws DaoException {
        Recipe recipe = new Recipe();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ID_PSTM)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            fillRecipe(recipe, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exception on Recipe find by id", e);
        }
        return recipe;
    }

    @Override
    public Recipe findEntityByIdWithDetails(Integer recipeId) throws DaoException {
        Recipe recipe = new Recipe();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ID_WITH_DETAILS_PSTM)) {
            preparedStatement.setInt(1, recipeId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                ClientDetail clientDetail = new ClientDetail();
                Medicine medicine = new Medicine();
                recipe.setRecipeId(resultSet.getInt(1));
                recipe.setDoctorId(resultSet.getInt(2));
                recipe.setMedicineId(resultSet.getInt(3));
                recipe.setClientId(resultSet.getInt(4));
                recipe.setMedicineQuantity(resultSet.getInt(5));
                recipe.setDosage(resultSet.getBigDecimal(6));
                recipe.setValidTill(resultSet.getTimestamp(7));
                recipe.setApproved(resultSet.getBoolean(8));
                clientDetail.setName(resultSet.getString(9));
                clientDetail.setLastname(resultSet.getString(10));
                medicine.setMedicineName(resultSet.getString(11));
                recipe.setClientDetail(clientDetail);
                recipe.setMedicine(medicine);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception on recipe find all by id with details", e);
        }
        return recipe;

    }

    /**
     * @param id of type Integer
     * @return true if statement delete item successfully
     * @throws DaoException
     */
    @Override
    public boolean deleteById(Integer id) throws DaoException {
        return deleteById(id, DELETE_PSTM);
    }

    /**
     * @param entity of type Recipe
     * @return true if statement delete item successfully
     * @throws DaoException
     */
    @Override
    public boolean delete(Recipe entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_PSTM)) {
            preparedStatement.setInt(1, entity.getRecipeId());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on Recipe deleteById", e);
        }
        return success;
    }

    /**
     * @param entity of type Recipe
     * @return true if statement create item successfully
     * @throws DaoException
     */
    @Override
    public boolean create(Recipe entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(INSERT_PSTM)) {
            preparedStatement.setInt(1, entity.getDoctorId());
            preparedStatement.setInt(2, entity.getMedicineId());
            preparedStatement.setInt(3, entity.getClientId());
            preparedStatement.setInt(4, entity.getMedicineQuantity());
            preparedStatement.setBigDecimal(5,entity.getDosage());
            preparedStatement.setTimestamp(6, entity.getValidTill());
            preparedStatement.setBoolean(7, entity.isApproved());
            logger.info(preparedStatement);
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on Recipe create", e);
        }
        return success;
    }

    /**
     * @param entity of type Recipe
     * @return true if statement update item successfully
     * @throws DaoException
     */
    @Override
    public boolean update(Recipe entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_PSTM)) {
            preparedStatement.setInt(1, entity.getDoctorId());
            preparedStatement.setInt(2, entity.getMedicineId());
            preparedStatement.setInt(3, entity.getClientId());
            preparedStatement.setInt(4, entity.getMedicineQuantity());
            preparedStatement.setBigDecimal(5,entity.getDosage());
            preparedStatement.setTimestamp(6, entity.getValidTill());
            preparedStatement.setBoolean(7, entity.isApproved());
            preparedStatement.setInt(8,entity.getRecipeId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on Recipe  update", e);
        }
        return success;
    }


    @Override
    public Recipe findRecipeByClientMedicineQuantity(Integer clientId, Integer medicineId, Integer medicineQuantity) throws DaoException {
        Recipe recipe = new Recipe();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_CLIENT_MED_QUANT_PSTM)) {
            preparedStatement.setInt(1, clientId);
            preparedStatement.setInt(2, medicineId);
            preparedStatement.setInt(3, medicineQuantity);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            fillRecipe(recipe, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exception on Recipe find by Client Med Quant", e);
        }
        return recipe;

    }

    private void fillRecipe(Recipe recipe, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            recipe.setRecipeId(resultSet.getInt(1));
            recipe.setDoctorId(resultSet.getInt(2));
            recipe.setMedicineId(resultSet.getInt(3));
            recipe.setClientId(resultSet.getInt(4));
            recipe.setMedicineQuantity(resultSet.getInt(5));
            recipe.setDosage(resultSet.getBigDecimal(6));
            recipe.setValidTill(resultSet.getTimestamp(7));
            recipe.setApproved(resultSet.getBoolean(8));
        }
    }
}
