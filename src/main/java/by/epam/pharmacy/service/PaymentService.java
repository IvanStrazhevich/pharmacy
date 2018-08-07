package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface PaymentService {
    boolean createOrUpdatePayment(SessionRequestContent content) throws ServiceException;

    void proceedToPayment(SessionRequestContent content) throws ServiceException;

    void makePayment(SessionRequestContent content) throws ServiceException;
}
