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
import java.io.IOException;
import java.util.ArrayList;

public class CheckUserHandler implements RequestHandler {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.wrongloginAndPass";
    private static final String MESSAGE_SUCCESS = "message.loginOk";
    private Encodable encoder = new SHAConverter();
    private LanguageSwitchable languageSwitcher = new LanguageSwitcher();

    private ArrayList<User> getUsersList() throws DaoException {
        ArrayList<User> users = new ArrayList<>();
        try (AuthentificationDao authentificationDao = new AuthentificationDao()) {
            users = authentificationDao.findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        languageSwitcher.langSwitch(request);
        String login = request.getParameter(AttributeEnum.LOGIN.getValue());
        String password = request.getParameter(AttributeEnum.PASSWORD.getValue());
        Boolean logeed = false;
        try {
            ArrayList<User> list = new ArrayList();
            list = getUsersList();
            String shaLogin = encoder.encode(login);
            String shaPassword = encoder.encode(password);
            for (int i = 0; i < list.size() && !logeed; i++) {
                User user = list.get(i);
                if (request.getSession().getAttribute(AttributeEnum.LOGGED.getValue()) == null) {
                    String loginDB = user.getAuLogin();
                    String passDB = user.getAuPassword();
                    if (shaLogin.equals(loginDB) && shaPassword.equals(passDB)) {
                        request.getSession().setAttribute(AttributeEnum.LOGGED.getValue(), AttributeEnum.LOGGED.getValue());
                        request.getSession().setAttribute(AttributeEnum.ACCESS_LEVEL.getValue(), user.getAuAccessLevel());
                        request.getSession().setAttribute(AttributeEnum.LOGIN.getValue(), login);
                        logeed = true;
                        request.setAttribute(AttributeEnum.GREETING.getValue(), ResourceManager.INSTANCE.getString(MESSAGE_SUCCESS));
                        page = PagesEnum.WELCOME_PAGE.getValue();
                    } else {
                        request.getSession().setAttribute(AttributeEnum.NEED_REGISTER.getValue(), ResourceManager.INSTANCE.getString(MESSAGE));
                        page = PagesEnum.LOGIN_PAGE.getValue();
                    }
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

