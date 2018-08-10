package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

/**
 *
 */
public interface PaymentService {
    /**
     * @param content
     * @throws ServiceException
     */
    void createOrUpdatePayment(SessionRequestContent content) throws ServiceException;

    /**
     * @param content
     * @throws ServiceException
     */
    void proceedToPayment(SessionRequestContent content) throws ServiceException;

    /**
     * @param content
     * @throws ServiceException
     */
    void makePayment(SessionRequestContent content) throws ServiceException;

    /**
     * @param content
     * @return
     * @throws ServiceException
     */
    boolean validateForCreateOrUpdatePayment(SessionRequestContent content) throws ServiceException;
}
