package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.connection.ConnectionPool;
import by.epam.pharmacy.dao.AbstractDao;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Common Dao methods implementation
 * @param <T>
 */
public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_LAST_INSERT_ID_PSTM = "select last_insert_id()";
    protected ProxyConnection proxyConnection;

    public AbstractDaoImpl() throws DaoException {
        try {
            this.proxyConnection = ConnectionPool.getInstance().getConnection();
        } catch (PoolException e) {
            throw new DaoException("There is no free connection", e);
        }
    }

    /**
     * @return last autoincrement inserted id of entity
     * @throws DaoException
     */
    @Override
    public Integer findLastInsertId() throws DaoException {
        int id = 0;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_LAST_INSERT_ID_PSTM)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            id = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DaoException("Exception on find last id", e);
        }
        return id;
    }

    /**
     * Delete entity by it's id
     * @param id
     * @param statement
     * @return true if deleted, false if exception
     * @throws DaoException
     */
    @Override
    public boolean deleteById(Integer id, String statement) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(statement)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on deleteById", e);
        } return success;
    }

    /**
     * puts proxy connection back to pool
     */
    public void close() {
        if (proxyConnection != null) {
            logger.info("closing dao");
            proxyConnection.close();

        }
    }
}
