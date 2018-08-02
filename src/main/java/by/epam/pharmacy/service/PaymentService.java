package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface PaymentService {
    void createPayment(SessionRequestContent content) throws ServiceException;
    void makePayment(SessionRequestContent content);
}
