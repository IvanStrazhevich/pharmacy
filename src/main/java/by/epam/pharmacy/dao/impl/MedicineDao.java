package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.dao.AbstractMedicineDao;
import by.epam.pharmacy.entity.Medicine;
import by.epam.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Medicine implementations for AbstractDao and AbstractMedicineDao
 */
public class MedicineDao extends AbstractDaoImpl<Medicine> implements AbstractMedicineDao<Medicine> {
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_PSTM = "select  mdc_id, mdc_name, mdc_description, mdc_dosage, mdc_recipe_required, mdc_price, mdc_available, mdc_quantity from medicine";
    private static final String SELECT_BY_ID_PSTM = "select mdc_id, mdc_name, mdc_description, mdc_dosage, mdc_recipe_required, mdc_price, mdc_available, mdc_quantity from medicine where mdc_id = ?";
    private static final String SELECT_BY_NAME_PSTM = "select mdc_id, mdc_name, mdc_description, mdc_dosage, mdc_recipe_required, mdc_price, mdc_available, mdc_quantity from medicine where mdc_name = ?";
    private static final String INSERT_PSTM = "insert into medicine( mdc_name, mdc_description, mdc_dosage, mdc_recipe_required, mdc_price, mdc_available, mdc_quantity) values(?,?,?,?,?,?,?)";
    private static final String UPDATE_MEDICINE_SET_MDC_AVAILABLE = "update medicine set mdc_available=?where mdc_id = ?";
    private static final String UPDATE_PSTM = "update medicine set mdc_name=?, mdc_description=?, mdc_dosage=?, mdc_recipe_required=?, mdc_price=?, mdc_available=?, mdc_quantity=? where mdc_id = ?";
    private static final String DELETE_PSTM = "delete FROM medicine where mdc_id=?";
    private ProxyConnection proxyConnection;

    public MedicineDao() throws DaoException {
        proxyConnection = super.proxyConnection;
    }

    /**
     * Finds all Medicines in database
     *
     * @return Arraylist of Medicines
     * @throws DaoException
     */
    @Override
    public ArrayList<Medicine> findAll() throws DaoException {
        ArrayList<Medicine> medicineList = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_ALL_PSTM)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            fillMedicine(medicineList, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exception on find all", e);
        }
        return medicineList;
    }

    /**
     * Finds all Medicines in database with exact name
     *
     * @param name of type String
     * @return Arraylist of Medicines
     * @throws DaoException
     */
    @Override
    public ArrayList<Medicine> findMedicineByName(String name) throws DaoException {
        ArrayList<Medicine> medicineList = new ArrayList<>();

        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_NAME_PSTM)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            fillMedicine(medicineList, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exception on find bu id", e);
        }
        return medicineList;
    }

    private void fillMedicine(ArrayList<Medicine> medicineList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Medicine medicine = new Medicine();
            medicine.setMedicineId(resultSet.getInt(1));
            medicine.setMedicineName(resultSet.getString(2));
            medicine.setDescription(resultSet.getString(3));
            medicine.setDosage(resultSet.getBigDecimal(4));
            medicine.setRecipeRequired(resultSet.getBoolean(5));
            medicine.setPrice(resultSet.getBigDecimal(6));
            medicine.setAvailable(resultSet.getBoolean(7));
            medicine.setQuantityAtStorage(resultSet.getInt(8));
            medicineList.add(medicine);
        }
    }

    /**
     * Finds Medicine by given id
     *
     * @param id of type Integer
     * @return Medicine
     * @throws DaoException
     */
    @Override
    public Medicine findEntityById(Integer id) throws DaoException {
        Medicine medicine = new Medicine();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ID_PSTM)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            medicine.setMedicineId(resultSet.getInt(1));
            medicine.setMedicineName(resultSet.getString(2));
            medicine.setDescription(resultSet.getString(3));
            medicine.setDosage(resultSet.getBigDecimal(4));
            medicine.setRecipeRequired(resultSet.getBoolean(5));
            medicine.setPrice(resultSet.getBigDecimal(6));
            medicine.setAvailable(resultSet.getBoolean(7));
            medicine.setQuantityAtStorage(resultSet.getInt(8));
        } catch (SQLException e) {
            throw new DaoException("Exception on find Medicine by id", e);
        }
        return medicine;
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        return deleteById(id,DELETE_PSTM);
    }

    @Override
    public boolean delete(Medicine entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_PSTM)) {
            preparedStatement.setInt(1, entity.getMedicineId());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on delete entity", e);
        }
        return success;
    }

    /**
     * Set available field to false at medicine with exact id
     *
     * @param id
     * @return true if updated
     * @throws DaoException
     */
    @Override
    public boolean setUnavailableById(Integer id) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_MEDICINE_SET_MDC_AVAILABLE)) {
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DaoException( "Exception on set unavailable By Id", e);
        }
        return success;
    }

    /**
     * Set available field to false at exact medicine
     *
     * @param entity
     * @return true if updated
     * @throws DaoException
     */
    @Override
    public boolean setUnavailableByName(Medicine entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_MEDICINE_SET_MDC_AVAILABLE)) {
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, entity.getMedicineId());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on set unavailable By Name", e);
        }
        return success;
    }

    /**
     * Method inserts  medicine into database
     *
     * @param entity
     * @return true if successfully inserted
     * @throws DaoException
     */
    @Override
    public boolean create(Medicine entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(INSERT_PSTM)) {
            preparedStatement.setString(1, entity.getMedicineName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setBigDecimal(3, entity.getDosage());
            preparedStatement.setBoolean(4, entity.isRecipeRequired());
            preparedStatement.setBigDecimal(5, entity.getPrice());
            preparedStatement.setBoolean(6, entity.isAvailable());
            preparedStatement.setInt(7, entity.getQuantityAtStorage());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on create medicine", e);
        }
        return success;
    }

    /**
     * @param entity
     * @return
     * @throws DaoException
     */
    @Override
    public boolean update(Medicine entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_PSTM)) {
            preparedStatement.setString(1, entity.getMedicineName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setBigDecimal(3, entity.getDosage());
            preparedStatement.setBoolean(4, entity.isRecipeRequired());
            preparedStatement.setBigDecimal(5, entity.getPrice());
            preparedStatement.setBoolean(6, entity.isAvailable());
            preparedStatement.setInt(7, entity.getQuantityAtStorage());
            preparedStatement.setInt(8, entity.getMedicineId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on update", e);
        }
        return success;
    }


}
