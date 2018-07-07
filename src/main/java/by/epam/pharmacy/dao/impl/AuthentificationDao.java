package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthentificationDao extends AbstractDaoImpl<User> {
    private static final String SELECT_ALL_PSTM = "select client_cl_id, au_login, au_password, au_access_level from autentification";
    private static final String SELECT_BY_ID_PSTM = "client_cl_id, au_login, au_password, au_access_level from autentification where client_cl_id = ?";
    private static final String INSERT_PSTM = "insert into autentification(client_cl_id, au_login, au_password, au_access_level) values(?,?,?,?)";
    private static final String DELETE_PSTM = "delete from autentification where client_cl_id = ?";
    private static final String UPDATE_PSTM = "update user set au_login = ?, au_password = ?, au_access_level = ? where client_cl_id = ?";
    private static Logger logger = LogManager.getLogger();
    private ProxyConnection proxyConnection;

    public AuthentificationDao() throws DaoException {
        proxyConnection = super.proxyConnection;
    }

    @Override
    public ArrayList<User> findAll() throws DaoException {
        ArrayList<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_ALL_PSTM)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                User user = new User();
                user.setClientClId(resultSet.getInt(1));
                user.setAuLogin(resultSet.getString(2));
                user.setAuPassword(resultSet.getString(3));
                user.setAuAccessLevel(resultSet.getString(4));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception on find all", e);
        }
        return userList;
    }


    @Override
    public User findEntityById(int id) throws DaoException {
        User user = new User();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ID_PSTM)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            user.setClientClId(resultSet.getInt(1));
            user.setAuLogin(resultSet.getString(2));
            user.setAuPassword(resultSet.getString(3));
            user.setAuAccessLevel(resultSet.getString(4));
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException("Exception on find by id", e);
        }
        return user;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_PSTM)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Exception on delete", e);
        }
        return true;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_PSTM)) {
            preparedStatement.setInt(1, user.getClientClId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Exception on delete", e);
        }
        return true;
    }

    @Override
    public boolean create(User user) throws DaoException {
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(INSERT_PSTM)) {
            preparedStatement.setInt(1, user.getClientClId());
            preparedStatement.setString(2, user.getAuLogin());
            preparedStatement.setString(3, user.getAuPassword());
            preparedStatement.setString(4, user.getAuAccessLevel());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new DaoException("Exception on create", e);
        }
        return true;
    }

    @Override
    public boolean update(User user) throws DaoException {
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_PSTM)) {
            preparedStatement.setString(1,user.getAuLogin());
            preparedStatement.setString(2, user.getAuPassword());
            preparedStatement.setString(3, user.getAuAccessLevel());
            preparedStatement.setInt(4, user.getClientClId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Exception on update", e);
        }
        return true;
    }
}
