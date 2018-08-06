package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

import java.math.BigDecimal;

public interface PaymentDao<T> extends AbstractDao<T> {
    T findPaymentByOrderId(int orderId) throws DaoException;

    boolean deletePaymentByOrderId(int orderId) throws DaoException;

    boolean makePayment(BigDecimal accountDebit, BigDecimal accountCredit, int userId) throws DaoException;

}
