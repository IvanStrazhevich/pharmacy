package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

public interface AbstractUserDao<T> extends AbstractDao<T> {

    T findUserByLogin(String login) throws DaoException;
}
