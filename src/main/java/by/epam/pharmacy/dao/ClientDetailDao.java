package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

public interface ClientDetailDao<T> extends AbstractDao<T> {
    boolean updatePhoto(T entity) throws DaoException;
}
