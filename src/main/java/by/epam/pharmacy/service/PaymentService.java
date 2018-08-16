package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

/**
 *
 */
public interface PaymentService {
    /**
     * Create or Update Payment record
     *
     * @param content
     * @throws ServiceException
     */
    void createOrUpdatePayment(SessionRequestContent content) throws ServiceException;

    /**
     * Prepare payment info
     *
     * @param content
     * @throws ServiceException
     */
    void proceedToPayment(SessionRequestContent content) throws ServiceException;

    /**
     * Make transaction payment
     *
     * @param content
     * @throws ServiceException
     */
    void makePayment(SessionRequestContent content) throws ServiceException;

    /**
     * Validate incoming params
     *
     * @param content
     * @return
     * @throws ServiceException
     */
    boolean validateForCreateOrUpdatePayment(SessionRequestContent content) throws ServiceException;
}
