package by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;

import java.util.ArrayList;

/**
 * @param <T>
 */
public interface UserDao<T> extends AbstractDao<T> {

    /**
     * Find User by login
     * @param login
     * @return User
     */
    T findUserByLogin(String login) throws DaoException;

    /**
     * Find all users with names
     * @return ArrayList of Users
     */
    ArrayList<User> findUserWithNames() throws DaoException;

    /**
     * Find users by access level
     * @param accessLevel
     * @return ArrayList of Users
     */
    ArrayList<User> findUsersByAccessLevel(String accessLevel) throws DaoException;
}

