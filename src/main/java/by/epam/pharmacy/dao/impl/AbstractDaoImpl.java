package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.connection.ProxyConnectionPool;
import by.epam.pharmacy.dao.AbstractDao;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.ProxyPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {
    private static final String SELECT_LAST_INSERT_ID_PSTM = "select last_insert_id()";
    private static Logger logger = LogManager.getLogger();
    protected ProxyConnection proxyConnection;

    public AbstractDaoImpl() throws DaoException {
        try {
            this.proxyConnection = ProxyConnectionPool.getConnectionPool().getConnection();
        } catch (ProxyPoolException e) {
            throw new DaoException("There is no free connection", e);
        }
    }

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
    @Override
    public boolean deleteById(Integer id, String statement) throws DaoException {
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(statement)) {
            preparedStatement.setInt(1, id);
            logger.info("deleting");
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception on deleteById", e);
        }
    }


    public void close() throws DaoException {
        if (proxyConnection != null) {
            logger.info("closing dao");
            proxyConnection.close();

        }
    }
}
