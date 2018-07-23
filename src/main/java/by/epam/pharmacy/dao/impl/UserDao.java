package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.dao.AbstractUserDao;
import by.epam.pharmacy.entity.ClientDetail;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implementation of AbstractDao for type User
 */
public class UserDao extends AbstractDaoImpl<User> implements AbstractUserDao<User> {
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_PSTM = "select  user_id, user_login, user_password, user_access_level from user";
    private static final String SELECT_BY_ID_PSTM = "select user_id, user_login, user_password, user_access_level from user where user_id = ?";
    private static final String SELECT_BY_LOGIN_PSTM = "select user_id, user_login, user_password, user_access_level from user where user_login = ?";
    private static final String INSERT_PSTM = "insert into user(user_login, user_password, user_access_level) values(?,?,?)";
    private static final String DELETE_PSTM = "delete from user where user_id = ?";
    private static final String UPDATE_PSTM = "update user set user_login = ?, user_password = ?, user_access_level = ? where user_id = ?";
    private static final String FIND_ALL_WITH_NAMES
            = "select u.user_id, u.user_access_level, cd.cl_name, cd.cl_lastname  FROM client_detail as cd LEFT OUTER JOIN `user` as u ON cd.user_id = u.user_id";
    private ProxyConnection proxyConnection;

    public UserDao() throws DaoException {
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


    /**
     * Finds User by its id
     *
     * @param id type Integer
     * @return User
     * @throws DaoException
     */
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

    /**
     * Finds User by its login
     *
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
            resultSet.next();
            user.setUserId(resultSet.getInt(1));
            user.setLogin(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setAccessLevel(resultSet.getString(4));
        } catch (SQLException e) {
            throw new DaoException("Exception on find by login", e);
        }
        return user;
    }

    /**
     * @param id of type Integer
     * @return true if statement delete item successfully
     * @throws DaoException
     */
    @Override
    public boolean deleteById(Integer id) throws DaoException {
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
            throw new DaoException("Exception on deleteById", e);
        }
        return success;
    }

    /**
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
            throw new DaoException("Exception on create", e);
        }
        return success;
    }

    /**
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
            throw new DaoException("Exception on update", e);
        }
        return success;
    }

    @Override
    public HashMap<User, ClientDetail> findUserWithNames() throws DaoException {
        HashMap<User, ClientDetail> map = new HashMap<>();
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
                map.put(user, clientDetail);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return map;
    }
}
