package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.dao.AbstractOrderHasMedicineDao;
import by.epam.pharmacy.entity.OrderHasMedicine;
import by.epam.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 */
public class OrderHasMedicineDao extends AbstractDaoImpl<OrderHasMedicine> implements AbstractOrderHasMedicineDao<OrderHasMedicine> {
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_PSTM = "select order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id from order_has_medicine";
    private static final String SELECT_BY_ORDER_ID_PSTM = "select order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id from order_has_medicine where order_order_id = ?";
    private static final String SELECT_BY_ORDER_MEDICINE_PSTM = "select order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id from order_has_medicine where order_order_id = ? and medicine_mdc_id=?";
    private static final String SELECT_BY_MEDICINE_ID_PSTM = "select order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id from order_has_medicine where medicine_mdc_id = ?";
    private static final String INSERT_MEDICINE_IN_ORDER_PSTM = "insert into order_has_medicine(order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum ) values(?,?,?,?)";
    private static final String DELETE_ALL_MEDS_FROM_ORDER_PSTM = "delete from order_has_medicine where order_order_id = ?";
    private static final String DELETE_MEDICINE_FROM_ORDER_PSTM = "delete from order_has_medicine where order_order_id = ? and medicine_mdc_id=?";
    private static final String UPDATE_PSTM = "update `order_has_medicine` set ohm_med_quantity = ?, ohm_med_sum = ? where order_order_id = ? and medicine_mdc_id = ?";
    private ProxyConnection proxyConnection;

    public OrderHasMedicineDao() throws DaoException {
        proxyConnection = super.proxyConnection;
    }

    @Override
    public OrderHasMedicine findOrderHasMedicineByOrderIdMedicineId(Integer orderId, Integer medicineId) throws DaoException {
        OrderHasMedicine orderHasMedicine=new OrderHasMedicine();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ORDER_MEDICINE_PSTM)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, medicineId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                orderHasMedicine.setOrderId(resultSet.getInt(1));
                orderHasMedicine.setMedicineId(resultSet.getInt(2));
                orderHasMedicine.setMedicineQuantity(resultSet.getInt(3));
                orderHasMedicine.setMedicineSum(resultSet.getBigDecimal(4));
                orderHasMedicine.setRecipeId(resultSet.getInt(5));
            }
            } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orderHasMedicine;
    }

    /**
     * @return
     * @throws DaoException
     */
    @Override
    public ArrayList<OrderHasMedicine> findAll() throws DaoException {
        ArrayList<OrderHasMedicine> ordersHasMedicinesList = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_ALL_PSTM)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            fillMedicinesInOrder(ordersHasMedicinesList, resultSet);
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
    public ArrayList<OrderHasMedicine> findAllMedicinesByOrderId(Integer orderId) throws DaoException {
        ArrayList<OrderHasMedicine> ordersHasMedicinesList = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ORDER_ID_PSTM)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            fillMedicinesInOrder(ordersHasMedicinesList, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exception on find all", e);
        }
        return ordersHasMedicinesList;
    }

    @Override
    public OrderHasMedicine findOrderHasMedicineByMedicineId(Integer medicineId) throws DaoException {
        OrderHasMedicine orderHasMedicine = new OrderHasMedicine();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_MEDICINE_ID_PSTM)) {
            preparedStatement.setInt(1, medicineId);
            preparedStatement.execute();
            logger.info(preparedStatement);
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
            orderHasMedicine.setOrderId(resultSet.getInt(1));
            orderHasMedicine.setMedicineId(resultSet.getInt(2));
            orderHasMedicine.setMedicineQuantity(resultSet.getInt(3));
            orderHasMedicine.setMedicineSum(resultSet.getBigDecimal(4));
            orderHasMedicine.setRecipeId(resultSet.getInt(5));
            logger.info(orderHasMedicine);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception on  findOrderHasMedicineByMedicineId() ", e);
        }
        return orderHasMedicine;
    }

    /**
     * @param orderId
     * @param medicineId
     * @return
     * @throws DaoException
     */
    @Override
    public boolean deleteMedicineFromOrder(Integer orderId, Integer medicineId) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_MEDICINE_FROM_ORDER_PSTM)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, medicineId);
            logger.info(preparedStatement);
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on delete all", e);
        }
        return success;
    }

    /**
     * @param orderId
     * @return
     * @throws DaoException
     */
    @Override
    public boolean deleteAllMedicineFromOrder(Integer orderId) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_ALL_MEDS_FROM_ORDER_PSTM)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on delete all", e);
        }
        return success;
    }

    /**
     * @param ordersHasMedicinesList
     * @param resultSet
     * @throws SQLException
     */
    private void fillMedicinesInOrder(ArrayList<OrderHasMedicine> ordersHasMedicinesList, ResultSet resultSet) throws
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
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(INSERT_MEDICINE_IN_ORDER_PSTM)) {
            preparedStatement.setInt(1, entity.getOrderId());
            preparedStatement.setInt(2, entity.getMedicineId());
            preparedStatement.setInt(3, entity.getMedicineQuantity());
            preparedStatement.setBigDecimal(4, entity.getMedicineSum());
            logger.info(preparedStatement);
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on create medicine in order", e);
        }
        return success;
    }

    /**
     * @param entity
     * @return
     * @throws DaoException
     */
    @Override
    public boolean update(OrderHasMedicine entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_PSTM)) {
            preparedStatement.setInt(1, entity.getMedicineQuantity());
            preparedStatement.setBigDecimal(2, entity.getMedicineSum());
            preparedStatement.setInt(3, entity.getOrderId());
            preparedStatement.setInt(4, entity.getMedicineId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on create medicine in order", e);
        }
        return success;
    }


}
