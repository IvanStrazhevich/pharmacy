package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.ServiceException;

public interface UserService {
    boolean createUser(String login, String password) throws ServiceException;
    boolean checkUserExist(String login) throws ServiceException;
    String checkUserAccessLevel(String login) throws ServiceException;
    boolean checkLogin(String login, String password) throws ServiceException;
    void showUsersAndAccess(SessionRequestContent content) throws ServiceException;
    void showUserAccessLvl(SessionRequestContent content) throws ServiceException;
    void saveAccessLvl(SessionRequestContent content) throws ServiceException;
    User findDefaultDoctor() throws ServiceException;
}
