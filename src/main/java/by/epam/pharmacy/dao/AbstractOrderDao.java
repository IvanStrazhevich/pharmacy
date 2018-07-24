package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

public interface AbstractOrderDao<T> extends AbstractDao<T>{
    T findCurrentOrderByUserId(int id) throws DaoException;
}
