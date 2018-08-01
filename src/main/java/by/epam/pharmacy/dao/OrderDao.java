package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

public interface OrderDao<T> extends AbstractDao<T>{
    /**
     * 
     * @param id 
     */
    T findCurrentOrderByUserId(int id) throws DaoException;
    /**
     * 
     * @param orderId 
     */
    T showOrderWithMedicineByOrderId(Integer orderId) throws DaoException;
}

