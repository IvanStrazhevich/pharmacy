package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.dao.AbstractDao;
import by.epam.pharmacy.entity.PharmacyAccount;
import by.epam.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PharmacyAccountDaoImpl extends AbstractDaoImpl<PharmacyAccount> implements AbstractDao<PharmacyAccount> {
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_PSTM = "select user_id, phac_account_debet, phac_account_credit from `pharmacy_account`";
    private static final String SELECT_BY_ID_PSTM = "select user_id, phac_account_debet, phac_account_credit from `pharmacy_account` where user_id = ?";
    private static final String INSERT_PSTM = "insert into `pharmacy_account` (user_id, phac_account_debet, phac_account_credit) values(?,?,?)";
    private static final String DELETE_PSTM = "delete from `pharmacy_account` where user_id = ?";
    private static final String UPDATE_PSTM = "update `pharmacy_account` set user_id=?, phac_account_debet=?, phac_account_credit=? where user_id = ?";
    private ProxyConnection proxyConnection;

    public PharmacyAccountDaoImpl() throws DaoException {
        proxyConnection = super.proxyConnection;
    }

    @Override
    public ArrayList<PharmacyAccount> findAll() throws DaoException {
        ArrayList<PharmacyAccount> accounts = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_ALL_PSTM)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PharmacyAccount account = new PharmacyAccount();
                account.setClientId(resultSet.getInt(1));
                account.setAccountDebit(resultSet.getBigDecimal(2));
                account.setAccountCredit(resultSet.getBigDecimal(3));
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new DaoException("Exeption on findAll Parmacy Account", e);
        }
        return accounts;
    }

    /**
     * @param id
     */
    @Override
    public PharmacyAccount findEntityById(int id) throws DaoException {
        PharmacyAccount account = new PharmacyAccount();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ID_PSTM)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                account.setClientId(resultSet.getInt(1));
                account.setAccountDebit(resultSet.getBigDecimal(2));
                account.setAccountCredit(resultSet.getBigDecimal(3));
            }
        } catch (SQLException e) {
            throw new DaoException("Exeption on findById Parmacy Account", e);
        }
        return account;

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
    public boolean delete(PharmacyAccount entity) throws DaoException {
        int id = entity.getClientId();
        return deleteById(id, DELETE_PSTM);
    }

    /**
     * @param entity
     */
    @Override
    public boolean create(PharmacyAccount entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(INSERT_PSTM)) {
            preparedStatement.setInt(1, entity.getClientId());
            preparedStatement.setBigDecimal(2, entity.getAccountDebit());
            preparedStatement.setBigDecimal(3, entity.getAccountCredit());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on create Pharmacy Account", e);
        }
        return success;
    }

    /**
     * @param entity
     */
    @Override
    public boolean update(PharmacyAccount entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_PSTM)) {
            preparedStatement.setInt(1, entity.getClientId());
            preparedStatement.setBigDecimal(2, entity.getAccountDebit());
            preparedStatement.setBigDecimal(3, entity.getAccountCredit());
            preparedStatement.setInt(4, entity.getClientId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on update Pharmacy Account", e);
        }
        return success;
    }
}