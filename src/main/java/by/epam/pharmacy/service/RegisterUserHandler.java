package by.epam.pharmacy.service;

import by.epam.pharmacy.util.SessionRequestContent;
import by.epam.pharmacy.dao.impl.UserDao;
import by.epam.pharmacy.entity.AccessLevel;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.EncriptingException;
import by.epam.pharmacy.util.Encodable;
import by.epam.pharmacy.util.ResourceManager;
import by.epam.pharmacy.util.SHAConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import java.util.ArrayList;

public class RegisterUserHandler implements RequestHandler<SessionRequestContent> {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE_USER_EXIST = "message.userExist";
    private static final String MESSAGE_USER_REGISTERED = "message.userRegistered";
    private static final String MESSAGE_USER_NOT_REGISTERED = "message.userNotRegistered";
    private Encodable encoder = new SHAConverter();

    private boolean createUser(User user) throws DaoException {
        try (UserDao userDao = new UserDao()) {
            if (null == user.getAccessLevel()) {
                user.setAccessLevel(AccessLevel.CLIENT.getValue());
            }
            return userDao.create(user);
        }
    }

    private ArrayList<User> getUserslist() throws DaoException {
        ArrayList<User> users = new ArrayList<>();
        try (UserDao userDao = new UserDao()) {
            users = userDao.findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws ServletException {
        String login = sessionRequestContent.getRequestParameters().get(AttributeEnum.LOGIN.getAttribute());
        String password = sessionRequestContent.getRequestParameters().get(AttributeEnum.PASSWORD.getAttribute());
        String shalogin = null;
        String shaPassword = null;
        String page = null;
        try {
            ArrayList<User> list = new ArrayList();
            list = getUserslist();
            shalogin = encoder.encode(login);
            shaPassword = encoder.encode(password);
            boolean flag = false;

            for (User user : list) {
                if (sessionRequestContent.getSessionAttributes().get(AttributeEnum.LOGIN.getAttribute()) == null) {
                    String loginDB = user.getLogin();
                    logger.info(loginDB + '\n' + shalogin);
                    if (shalogin.equals(loginDB)) {
                        sessionRequestContent.getRequestAttributes().put(AttributeEnum.USER_EXIST.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_USER_EXIST));
                        page = PagesEnum.REGISTER_PAGE.getPage();
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                User user = new User();
                user.setLogin(shalogin);
                user.setPassword(shaPassword);
                if (createUser(user)) {
                    logger.debug("registered");
                    sessionRequestContent.getSessionAttributes().put(AttributeEnum.LOGGED.getAttribute(), AttributeEnum.LANG.getAttribute());
                    sessionRequestContent.getRequestAttributes().put(AttributeEnum.USER_REGISTERED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_USER_REGISTERED));
                    sessionRequestContent.getSessionAttributes().put(AttributeEnum.ACCESS_LEVEL.getAttribute(), user.getAccessLevel());
                    sessionRequestContent.getSessionAttributes().put(AttributeEnum.LOGIN.getAttribute(), login);
                    page = PagesEnum.WELCOME_PAGE.getPage();
                } else {
                    logger.debug("not registered");
                    sessionRequestContent.getRequestAttributes().put(AttributeEnum.USER_NOT_REGISTERED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_USER_NOT_REGISTERED));
                    page = PagesEnum.REGISTER_PAGE.getPage();
                }
            }
        } catch (EncriptingException | DaoException e) {
            logger.error(e.getCause());
            throw new ServletException(e);
        }
        return page;
    }

    public void setEncoder(Encodable encoder) {
        this.encoder = encoder;
    }
}
