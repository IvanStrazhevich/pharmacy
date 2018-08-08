package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface ClientService {
    /**
     *
     * @param content
     */
    boolean createClientDetail(SessionRequestContent content) throws ServiceException;
    /**
     * 
     * @param content 
     */
    void findClientDetail(SessionRequestContent content) throws ServiceException;
}

