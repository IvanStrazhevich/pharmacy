package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

import java.util.ArrayList;


public interface AbstractDao<T> extends AutoCloseable {
    /**
     * Finds las insert id
     * @return id of int type
     */
    int findLastInsertId() throws DaoException;

    /**
     * Find all items of type T
     * @return ArrayList of T type elements
     */
    ArrayList<T> findAll() throws DaoException;

    /**
     * @return T type element according on id value
     * @param id int type
     */
    T findEntityById(int id) throws DaoException;

    /**
     * @param id of type int
     * @param statement prepared statement type
     * @return true if deleted successfully false if not
     */
    boolean deleteById(int id, String statement) throws DaoException;

    /**
     * @param id of type int
     * @return true if deleted successfully false if not
     */
    boolean deleteById(int id) throws DaoException;

    /**
     * Delete element if it is
     * @return true if deleted successfully false if not
     * @param entity 
     */
    boolean delete(T entity) throws DaoException;

    /**
     * Persist element in Database
     * @param entity
     * @return true if item created successfully false if not
     */
    boolean create(T entity) throws DaoException;

    /**
     * Update element data in database
     * @param entity
     * @return true if item updated successfully false if not
     */
    boolean update(T entity) throws DaoException;

    /**
     * close resources
     */
    void close();
}


