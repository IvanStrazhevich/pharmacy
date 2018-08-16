package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.dao.PaymentDao;
import by.epam.pharmacy.entity.Payment;
import by.epam.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDaoImpl<T> extends AbstractDaoImpl<Payment> implements PaymentDao<Payment> {
    private static Logger logger = LogManager.getLogger();
    private static final String SET_AMOUNT = "UPDATE client_amount set clam_amount_debit = ?, clam_amount_credit = ? WHERE user_id = ?";
    private static final String SET_ACCOUNT = "UPDATE pharmacy_account set phac_account_debet = ?, phac_account_credit = ? WHERE user_id = ?";
    private static final String SELECT_BY_ID_AMOUNT_PSTM = "select clam_amount_debit, clam_amount_credit from client_amount WHERE user_id = ?";
    private static final String SELECT_BY_ID_ACCOUNT_PSTM = "SELECT phac_account_debet, phac_account_credit FROM pharmacy_account WHERE  user_id =?";
    private static final String SELECT_ALL_PSTM = "select payment_id, pmt_order_id, pmt_ord_sum, pmt_confirmed from `payment`";
    private static final String SELECT_BY_ID_PSTM = "select payment_id, pmt_order_id, pmt_ord_sum, pmt_confirmed from `payment` where payment_id = ?";
    private static final String SELECT_BY_ORDER_ID_PSTM = "select payment_id, pmt_order_id, pmt_ord_sum, pmt_confirmed from `payment` where pmt_order_id = ?";
    private static final String INSERT_PSTM = "insert into `payment` (pmt_order_id, pmt_ord_sum, pmt_confirmed) values(?,?,?)";
    private static final String DELETE_PSTM = "delete from `payment` where payment_id = ?";
    private static final String DELETE_BY_ORDER_ID_PSTM = "delete from `payment` where pmt_order_id = ?";
    private static final String UPDATE_PSTM = "update `payment` set pmt_order_id=?, pmt_ord_sum=?, pmt_confirmed=? where payment_id = ?";
    private ProxyConnection proxyConnection;

    /**
     *
     */
    public PaymentDaoImpl() throws DaoException {
        proxyConnection = super.proxyConnection;
    }

    /**
     * Make payment transaction
     * @param accountDebit sum to pay from debit amount
     * @param accountCredit sum to pay as credit
     * @param userId user payed
     * @return true if payment succeed
     * @throws DaoException
     */
    @Override
    public boolean makePayment(BigDecimal accountDebit, BigDecimal accountCredit, int userId) throws DaoException {
        boolean transfered = false;
        try (PreparedStatement preparedStatementFrom = proxyConnection.prepareStatement(SET_AMOUNT);
             PreparedStatement preparedStatementTo = proxyConnection.prepareStatement(SET_ACCOUNT);
             PreparedStatement preparedStatementGetBalanceFrom = proxyConnection.prepareStatement(SELECT_BY_ID_AMOUNT_PSTM);
             PreparedStatement preparedStatementGetBalanceTo = proxyConnection.prepareStatement(SELECT_BY_ID_ACCOUNT_PSTM)) {
            proxyConnection.setAutoCommit(false);
            BigDecimal amountDebitDB = null;
            BigDecimal amountCreditDB = null;
            BigDecimal accountDebitDB = null;
            BigDecimal accountCreditDB = null;
            preparedStatementGetBalanceFrom.setInt(1, userId);
            preparedStatementGetBalanceFrom.execute();
            preparedStatementGetBalanceTo.setInt(1, userId);
            preparedStatementGetBalanceTo.execute();
            ResultSet resultSetFrom = preparedStatementGetBalanceFrom.getResultSet();
            while (resultSetFrom.next()) {
                amountDebitDB = resultSetFrom.getBigDecimal(1);
                amountCreditDB = resultSetFrom.getBigDecimal(2);
            }
            ResultSet resultSetTo = preparedStatementGetBalanceTo.getResultSet();
            while (resultSetTo.next()) {
                accountDebitDB = resultSetTo.getBigDecimal(1);
                accountCreditDB = resultSetTo.getBigDecimal(2);
            }
            if (amountDebitDB.subtract(accountDebit).compareTo(new BigDecimal(0)) >= 0) {
                preparedStatementFrom.setBigDecimal(1, amountDebitDB.subtract(accountDebit));
                preparedStatementFrom.setBigDecimal(2, amountCreditDB);
                preparedStatementFrom.setInt(3, userId);
                preparedStatementTo.setBigDecimal(1, accountDebitDB.add(accountDebit));
                preparedStatementTo.setBigDecimal(2, accountCreditDB.add(accountCredit));
                preparedStatementTo.setInt(3, userId);
                preparedStatementFrom.executeUpdate();
                preparedStatementTo.executeUpdate();
                proxyConnection.commit();
                transfered = true;
            }
        } catch (SQLException e) {
            try {
                proxyConnection.rollback();
            } catch (SQLException e1) {
                throw new DaoException("Rollback error", e);
            }
            throw new DaoException("Rolled Back", e);
        } finally {
            try {
                proxyConnection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error("AutoCommit true not settled", e);
            }
        }
        return transfered;
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
                payment.setOrderId(resultSet.getInt(2));
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
     * Find payment details for order by param
     * @param orderId id of order type int
     * @return Payment record
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
     * Delete element from database
     * @param id of type int
     * @return true if deleted successfully false if not
     */
    @Override
    public boolean deleteById(int id) throws DaoException {
        return deleteById(id, DELETE_PSTM);
    }

    /**
     * Delete element if it is
     * @return true if deleted successfully false if not
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
    public boolean deletePaymentByOrderId(int orderId) throws DaoException {
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
            preparedStatement.setInt(1, entity.getOrderId());
            preparedStatement.setBigDecimal(2, entity.getOrderSum());
            preparedStatement.setBoolean(3, entity.isPaymentConfirmed());
            preparedStatement.setInt(4, entity.getPaymentId());
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
            payment.setOrderId(resultSet.getInt(2));
            payment.setOrderSum(resultSet.getBigDecimal(3));
            payment.setPaymentConfirmed(resultSet.getBoolean(4));
        }
    }

}