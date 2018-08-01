package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

public interface PaymentDao<T> extends AbstractDao<T> {
    T findPaymentByOrderId(int orderId) throws DaoException;

    boolean DeletePaymentByOrderId(int orderId) throws DaoException;

    boolean makePayment();

}
