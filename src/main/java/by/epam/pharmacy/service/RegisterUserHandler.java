package by.epam.pharmacy.service;

import by.epam.pharmacy.dao.impl.AuthentificationDao;
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
        try (ClientDao clientDao = new ClientDao();
             AuthentificationDao authentificationDao = new AuthentificationDao()) {
            clientDao.create(client);
            client.setClientId(clientDao.findLastInsertId());
            user.setClientClId(client.getClientId());
            if(null==user.getAuAccessLevel()){
                user.setAuAccessLevel(AccessLevel.CLIENT.getValue());
            }
            return authentificationDao.create(user);
        }
    }

    private ArrayList<User> getUserslist() throws DaoException {
        ArrayList<User> users = new ArrayList<>();
        try (AuthentificationDao authentificationDao = new AuthentificationDao()) {
            users = authentificationDao.findAll();
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
        logger.info(name);
        String lastname = request.getParameter(AttributeEnum.LASTNAME.getValue());
        logger.info(lastname);
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
                    String loginDB = user.getAuLogin();
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
                user.setAuLogin(shalogin);
                user.setAuPassword(shaPassword);
                if (createUser(client, user)) {
                    logger.info("registered");
                    request.getSession().setAttribute(AttributeEnum.LOGGED.getValue(), AttributeEnum.LANG.getValue());
                    request.setAttribute(AttributeEnum.USER_REGISTERED.getValue(), ResourceManager.INSTANCE.getString(MESSAGE_USER_REGISTERED));
                    request.getSession().setAttribute(AttributeEnum.ACCESS_LEVEL.getValue(), user.getAuAccessLevel());
                    request.getSession().setAttribute(AttributeEnum.LOGIN.getValue(), login);
                    page = PagesEnum.WELCOME_PAGE.getValue();
                } else {
                    logger.info("not registered");
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
