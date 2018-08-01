package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.dao.AmountDao;
import by.epam.pharmacy.entity.ClientAmount;
import by.epam.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AmountDaoImpl extends AbstractDaoImpl<ClientAmount> implements AmountDao<ClientAmount> {
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_PSTM = "select user_id, clam_amount_debit, clam_amount_credit from `client_amount`";
    private static final String SELECT_BY_ID_PSTM = "select user_id, clam_amount_debit, clam_amount_credit from `client_amount` where user_id = ?";
    private static final String INSERT_PSTM = "insert into `client_amount` (user_id, clam_amount_debit, clam_amount_credit) values(?,?,?)";
    private static final String DELETE_PSTM = "delete from `client_amount` where user_id = ?";
    private static final String UPDATE_PSTM = "update `client_amount` set user_id=?, clam_amount_debit=?, clam_amount_credit=? where user_id = ?";
    private ProxyConnection proxyConnection;

    public AmountDaoImpl(ProxyConnection proxyConnection) throws DaoException {
        this.proxyConnection = proxyConnection;
    }


    /**
     *
     */
    @Override
    public ArrayList<ClientAmount> findAll() throws DaoException {
        ArrayList<ClientAmount> amounts = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_ALL_PSTM)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                ClientAmount amount = new ClientAmount();
                amount.setClientId(resultSet.getInt(1));
                amount.setAmountCredit(resultSet.getBigDecimal(2));
                amount.setAmountCredit(resultSet.getBigDecimal(3));
                amounts.add(amount);
            }
        } catch (SQLException e) {
            throw new DaoException("Exeption on findAll Amount", e);
        }
        return amounts;
    }

    /**
     * @param id
     */
    @Override
    public ClientAmount findEntityById(int id) throws DaoException {
        ClientAmount amount = new ClientAmount();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ID_PSTM)) {
            preparedStatement.setInt(1, id);
            logger.info(preparedStatement.execute());
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                amount.setClientId(resultSet.getInt(1));
                amount.setAmountCredit(resultSet.getBigDecimal(2));
                amount.setAmountCredit(resultSet.getBigDecimal(3));
            }
        } catch (SQLException e) {
            throw new DaoException("Exeption on findById Amount", e);
        }
        return amount;

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
    public boolean delete(ClientAmount entity) throws DaoException {
    int id = entity.getClientId();
    return deleteById(id, DELETE_PSTM);
    }

    /**
     * @param entity
     */
    @Override
    public boolean create(ClientAmount entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(INSERT_PSTM)) {
            preparedStatement.setInt(1, entity.getClientId());
            preparedStatement.setBigDecimal(2, entity.getAmountDebit());
            preparedStatement.setBigDecimal(3, entity.getAmountCredit());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on create Amount", e);
        }
        return success;
    }

    /**
     * @param entity
     */
    @Override
    public boolean update(ClientAmount entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_PSTM)) {
            preparedStatement.setInt(1, entity.getClientId());
            preparedStatement.setBigDecimal(2, entity.getAmountDebit());
            preparedStatement.setBigDecimal(3, entity.getAmountCredit());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on create Amount", e);
        }
        return success;
    }

    @Override
    public boolean fillAccount() {
        return false;
    }
}
