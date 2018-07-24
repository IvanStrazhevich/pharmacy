package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.dao.AbstractOrderDao;
import by.epam.pharmacy.entity.Order;
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
public class OrderDao extends AbstractDaoImpl<Order> implements AbstractOrderDao<Order>{
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_PSTM = "select order_id, ord_user_id, ord_payed, ord_med_sum from `order`";
    private static final String SELECT_BY_ID_PSTM = "select order_id, ord_user_id, ord_payed, ord_med_sum from `order` where order_id = ?";
    private static final String SELECT_BY_USER_PSTM = "select order_id, ord_user_id, ord_payed, ord_med_sum from `order` where ord_user_id = ?";

    private static final String INSERT_PSTM = "insert into `order` (ord_user_id, ord_payed, ord_med_sum) values(?,?,?)";
    private static final String DELETE_PSTM = "delete from `order` where order_id = ?";
    private static final String UPDATE_PSTM = "update `order` set ord_user_id=?, ord_payed=?, ord_med_sum=? where order_id = ?";
    private ProxyConnection proxyConnection;

    public OrderDao() throws DaoException {
        proxyConnection = super.proxyConnection;
    }

    /**
     * @return
     * @throws DaoException
     */
    @Override
    public ArrayList<Order> findAll() throws DaoException {
        ArrayList<Order> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_ALL_PSTM)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt(1));
                order.setClientId(resultSet.getInt(2));
                order.setPayed(resultSet.getBoolean(3));
                order.setMedicineSum(resultSet.getBigDecimal(4));
                userList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception on find all Order", e);
        }
        return userList;
    }


    /**
     * @param id
     * @return
     * @throws DaoException
     */
    @Override
    public Order findEntityById(Integer id) throws DaoException {
        Order order = new Order();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ID_PSTM)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            fillOrder(order, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exception on Order find by id", e);
        }
        return order;
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
    public boolean delete(Order entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_PSTM)) {
            preparedStatement.setInt(1, entity.getOrderId());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on Order deleteById", e);
        }
        return success;
    }

    /**
     * @param entity
     * @return
     * @throws DaoException
     */
    @Override
    public boolean create(Order entity) throws DaoException {
        boolean success = false;
        logger.info(entity);
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(INSERT_PSTM)) {
            preparedStatement.setInt(1, entity.getClientId());
            preparedStatement.setBoolean(2, entity.isPayed());
            preparedStatement.setBigDecimal(3, entity.getMedicineSum());
            logger.info(preparedStatement);
            logger.info(entity);
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on Order create", e);
        }
        return success;
    }

    /**
     * @param entity
     * @return
     * @throws DaoException
     */
    @Override
    public boolean update(Order entity) throws DaoException {
        boolean success = false;
        logger.info(entity);
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_PSTM)) {
            preparedStatement.setInt(1, entity.getClientId());
            preparedStatement.setBoolean(2, entity.isPayed());
            preparedStatement.setBigDecimal(3, entity.getMedicineSum());
            preparedStatement.setInt(4, entity.getOrderId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on Order update", e);
        }
        return success;
    }

    @Override
    public Order findCurrentOrderByUserId(int id) throws DaoException {
        Order order = new Order();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_USER_PSTM)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            fillOrder(order, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exception on Order find by User", e);
        }
        return order;
    }

    private void fillOrder(Order order, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            order.setOrderId(resultSet.getInt(1));
            order.setClientId(resultSet.getInt(2));
            order.setPayed(resultSet.getBoolean(3));
            order.setMedicineSum(resultSet.getBigDecimal(4));
        }
    }
}

