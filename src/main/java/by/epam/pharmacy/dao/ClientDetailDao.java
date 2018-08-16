package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

public interface ClientDetailDao<T> extends AbstractDao<T> {

    /**
     * Updates photo uri for entity
     * @param entity
     * @return true if photo updated false if exception thrown
     * @throws DaoException
     */
    boolean updatePhoto(T entity) throws DaoException;
}
