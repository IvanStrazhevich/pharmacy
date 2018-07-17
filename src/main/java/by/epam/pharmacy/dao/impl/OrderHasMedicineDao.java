package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.SecureConnection;
import by.epam.pharmacy.dao.AbstractOrderHasMedicineDao;
import by.epam.pharmacy.entity.OrderHasMedicine;
import by.epam.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class OrderHasMedicineDao extends AbstractDaoImpl<OrderHasMedicine> implements AbstractOrderHasMedicineDao<OrderHasMedicine> {
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_PSTM = "select order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id from order_has_medicine";
    private static final String SELECT_BY_ORDER_ID_PSTM = "select order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id from order_has_medicine where order_order_id = ?";
    private static final String INSERT_MEDICINE_IN_ORDER_PSTM = "insert into order_has_medicine(order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id ) values(?,?,?,?,?)";
    private static final String DELETE_ALL_MEDS_FROM_ORDER_PSTM = "delete from order_has_medicine where order_order_id = ?";
    private static final String DELETE_MEDICINE_FROM_ORDER_PSTM = "delete from order_has_medicine where order_order_id = ? and medicine_mdc_id=?";
    private static final String UPDATE_PSTM = "update order set ohm_med_quantity = ?, ohm_med_sum = ?, recipe_rec_id = ? where order_order_id = ? and medicine_mdc_id = ?";
    private SecureConnection secureConnection;

    public OrderHasMedicineDao() throws DaoException {
        secureConnection = super.secureConnection;
    }


    /**
     * @return
     * @throws DaoException
     */
    @Override
    public List<OrderHasMedicine> findAll() throws DaoException {
        ArrayList<OrderHasMedicine> ordersHasMedicinesList = new ArrayList<>();
        try (PreparedStatement preparedStatement = secureConnection.prepareStatement(SELECT_ALL_PSTM)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            selectMedicinesInOrder(ordersHasMedicinesList, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exception on find all", e);
        }
        return ordersHasMedicinesList;
    }

    /**
     * @param orderId
     * @return
     * @throws DaoException
     */
    @Override
    public List<OrderHasMedicine> findAllMedicinesByOrderId(Integer orderId) throws DaoException {
        ArrayList<OrderHasMedicine> ordersHasMedicinesList = new ArrayList<>();
        try (PreparedStatement preparedStatement = secureConnection.prepareStatement(SELECT_BY_ORDER_ID_PSTM)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            selectMedicinesInOrder(ordersHasMedicinesList, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exception on find all", e);
        }
        return ordersHasMedicinesList;
    }

    /**
     * @param orderId
     * @param medicineId
     * @return
     * @throws DaoException
     */
    @Override
    public boolean deleteMedicineFromOrder(Integer orderId, Integer medicineId) throws DaoException {
        try (PreparedStatement preparedStatement = secureConnection.prepareStatement(DELETE_MEDICINE_FROM_ORDER_PSTM)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, medicineId);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception on delete all", e);
        }
    }

    /**
     * @param orderId
     * @return
     * @throws DaoException
     */
    @Override
    public boolean deleteAllMedicineFromOrder(Integer orderId) throws DaoException {
        try (PreparedStatement preparedStatement = secureConnection.prepareStatement(DELETE_ALL_MEDS_FROM_ORDER_PSTM)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception on delete all", e);
        }
    }

    /**
     * @param ordersHasMedicinesList
     * @param resultSet
     * @throws SQLException
     */
    private void selectMedicinesInOrder(ArrayList<OrderHasMedicine> ordersHasMedicinesList, ResultSet resultSet) throws
            SQLException {
        while (resultSet.next()) {
            OrderHasMedicine orderHasMedicine = new OrderHasMedicine();
            orderHasMedicine.setOrderId(resultSet.getInt(1));
            orderHasMedicine.setMedicineId(resultSet.getInt(2));
            orderHasMedicine.setMedicineQuantity(resultSet.getInt(3));
            orderHasMedicine.setMedicineSum(resultSet.getBigDecimal(4));
            orderHasMedicine.setRecipeId(resultSet.getInt(5));
            ordersHasMedicinesList.add(orderHasMedicine);
        }
    }

    /**
     * @param id
     * @return
     * @throws DaoException
     */
    @Override
    public OrderHasMedicine findEntityById(Integer id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(OrderHasMedicine entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * @param entity
     * @return
     * @throws DaoException
     */
    @Override
    public boolean create(OrderHasMedicine entity) throws DaoException {
        try (PreparedStatement preparedStatement = secureConnection.prepareStatement(INSERT_MEDICINE_IN_ORDER_PSTM)) {
            preparedStatement.setInt(1, entity.getOrderId());
            preparedStatement.setInt(2, entity.getMedicineId());
            preparedStatement.setInt(3, entity.getMedicineQuantity());
            preparedStatement.setBigDecimal(4, entity.getMedicineSum());
            preparedStatement.setInt(5, entity.getRecipeId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception on create medicine in order", e);
        }
    }

    /**
     * @param entity
     * @return
     * @throws DaoException
     */
    @Override
    public boolean update(OrderHasMedicine entity) throws DaoException {
        try (PreparedStatement preparedStatement = secureConnection.prepareStatement(UPDATE_PSTM)) {
            preparedStatement.setInt(1, entity.getMedicineQuantity());
            preparedStatement.setBigDecimal(2, entity.getMedicineSum());
            preparedStatement.setInt(3, entity.getRecipeId());
            preparedStatement.setInt(4, entity.getOrderId());
            preparedStatement.setInt(5, entity.getMedicineId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception on create medicine in order", e);
        }
    }


}
