package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface ClientService {
    void createClientDetail(SessionRequestContent content) throws ServiceException;
    void findClientDetail(SessionRequestContent content) throws ServiceException;
}
