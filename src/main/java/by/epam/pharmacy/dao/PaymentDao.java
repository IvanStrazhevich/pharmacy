package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

import java.math.BigDecimal;

public interface PaymentDao<T> extends AbstractDao<T> {

    /**
     * Find payment details for order by param
     * @param orderId id of order type int
     * @return T of typePayment
     * @throws DaoException
     */
    T findPaymentByOrderId(int orderId) throws DaoException;

    /**
     * Make payment transaction
     * @param accountDebit sum to pay from debit amount
     * @param accountCredit sum to pay as credit
     * @param userId user payed
     * @return true if payment succeed
     * @throws DaoException
     */
    boolean makePayment(BigDecimal accountDebit, BigDecimal accountCredit, int userId) throws DaoException;

    boolean deletePaymentByOrderId(int orderId) throws DaoException;

}
