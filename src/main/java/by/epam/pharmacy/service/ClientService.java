package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface ClientService {
    /**
     * Create or update Client Detail
     *
     * @param content from request
     */
    void createOrUpdateClientDetails(SessionRequestContent content) throws ServiceException;

    /**
     * Find client detail record
     *
     * @param content from request
     */
    void findClientDetail(SessionRequestContent content) throws ServiceException;

    /**
     * Validate incoming params
     *
     * @param content from request
     * @return boolean
     * @throws ServiceException
     */
    boolean validateForCreateClientDetail(SessionRequestContent content) throws ServiceException;


    /**
     * Upload photo to application
     *
     * @param request
     * @throws ServiceException
     */
    void uploadPhoto(HttpServletRequest request) throws ServiceException;


    /**
     * Find client detail record on params from request
     * @param request
     */
    void findClientDetailFromPhotoUpload(HttpServletRequest request) throws ServiceException;

    public int findClientId(String login) throws ServiceException;
}

