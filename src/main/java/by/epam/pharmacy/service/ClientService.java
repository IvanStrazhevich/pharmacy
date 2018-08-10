package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface ClientService {
    /**
     * @param content
     */
    void createClientDetail(SessionRequestContent content) throws ServiceException;

    /**
     * @param content
     */
    void findClientDetail(SessionRequestContent content) throws ServiceException;

    /**
     * @param content
     * @return boolean
     * @throws ServiceException
     */
    boolean validateForCreateClientDetail(SessionRequestContent content) throws ServiceException;
}

