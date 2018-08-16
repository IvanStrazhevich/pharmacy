package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.dao.UserDao;
import by.epam.pharmacy.entity.ClientDetail;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementation of AbstractDao for type User
 */
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao<User> {
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_PSTM = "select  user_id, user_login, user_password, user_access_level from user";
    private static final String SELECT_BY_ID_PSTM = "select user_id, user_login, user_password, user_access_level from user where user_id = ?";
    private static final String SELECT_BY_LOGIN_PSTM = "select user_id, user_login, user_password, user_access_level from user where user_login = ?";
    private static final String SELECT_BY_ACCESS_PSTM = "select user_id, user_login, user_password, user_access_level from user where user_access_level = ?";
    private static final String INSERT_PSTM = "insert into user(user_login, user_password, user_access_level) values(?,?,?)";
    private static final String DELETE_PSTM = "delete from user where user_id = ?";
    private static final String UPDATE_PSTM = "update user set user_login = ?, user_password = ?, user_access_level = ? where user_id = ?";
    private static final String FIND_ALL_WITH_NAMES
            = "select u.user_id, u.user_access_level, cd.cl_name, cd.cl_lastname  FROM client_detail as cd LEFT OUTER JOIN `user` as u ON cd.user_id = u.user_id";
    private ProxyConnection proxyConnection;

    /**
     * 
     */
    public UserDaoImpl() throws DaoException {
        proxyConnection = super.proxyConnection;
    }

    /**
     * Finds Users all existed
     *
     * @return ArrayList<User>
     * @throws DaoException
     */
    @Override
    public ArrayList<User> findAll() throws DaoException {
        ArrayList<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_ALL_PSTM)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            fillUsers(userList, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exception on User find all", e);
        }
        return userList;
    }


    /**
     * Finds User by its id
     * @param id type Integer
     * @return User
     * @throws DaoException
     */

    @Override
    public User findEntityById(int id) throws DaoException {
        User user = new User();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ID_PSTM)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            fillUser(user, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exception on User find by id", e);
        }
        return user;
    }


    /**
     * Finds User by its login
     * @param login type String
     * @return User
     * @throws DaoException
     */
    public User findUserByLogin(String login) throws DaoException {
        User user = new User();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_LOGIN_PSTM)) {
            preparedStatement.setString(1, login);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            fillUser(user, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exception on User find by login", e);
        }
        return user;
    }

    /**
     * @param id of type Integer
     * @return true if statement delete item successfully
     * @throws DaoException
     */
    @Override
    public boolean deleteById(int id) throws DaoException {
        return deleteById(id, DELETE_PSTM);
    }

    /**
     * @param entity of type User
     * @return true if statement delete item successfully
     * @throws DaoException
     */
    @Override
    public boolean delete(User entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_PSTM)) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on User deleteById", e);
        }
        return success;
    }

    /**
     * Persists to database
     * @param entity of type User
     * @return true if statement create item successfully
     * @throws DaoException
     */
    @Override
    public boolean create(User entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(INSERT_PSTM)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getAccessLevel());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on User create", e);
        }
        return success;
    }

    /**
     * Updates record in database
     * @param entity of type User
     * @return true if statement update item successfully
     * @throws DaoException
     */
    @Override
    public boolean update(User entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_PSTM)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getAccessLevel());
            preparedStatement.setInt(4, entity.getUserId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on User update", e);
        }
        return success;
    }

    /**
     * Find all users with names
     * @return ArrayList of Users
     */
    @Override
    public ArrayList<User> findUserWithNames() throws DaoException {
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(FIND_ALL_WITH_NAMES)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                User user = new User();
                ClientDetail clientDetail = new ClientDetail();
                user.setUserId(resultSet.getInt(1));
                user.setAccessLevel(resultSet.getString(2));
                clientDetail.setName(resultSet.getString(3));
                clientDetail.setLastname(resultSet.getString(4));
                user.setClientDetail(clientDetail);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception on FindUserWithNames",e);
        }
        return users;
    }

    /**
     * Find users by access level
     * @param accessLevel
     * @return ArrayList of Users
     */
    @Override
    public ArrayList<User> findUsersByAccessLevel(String accessLevel) throws DaoException {
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ACCESS_PSTM)) {
            preparedStatement.setString(1, accessLevel);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            fillUsers(users, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Exception on FindUsersByAccessLevel",e);
        }
        return users;
    }

    private void fillUsers(ArrayList<User> users, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            User user = new User();
            user.setUserId(resultSet.getInt(1));
            user.setLogin(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setAccessLevel(resultSet.getString(4));
            users.add(user);
        }
    }

    private void fillUser(User user, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            user.setUserId(resultSet.getInt(1));
            user.setLogin(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setAccessLevel(resultSet.getString(4));
        }
    }
}

