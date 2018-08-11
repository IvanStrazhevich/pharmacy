package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

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

    void downloadPhoto(HttpServletRequest request) throws ServiceException;

    void findClientDetailFromPhotoUpload(HttpServletRequest request) throws ServiceException;
    public int findClientId(String login) throws ServiceException;
}

