package by.epam.pharmacy.service;

import by.epam.pharmacy.dao.impl.AuthentificationDao;
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

    private boolean createUser(User user) throws DaoException {
        boolean flag = false;
        try (AuthentificationDao authentificationDao = new AuthentificationDao()) {
            flag = authentificationDao.create(user);

        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return flag;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        languageSwitcher.langSwitch(request);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
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
                if (request.getSession().getAttribute("Login") == null) {
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
                User user = new User();
                user.setAuLogin(shalogin);
                user.setAuPassword(shaPassword);
                if (createUser(user)) {
                    request.getSession().setAttribute(AttributeEnum.LOGGED.getValue(), AttributeEnum.LANG.getValue());
                    request.setAttribute(AttributeEnum.USER_REGISTERED.getValue(), ResourceManager.INSTANCE.getString(MESSAGE_USER_REGISTERED));
                    page=PagesEnum.WELCOME_PAGE.getValue();
                } else {
                    request.setAttribute(AttributeEnum.USER_NOT_REGISTERED.getValue(), ResourceManager.INSTANCE.getString(MESSAGE_USER_NOT_REGISTERED));
                    page=PagesEnum.REGISTER_PAGE.getValue();
                }
            }
        } catch (EncriptingException | DaoException e) {
            logger.error(e);
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
