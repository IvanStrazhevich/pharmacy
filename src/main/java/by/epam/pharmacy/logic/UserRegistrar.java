package by.epam.pharmacy.logic;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.dao.impl.UserDao;
import by.epam.pharmacy.entity.AccessLevel;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.LogicException;
import by.epam.pharmacy.service.AttributeEnum;
import by.epam.pharmacy.service.PagesEnum;
import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class UserRegistrar {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE_USER_EXIST = "message.userExist";
    private static final String MESSAGE_USER_REGISTERED = "message.userRegistered";
    private static final String MESSAGE_USER_NOT_REGISTERED = "message.userNotRegistered";
    private UserAuthorisation authorisation = new UserAuthorisation();
    private Encodable encoder = new SHAConverter();

    private ArrayList<User> getUserslist() throws LogicException {
        ArrayList<User> users = new ArrayList<>();
        try (UserDao userDao = new UserDao()) {
            users = userDao.findAll();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return users;
    }

    private boolean checkUserExist(String login) throws LogicException {
        boolean exist = false;
        ArrayList<User> list = new ArrayList();
        list = getUserslist();
        String shalogin = encoder.encode(login);
        for (User user : list) {
            String loginDB = user.getLogin();
            logger.info(loginDB + '\n' + shalogin);
            if (shalogin.equals(loginDB)) {
                exist = true;
            }
        }
        return exist;
    }

    private boolean createUser(String login, String password) throws LogicException {
        String shalogin = null;
        String shaPassword = null;
        try (UserDao userDao = new UserDao()) {
            shalogin = encoder.encode(login);
            shaPassword = encoder.encode(password);
            User user = new User();
            user.setLogin(shalogin);
            user.setPassword(shaPassword);
            user.setAccessLevel(AccessLevel.CLIENT.getLevel());
            return userDao.create(user);
        } catch (DaoException e) {
            throw new LogicException("DaoException", e);
        }
    }

    public String registerUser(SessionRequestContent sessionRequestContent) throws LogicException {
        String login = sessionRequestContent.getRequestParameters().get(AttributeEnum.LOGIN.getAttribute());
        String password = sessionRequestContent.getRequestParameters().get(AttributeEnum.PASSWORD.getAttribute());
        String page = null;
        if (checkUserExist(login)) {
            sessionRequestContent.getRequestAttributes().put(AttributeEnum.USER_EXIST.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_USER_EXIST));
            page = PagesEnum.REGISTER_PAGE.getPage();
        } else if (createUser(login, password)) {
            logger.debug("registered");
            sessionRequestContent.getSessionAttributes().put(AttributeEnum.LOGGED.getAttribute(), AttributeEnum.LANG.getAttribute());
            sessionRequestContent.getRequestAttributes().put(AttributeEnum.USER_REGISTERED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_USER_REGISTERED));
            sessionRequestContent.getSessionAttributes().put(AttributeEnum.ACCESS_LEVEL.getAttribute(), authorisation.checkUserAccessLevel(login));
            sessionRequestContent.getSessionAttributes().put(AttributeEnum.LOGIN.getAttribute(), login);
            page = PagesEnum.WELCOME_PAGE.getPage();
        } else {
            logger.debug("not registered");
            sessionRequestContent.getRequestAttributes().put(AttributeEnum.USER_NOT_REGISTERED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_USER_NOT_REGISTERED));
            page = PagesEnum.REGISTER_PAGE.getPage();
        }
        return page;
    }
}
