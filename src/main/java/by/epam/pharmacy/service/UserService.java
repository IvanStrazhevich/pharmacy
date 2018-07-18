package by.epam.pharmacy.service;

import by.epam.pharmacy.exception.LogicException;

public interface UserService {
    boolean createUser(String login, String password) throws LogicException;
    boolean checkUserExist(String login) throws LogicException;
    String checkUserAccessLevel(String login) throws LogicException;
    boolean checkLogin(String login, String password) throws LogicException;
}
