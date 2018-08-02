package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.ServiceException;

public interface UserService {
    /**
     *
     * @param content
     */
    boolean createUser(SessionRequestContent content) throws ServiceException;
    /**
     *
     * @param content
     */
    boolean checkUserExist(SessionRequestContent content) throws ServiceException;
    /**
     * 
     * @param login 
     */
    String checkUserAccessLevel(String login) throws ServiceException;
    /**
     *
     * @param content
     */
    boolean checkLogin(SessionRequestContent content) throws ServiceException;
    /**
     * 
     * @param content 
     */
    void showUsersAndAccess(SessionRequestContent content) throws ServiceException;
    /**
     * 
     * @param content 
     */
    void showUserAccessLvl(SessionRequestContent content) throws ServiceException;
    /**
     * 
     * @param content 
     */
    void saveAccessLvl(SessionRequestContent content) throws ServiceException;
    /**
     * 
     */
    User findDefaultDoctor() throws ServiceException;

    void invalidateSession(SessionRequestContent content) throws ServiceException;
}

