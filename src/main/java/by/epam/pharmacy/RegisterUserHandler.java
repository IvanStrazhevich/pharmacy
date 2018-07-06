package by.epam.pharmacy;

import by.epam.pharmacy.dao.impl.UserDao;
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
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class RegisterUserHandler implements RequestHandler {
    private static final String MESSAGE_USER_EXIST = "message.userExist";
    private static final String MESSAGE_USER_REGISTERED = "message.userRegistered";
    private static final String MESSAGE_USER_NOT_REGISTERED = "message.userNotRegistered";
    private static Logger logger = LogManager.getLogger();
    private SHAConverter shaConverter = new SHAConverter();

    private boolean createUser(User user) throws DaoException {
        boolean flag = false;
        try (UserDao userDao = new UserDao()) {
            flag = userDao.create(user);

        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return flag;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResourceManager.INSTANCE.changeResource(new Locale(Config.FMT_LOCALE));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String shalogin = null;
        String shaPassword = null;
        String page = null;
        try {
            ArrayList<User> list = new ArrayList();
            list = getUserslist();
            shalogin = shaConverter.convertToSHA1(login);
            shaPassword = shaConverter.convertToSHA1(password);
            boolean flag = false;

            for (User user : list) {
                if (request.getSession().getAttribute("Login") == null) {
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
                User user = new User();
                user.setLogin(shalogin);
                user.setPassword(shaPassword);
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
}
