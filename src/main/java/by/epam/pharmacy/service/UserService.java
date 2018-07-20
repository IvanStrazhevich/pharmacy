package by.epam.pharmacy.service;

import by.epam.pharmacy.exception.ServiceException;

public interface UserService {
    boolean createUser(String login, String password) throws ServiceException;
    boolean checkUserExist(String login) throws ServiceException;
    String checkUserAccessLevel(String login) throws ServiceException;
    boolean checkLogin(String login, String password) throws ServiceException;
}
