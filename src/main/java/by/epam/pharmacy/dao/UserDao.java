package by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;

import java.util.ArrayList;

public interface UserDao<T> extends AbstractDao<T> {

    /**
     * 
     * @param login 
     */
    T findUserByLogin(String login) throws DaoException;
    /**
     * 
     */
    ArrayList<User> findUserWithNames() throws DaoException;
    /**
     * 
     * @param accessLevel 
     */
    ArrayList<User> findUsersByAccessLevel(String accessLevel) throws DaoException;
}

