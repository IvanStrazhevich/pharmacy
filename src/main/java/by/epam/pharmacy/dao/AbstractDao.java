package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

import java.util.List;


public interface AbstractDao<T> extends AutoCloseable {
    Integer findLastInsertId() throws DaoException;

    List<T> findAll() throws DaoException;

    T findEntityById(Integer id) throws DaoException;

    boolean deleteById(Integer id, String statement) throws DaoException;

    boolean deleteById(Integer id) throws DaoException;

    boolean delete(T entity) throws DaoException;

    boolean create(T entity) throws DaoException;

    boolean update(T entity) throws DaoException;

    void close() throws DaoException;
}

