package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

import java.util.ArrayList;


public interface AbstractDao<T> extends AutoCloseable {
    /**
     * 
     */
    Integer findLastInsertId() throws DaoException;

    /**
     * 
     */
    ArrayList<T> findAll() throws DaoException;

    /**
     *
     * @param id
     */
    T findEntityById(int id) throws DaoException;

    /**
     *  @param id
     * @param statement
     */
    boolean deleteById(int id, String statement) throws DaoException;

    /**
     *
     * @param id
     */
    boolean deleteById(int id) throws DaoException;

    /**
     * 
     * @param entity 
     */
    boolean delete(T entity) throws DaoException;

    /**
     * 
     * @param entity 
     */
    boolean create(T entity) throws DaoException;

    /**
     * 
     * @param entity 
     */
    boolean update(T entity) throws DaoException;

    /**
     * 
     */
    void close();
}


