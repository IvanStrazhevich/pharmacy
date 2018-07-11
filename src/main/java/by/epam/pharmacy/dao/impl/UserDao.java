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

public class UserDao extends AbstractDaoImpl<User> {
    private static final String SELECT_ALL_PSTM = "select  user_id, user_login, user_password, user_access_level from user";
    private static final String SELECT_BY_ID_PSTM = "select user_id, user_login, user_password, user_access_level from user where user_id = ?";
    private static final String INSERT_PSTM = "insert into user(user_login, user_password, user_access_level) values(?,?,?)";
    private static final String DELETE_PSTM = "delete from user where user_id = ?";
    private static final String UPDATE_PSTM = "update user set user_login = ?, user_password = ?, user_access_level = ? where user_id = ?";
    private static Logger logger = LogManager.getLogger();
    private ProxyConnection proxyConnection;

    public UserDao() throws DaoException {
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
                user.setUserId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setAccessLevel(resultSet.getString(4));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception on find all", e);
        }
        return userList;
    }


    @Override
    public User findEntityById(Integer id) throws DaoException {
        User user = new User();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ID_PSTM)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            user.setUserId(resultSet.getInt(1));
            user.setLogin(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setAccessLevel(resultSet.getString(4));
        } catch (SQLException e) {
            throw new DaoException("Exception on find by id", e);
        }
        return user;
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        return deleteById(id, DELETE_PSTM);
    }

    @Override
    public boolean delete(User entity) throws DaoException {
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_PSTM)) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception on deleteById", e);
        }
    }

    @Override
    public boolean create(User entity) throws DaoException {
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(INSERT_PSTM)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getAccessLevel());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception on create", e);
        }
    }

    @Override
    public boolean update(User entity) throws DaoException {
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_PSTM)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getAccessLevel());
            preparedStatement.setInt(4, entity.getUserId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception on update", e);
        }
    }
}
