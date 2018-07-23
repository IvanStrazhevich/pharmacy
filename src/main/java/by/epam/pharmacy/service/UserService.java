package by.epam.pharmacy.service;

import by.epam.pharmacy.controller.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface UserService {
    boolean createUser(String login, String password) throws ServiceException;
    boolean checkUserExist(String login) throws ServiceException;
    String checkUserAccessLevel(String login) throws ServiceException;
    boolean checkLogin(String login, String password) throws ServiceException;
    void showUsersAndAccess(SessionRequestContent sessionRequestContent) throws ServiceException;
    void showUserAccessLvl(SessionRequestContent sessionRequestContent) throws ServiceException;
    void saveAccessLvl(SessionRequestContent sessionRequestContent) throws ServiceException;
}
