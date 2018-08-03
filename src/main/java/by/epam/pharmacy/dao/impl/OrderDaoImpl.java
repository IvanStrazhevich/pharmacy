package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.dao.OrderDao;
import by.epam.pharmacy.entity.Medicine;
import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.entity.OrderHasMedicine;
import by.epam.pharmacy.entity.Recipe;
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
public class OrderDaoImpl extends AbstractDaoImpl<Order> implements OrderDao<Order> {
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_PSTM = "select order_id, ord_user_id, ord_payed, ord_med_sum from `order`";
    private static final String SELECT_BY_ID_PSTM = "select order_id, ord_user_id, ord_payed, ord_med_sum from `order` where order_id = ?";
    private static final String SELECT_BY_USER_PSTM = "select order_id, ord_user_id, ord_payed, ord_med_sum from `order` where ord_user_id = ?";
    private static final String SELECT_BY_ORDER_ID_PSTM = "select ohm.order_order_id, m.mdc_name, ohm.ohm_med_quantity, ohm.ohm_med_sum, ohm.recipe_rec_id, m.mdc_dosage, m.mdc_recipe_required, m.mdc_id, m.mdc_price, r.res_approved, r.rec_meds_quantity, m.mdc_quantity, o.ord_user_id, o.ord_payed from order_has_medicine as ohm LEFT JOIN `medicine` as m on medicine_mdc_id=mdc_id LEFT JOIN `order`as o on ohm.order_order_id = o.order_id LEFT JOIN `recipe` as r on ohm.recipe_rec_id = r.rec_id where ohm.order_order_id = ? order BY m.mdc_name";
    private static final String INSERT_PSTM = "insert into `order` (ord_user_id, ord_payed, ord_med_sum) values(?,?,?)";
    private static final String DELETE_PSTM = "delete from `order` where order_id = ?";
    private static final String UPDATE_PSTM = "update `order` set ord_user_id=?, ord_payed=?, ord_med_sum=? where order_id = ?";
    private ProxyConnection proxyConnection;

    /**
     *
     */
    public OrderDaoImpl() throws DaoException {
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
                order.setOrderSum(resultSet.getBigDecimal(4));
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
    public Order findEntityById(int id) throws DaoException {
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
    public boolean deleteById(int id) throws DaoException {
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
            preparedStatement.setBigDecimal(3, entity.getOrderSum());
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
            preparedStatement.setBigDecimal(3, entity.getOrderSum());
            preparedStatement.setInt(4, entity.getOrderId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on Order update", e);
        }
        return success;
    }

    /**
     * @param id
     */
    @Override
    public Order findCurrentOrderByUserId(int id) throws DaoException {
        Order order = new Order();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_USER_PSTM)) {
            logger.info(id);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            fillOrder(order, resultSet);
            logger.info(order);
        } catch (SQLException e) {
            throw new DaoException("Exception on Order find by User", e);
        }
        return order;
    }

    /**
     * @param orderId
     */
    @Override
    public Order showOrderWithMedicineByOrderId(Integer orderId) throws DaoException {
        Order order = new Order();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ORDER_ID_PSTM)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Medicine medicine = new Medicine();
                OrderHasMedicine orderHasMedicine = new OrderHasMedicine();
                Recipe recipe = new Recipe();
                order.setOrderId(resultSet.getInt(1));
                medicine.setMedicineName(resultSet.getString(2));
                orderHasMedicine.setMedicineQuantity(resultSet.getInt(3));
                orderHasMedicine.setMedicineSum(resultSet.getBigDecimal(4));
                orderHasMedicine.setRecipeId(resultSet.getInt(5));
                medicine.setDosage(resultSet.getBigDecimal(6));
                medicine.setRecipeRequired(resultSet.getBoolean(7));
                orderHasMedicine.setMedicineId(resultSet.getInt(8));
                medicine.setMedicineId(resultSet.getInt(8));
                medicine.setPrice(resultSet.getBigDecimal(9));
                recipe.setApproved(resultSet.getBoolean(10));
                recipe.setMedicineQuantity(resultSet.getInt(11));
                medicine.setQuantityAtStorage(resultSet.getInt(12));
                order.setClientId(resultSet.getInt(13));
                order.setPayed(resultSet.getBoolean(14));
                orderHasMedicine.setMedicine(medicine);
                orderHasMedicine.setRecipe(recipe);
                order.getOrderHasMedicines().add(orderHasMedicine);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception on OrderDaoImpl show Order With Medicine", e);
        }
        return order;
    }

    /**
     * @param order
     * @param resultSet
     */
    private void fillOrder(Order order, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            order.setOrderId(resultSet.getInt(1));
            order.setClientId(resultSet.getInt(2));
            order.setPayed(resultSet.getBoolean(3));
            order.setOrderSum(resultSet.getBigDecimal(4));
        }
    }
}


