package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.dao.OrderHasMedicineDao;
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
public class OrderHasMedicineDaoImpl extends AbstractDaoImpl<OrderHasMedicine> implements OrderHasMedicineDao<OrderHasMedicine> {
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_PSTM = "select order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id from order_has_medicine";
    private static final String SELECT_BY_ORDER_ID_PSTM = "select order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id from order_has_medicine where order_order_id = ?";
    private static final String SELECT_BY_ORDER_MEDICINE_PSTM = "select order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id from order_has_medicine where order_order_id = ? and medicine_mdc_id=?";
    private static final String SELECT_BY_MEDICINE_ID_PSTM = "select order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id from order_has_medicine where medicine_mdc_id = ?";
    private static final String INSERT_MEDICINE_IN_ORDER_PSTM = "insert into order_has_medicine(order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum ) values(?,?,?,?)";
    private static final String DELETE_ALL_MEDS_FROM_ORDER_PSTM = "delete from order_has_medicine where order_order_id = ?";
    private static final String DELETE_MEDICINE_FROM_ORDER_PSTM = "delete from order_has_medicine where order_order_id = ? and medicine_mdc_id=?";
    private static final String UPDATE_PSTM = "update `order_has_medicine` set ohm_med_quantity = ?, ohm_med_sum = ? where order_order_id = ? and medicine_mdc_id = ?";
    private static final String UPDATE_RECIPE_PSTM = "update `order_has_medicine` set recipe_rec_id=? where order_order_id = ? and medicine_mdc_id = ?";

    private ProxyConnection proxyConnection;

    /**
     * 
     */
    public OrderHasMedicineDaoImpl() throws DaoException {
        proxyConnection = super.proxyConnection;
    }

    /**
     * Find medicine from Order according params
     * @param orderId id of Order
     * @param medicineId id of Medicine
     * @return OrderHasMedicine record
     */
    @Override
    public OrderHasMedicine findOrderHasMedicineByOrderIdMedicineId(Integer orderId, Integer medicineId) throws DaoException {
        OrderHasMedicine orderHasMedicine=new OrderHasMedicine();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ORDER_MEDICINE_PSTM)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, medicineId);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            ResultSet resultSet = preparedStatement.executeQuery();
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
            ResultSet resultSet = preparedStatement.executeQuery();
            fillMedicinesInOrder(ordersHasMedicinesList, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exception on find all", e);
        }
        return ordersHasMedicinesList;
    }

    /**
     * 
     * @param medicineId 
     */
    @Override
    public OrderHasMedicine findOrderHasMedicineByMedicineId(Integer medicineId) throws DaoException {
        OrderHasMedicine orderHasMedicine = new OrderHasMedicine();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_MEDICINE_ID_PSTM)) {
            preparedStatement.setInt(1, medicineId);
            ResultSet resultSet = preparedStatement.executeQuery();
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
     * Delete medicine from Order according params
     * @param orderId id of Order
     * @param medicineId id of Medicine
     * @return true if deleted false if exception thrown
     */
    @Override
    public boolean deleteMedicineFromOrder(Integer orderId, Integer medicineId) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_MEDICINE_FROM_ORDER_PSTM)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, medicineId);
            logger.info(preparedStatement);
            preparedStatement.executeUpdate();
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
            preparedStatement.executeUpdate();
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
    public OrderHasMedicine findEntityById(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     */
    @Override
    public boolean deleteById(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param entity 
     */
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
            throw new DaoException("Exception on update medicine in order", e);
        }
        return success;
    }

    /**
     * Update recipe id in record
     * @param entity
     * @return true if updated false if exception thrown
     */
    public boolean updateRecipe(OrderHasMedicine entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_RECIPE_PSTM)) {
            preparedStatement.setInt(1, entity.getRecipeId());
            preparedStatement.setInt(2, entity.getOrderId());
            preparedStatement.setInt(3, entity.getMedicineId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on update recipe in medicine in order", e);
        }
        return success;
    }


    }

