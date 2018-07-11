package by.epam.pharmacy.service;

import by.epam.pharmacy.dao.impl.UserDao;
import by.epam.pharmacy.dao.impl.ClientDao;
import by.epam.pharmacy.entity.AccessLevel;
import by.epam.pharmacy.entity.Client;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.EncriptingException;
import by.epam.pharmacy.web.AttributeEnum;
import by.epam.pharmacy.web.PagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class RegisterUserHandler implements RequestHandler {
    private static final String MESSAGE_USER_EXIST = "message.userExist";
    private static final String MESSAGE_USER_REGISTERED = "message.userRegistered";
    private static final String MESSAGE_USER_NOT_REGISTERED = "message.userNotRegistered";
    private static Logger logger = LogManager.getLogger();
    private Encodable encoder = new SHAConverter();
    private LanguageSwitchable languageSwitcher = new LanguageSwitcher();

    private boolean createUser(Client client, User user) throws DaoException {
        try (UserDao userDao = new UserDao();
             ClientDao clientDao = new ClientDao()) {
            if(null==user.getAccessLevel()){
                user.setAccessLevel(AccessLevel.CLIENT.getValue());
            }
            userDao.create(user);
            client.setUserId(user.getLogin());
            return clientDao.create(client);
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        languageSwitcher.langSwitch(request);
        String login = request.getParameter(AttributeEnum.LOGIN.getValue());
        String password = request.getParameter(AttributeEnum.PASSWORD.getValue());
        String name = request.getParameter(AttributeEnum.NAME.getValue());
        String lastname = request.getParameter(AttributeEnum.LASTNAME.getValue());
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
                if (request.getSession().getAttribute(AttributeEnum.LOGIN.getValue()) == null) {
                    String loginDB = user.getLogin();
                    if (shalogin.equals(loginDB)) {
                        request.setAttribute(AttributeEnum.USER_EXIST.getValue(), ResourceManager.INSTANCE.getString(MESSAGE_USER_EXIST));
                        page = PagesEnum.REGISTER_PAGE.getValue();
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                Client client = new Client();
                client.setName(name);
                client.setLastname(lastname);
                User user = new User();
                user.setLogin(shalogin);
                user.setPassword(shaPassword);
                if (createUser(client, user)) {
                    logger.debug("registered");
                    request.getSession().setAttribute(AttributeEnum.LOGGED.getValue(), AttributeEnum.LANG.getValue());
                    request.setAttribute(AttributeEnum.USER_REGISTERED.getValue(), ResourceManager.INSTANCE.getString(MESSAGE_USER_REGISTERED));
                    request.getSession().setAttribute(AttributeEnum.ACCESS_LEVEL.getValue(), user.getAccessLevel());
                    request.getSession().setAttribute(AttributeEnum.LOGIN.getValue(), login);
                    page = PagesEnum.WELCOME_PAGE.getValue();
                } else {
                    logger.debug("not registered");
                    request.setAttribute(AttributeEnum.USER_NOT_REGISTERED.getValue(), ResourceManager.INSTANCE.getString(MESSAGE_USER_NOT_REGISTERED));
                    page = PagesEnum.REGISTER_PAGE.getValue();
                }
            }
        } catch (EncriptingException | DaoException e) {
            logger.error(e.getCause());
            throw new ServletException(e);
        }
        return page;
    }

    public void setLanguageSwitcher(LanguageSwitchable languageSwitcher) {
        this.languageSwitcher = languageSwitcher;
    }

    public void setEncoder(Encodable encoder) {
        this.encoder = encoder;
    }
}
