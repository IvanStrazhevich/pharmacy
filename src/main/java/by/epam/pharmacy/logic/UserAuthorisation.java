package by.epam.pharmacy.logic;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.dao.impl.UserDao;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.LogicException;
import by.epam.pharmacy.service.AttributeEnum;
import by.epam.pharmacy.service.PagesEnum;
import by.epam.pharmacy.util.ResourceManager;

import java.util.ArrayList;

public class UserAuthorisation {
    private Encodable encoder = new SHAConverter();
    private static final String MESSAGE = "message.wrongloginAndPass";
    private static final String MESSAGE_SUCCESS = "message.loginOk";

    private ArrayList<User> getUsersList() throws DaoException {
        ArrayList<User> users = new ArrayList<>();
        try (UserDao userDao = new UserDao()) {
            users = userDao.findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return users;
    }

    private boolean checkLogin(String login, String password) throws LogicException {
        Boolean logged = false;
        try {
            ArrayList<User> list = getUsersList();
            String shaLogin = encoder.encode(login);
            String shaPassword = encoder.encode(password);
            for (int i = 0; i < list.size() && !logged; i++) {
                User user = list.get(i);
                String loginDB = user.getLogin();
                String passDB = user.getPassword();
                if (shaLogin.equals(loginDB) && shaPassword.equals(passDB)) {
                    logged = true;
                }
            }
        } catch (DaoException e) {
            throw new LogicException("DaoException", e);
        }
        return logged;
    }
    public String checkUserAccessLevel(String login) throws LogicException {
        try (UserDao userDao = new UserDao()){
            String shaLogin = encoder.encode(login);
            User user = userDao.findUserByLogin(shaLogin);
            return user.getAccessLevel();
        } catch (DaoException e) {
            throw new LogicException("DaoException", e);
        }
    }
    public String logIn(SessionRequestContent sessionRequestContent) throws LogicException{
        String page = null;
        String login = sessionRequestContent.getRequestParameters().get(AttributeEnum.LOGIN.getAttribute());
        String password = sessionRequestContent.getRequestParameters().get(AttributeEnum.PASSWORD.getAttribute());
            if (checkLogin(login, password)) {
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.LOGGED.getAttribute(), AttributeEnum.LOGGED.getAttribute());
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.ACCESS_LEVEL.getAttribute(), checkUserAccessLevel(login));
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.LOGIN.getAttribute(), login);
                sessionRequestContent.getRequestAttributes().put(AttributeEnum.GREETING.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_SUCCESS));
                page = PagesEnum.WELCOME_PAGE.getPage();
            } else {
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.NEED_REGISTER.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE));
                page = PagesEnum.LOGIN_PAGE.getPage();
            }
        return page;
    }
}