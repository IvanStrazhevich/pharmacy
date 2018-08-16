package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

public interface OrderDao<T> extends AbstractDao<T>{
    /**
     * Find Order for current client that is not payed yet
     * @param id is a client id of type int
     * @return T of type Order record
     */
    T findCurrentOrderByUserId(int id) throws DaoException;
    /**
     * Find Order and join all info for this Order's medicines, recipes, sum, client
     * @param orderId
     * @return T of type Order record
     */
    T showOrderWithMedicineByOrderId(int orderId) throws DaoException;
}

