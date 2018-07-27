package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface ClientService {
    void createClientDetail(SessionRequestContent sessionRequestContent) throws ServiceException;
    void findClientDetail(SessionRequestContent sessionRequestContent) throws ServiceException;
}
