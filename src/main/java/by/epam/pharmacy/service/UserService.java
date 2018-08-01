package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.ServiceException;

public interface UserService {
    /**
     * 
     * @param login 
     * @param password 
     */
    boolean createUser(String login, String password) throws ServiceException;
    /**
     * 
     * @param login 
     */
    boolean checkUserExist(String login) throws ServiceException;
    /**
     * 
     * @param login 
     */
    String checkUserAccessLevel(String login) throws ServiceException;
    /**
     * 
     * @param login 
     * @param password 
     */
    boolean checkLogin(String login, String password) throws ServiceException;
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
}

