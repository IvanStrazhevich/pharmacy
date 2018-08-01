package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.dao.PaymentDao;
import by.epam.pharmacy.entity.Payment;
import by.epam.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDaoImpl<T> extends AbstractDaoImpl<Payment> implements PaymentDao<Payment> {
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_PSTM = "select payment_id, pmt_order_id, pmt_ord_sum, pmt_confirmed from `payment`";
    private static final String SELECT_BY_ID_PSTM = "select payment_id, pmt_order_id, pmt_ord_sum, pmt_confirmed from `payment` where payment_id = ?";
    private static final String SELECT_BY_ORDER_ID_PSTM = "select payment_id, pmt_order_id, pmt_ord_sum, pmt_confirmed from `payment` where pmt_order_id = ?";
    private static final String INSERT_PSTM = "insert into `payment` (pmt_order_id, pmt_ord_sum, pmt_confirmed) values(?,?,?,?)";
    private static final String DELETE_PSTM = "delete from `payment` where payment_id = ?";
    private static final String DELETE_BY_ORDER_ID_PSTM = "delete from `payment` where pmt_order_id = ?";
    private static final String UPDATE_PSTM = "update `payment` set payment_id=?, pmt_order_id=?, pmt_ord_sum=?, pmt_confirmed=? set where payment_id = ?";
    private ProxyConnection proxyConnection;

    /**
     *
     */
    public PaymentDaoImpl() throws DaoException {
        proxyConnection = super.proxyConnection;
    }

    @Override
    public boolean makePayment() {
        return false;
    }

    /**
     * @return ArrayList<Payment>
     * @throws DaoException
     */
    @Override
    public ArrayList<Payment> findAll() throws DaoException {
        ArrayList<Payment> payments = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_ALL_PSTM)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(resultSet.getInt(1));
                payment.setPaymentId(resultSet.getInt(2));
                payment.setOrderSum(resultSet.getBigDecimal(3));
                payment.setPaymentConfirmed(resultSet.getBoolean(4));
                payments.add(payment);
            }
        } catch (SQLException e) {
            throw new DaoException("Exeption on findAll Payment", e);
        }
        return payments;
    }

    /**
     * @param id
     */
    @Override
    public Payment findEntityById(int id) throws DaoException {
        Payment payment = new Payment();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ID_PSTM)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            fillPayment(payment, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exeption on findAllById Payment", e);
        }
        return payment;
    }

    /**
     * @param orderId
     * @return
     * @throws DaoException
     */
    @Override
    public Payment findPaymentByOrderId(int orderId) throws DaoException {
        Payment payment = new Payment();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ORDER_ID_PSTM)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            fillPayment(payment, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exeption on findAllByOrderId Payment", e);
        }
        return payment;
    }

    /**
     * @param id
     */
    @Override
    public boolean deleteById(int id) throws DaoException {
        return deleteById(id, DELETE_PSTM);
    }

    /**
     * @param entity
     */
    @Override
    public boolean delete(Payment entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_PSTM)) {
            preparedStatement.setInt(1, entity.getPaymentId());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on Payment deleteById", e);
        }
        return success;
    }

    /**
     * @param orderId
     * @return
     * @throws DaoException
     */
    @Override
    public boolean DeletePaymentByOrderId(int orderId) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_BY_ORDER_ID_PSTM)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on Payment deleteByOrderId", e);
        }
        return success;
    }

    /**
     * @param entity
     */
    @Override
    public boolean create(Payment entity) throws DaoException {
        boolean success = false;
        logger.info(entity);
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(INSERT_PSTM)) {
            preparedStatement.setInt(1, entity.getOrderId());
            preparedStatement.setBigDecimal(2, entity.getOrderSum());
            preparedStatement.setBoolean(3, entity.isPaymentConfirmed());
            logger.info(preparedStatement);
            logger.info(entity);
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on Payment create", e);
        }
        return success;
    }

    /**
     * @param entity
     */
    @Override
    public boolean update(Payment entity) throws DaoException {
        boolean success = false;
        logger.info(entity);
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_PSTM)) {
            preparedStatement.setInt(1,entity.getPaymentId());
            preparedStatement.setInt(2, entity.getOrderId());
            preparedStatement.setBigDecimal(3, entity.getOrderSum());
            preparedStatement.setBoolean(4, entity.isPaymentConfirmed());
            logger.info(preparedStatement);
            logger.info(entity);
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on Payment create", e);
        }
        return success;
    }


    private void fillPayment(Payment payment, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            payment.setPaymentId(resultSet.getInt(1));
            payment.setPaymentId(resultSet.getInt(2));
            payment.setOrderSum(resultSet.getBigDecimal(3));
            payment.setPaymentConfirmed(resultSet.getBoolean(4));
        }
    }

}