package by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;

import java.util.ArrayList;

public interface AbstractUserDao<T> extends AbstractDao<T> {

    T findUserByLogin(String login) throws DaoException;
    ArrayList<User> findUserWithNames() throws DaoException;
    ArrayList<User> findUsersByAccessLevel(String accessLevel) throws DaoException;
}
