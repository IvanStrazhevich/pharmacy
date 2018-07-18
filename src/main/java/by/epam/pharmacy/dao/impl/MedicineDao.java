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
import java.util.List;

/**
 * Implementation of AbstractDao for type Medicine
 */
public class MedicineDao extends AbstractDaoImpl<Medicine> implements AbstractMedicineDao<Medicine>{
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_PSTM = "select  mdc_id, mdc_name, mdc_description, mdc_dosage, mdc_recipe_required, mdc_price, mdc_available from medicine";
    private static final String SELECT_BY_ID_PSTM = "select mdc_id, mdc_name, mdc_description, mdc_dosage, mdc_recipe_required, mdc_price, mdc_available from medicine where mdc_id = ?";
    private static final String SELECT_BY_NAME_PSTM = "select mdc_id, mdc_name, mdc_description, mdc_dosage, mdc_recipe_required, mdc_price, mdc_available from medicine where mdc_name = ?";
    private static final String INSERT_PSTM = "insert into medicine( mdc_name, mdc_description, mdc_dosage, mdc_recipe_required, mdc_price, mdc_available) values(?,?,?,?,?,?,?)";
    private static final String DELETE_PSTM = "delete from medicine where mdc_id = ?";
    private static final String UPDATE_PSTM = "update medicine set mdc_name=?, mdc_description=?, mdc_dosage=?, mdc_recipe_required=?, mdc_price=?, mdc_available=? where mdc_id = ?";
    private ProxyConnection proxyConnection;

    public MedicineDao() throws DaoException {
        proxyConnection = super.proxyConnection;
    }

    /**
     * @return
     * @throws DaoException
     */
    @Override
    public List<Medicine> findAll() throws DaoException {
        ArrayList<Medicine> medicineList = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_ALL_PSTM)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Medicine medicine = new Medicine();
                medicine.setMedicineId(resultSet.getInt(1));
                medicine.setMedicineName(resultSet.getString(2));
                medicine.setDescription(resultSet.getString(3));
                medicine.setDosage(resultSet.getBigDecimal(4));
                medicine.setRecipeRequired(resultSet.getBoolean(5));
                medicine.setPrice(resultSet.getBigDecimal(6));
                medicine.setAvailable(resultSet.getBoolean(7));
                medicineList.add(medicine);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception on find all", e);
        }
        return medicineList;
    }

    /**
     * @param name
     * @return
     * @throws DaoException
     */
    @Override
    public Medicine findMedicineByName(String name) throws DaoException {
        Medicine medicine = new Medicine();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_NAME_PSTM)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
            medicine.setMedicineId(resultSet.getInt(1));
            medicine.setMedicineName(resultSet.getString(2));
            medicine.setDescription(resultSet.getString(3));
            medicine.setDosage(resultSet.getBigDecimal(4));
            medicine.setRecipeRequired(resultSet.getBoolean(5));
            medicine.setPrice(resultSet.getBigDecimal(6));
            medicine.setAvailable(resultSet.getBoolean(7));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception on find bu id", e);
        }
        return medicine;
    }

    /**
     * @param id
     * @return
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
        } catch (SQLException e) {
            throw new DaoException("Exception on find bu id", e);
        }
        return medicine;
    }

    /**
     * @param id
     * @return
     * @throws DaoException
     */
    @Override
    public boolean deleteById(Integer id) throws DaoException {
        return deleteById(id, DELETE_PSTM);
    }

    /**
     * @param entity
     * @return
     * @throws DaoException
     */
    @Override
    public boolean delete(Medicine entity) throws DaoException {
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_PSTM)) {
            preparedStatement.setInt(1, entity.getMedicineId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception on deleteById", e);
        }
    }

    /**
     * @param entity
     * @return
     * @throws DaoException
     */
    @Override
    public boolean create(Medicine entity) throws DaoException {
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(INSERT_PSTM)) {
            preparedStatement.setString(2, entity.getMedicineName());
            preparedStatement.setString(3, entity.getDescription());
            preparedStatement.setBigDecimal(4, entity.getDosage());
            preparedStatement.setBoolean(5, entity.isRecipeRequired());
            preparedStatement.setBigDecimal(6, entity.getPrice());
            preparedStatement.setBoolean(7, entity.isAvailable());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception on create", e);
        }
    }

    /**
     * @param entity
     * @return
     * @throws DaoException
     */
    @Override
    public boolean update(Medicine entity) throws DaoException {
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_PSTM)) {
            preparedStatement.setString(1, entity.getMedicineName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setBigDecimal(3, entity.getDosage());
            preparedStatement.setBoolean(4, entity.isRecipeRequired());
            preparedStatement.setBigDecimal(5, entity.getPrice());
            preparedStatement.setBoolean(6, entity.isAvailable());
            preparedStatement.setInt(1, entity.getMedicineId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception on update", e);
        }
    }


}
