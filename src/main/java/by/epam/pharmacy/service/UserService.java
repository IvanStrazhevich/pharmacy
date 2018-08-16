package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.ServiceException;

public interface UserService {
    /**
     * Create user record
     *
     * @param content
     * @return true if succeed
     */
    boolean createUser(SessionRequestContent content) throws ServiceException;

    /**
     * Check if user already exists
     *
     * @param content
     * @return true is so false if not
     */
    boolean checkUserExist(SessionRequestContent content) throws ServiceException;

    /**
     * Find user access level by login
     *
     * @param login
     * @return access level record
     */
    String checkUserAccessLevel(String login) throws ServiceException;

    /**
     * Check if user with such login exist and authorize
     *
     * @param content
     */
    boolean checkLogin(SessionRequestContent content) throws ServiceException;

    /**
     * Show users with names and access
     *
     * @param content
     */
    void showUsersAndAccess(SessionRequestContent content) throws ServiceException;

    /**
     * Show user access level
     *
     * @param content
     */
    void showUserAccessLvl(SessionRequestContent content) throws ServiceException;

    /**
     * Save access level
     *
     * @param content
     */
    void saveAccessLvl(SessionRequestContent content) throws ServiceException;

    /**
     * Find default doctor for recipe
     *
     * @return User
     * @throws ServiceException
     */
    User findDefaultDoctor() throws ServiceException;

    /**
     * Invalidate session clears all session attributes
     *
     */
    void invalidateSession(SessionRequestContent content) throws ServiceException;

}

