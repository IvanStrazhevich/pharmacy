package by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.ClientDetail;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;

import java.util.HashMap;

public interface AbstractUserDao<T> extends AbstractDao<T> {

    T findUserByLogin(String login) throws DaoException;
    HashMap<User, ClientDetail> findUserWithNames() throws DaoException;
}
