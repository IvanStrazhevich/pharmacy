package by.epam.pharmacy.service.impl;

import by.epam.pharmacy.dao.impl.UserDao;
import by.epam.pharmacy.entity.AccessLevel;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.LogicException;
import by.epam.pharmacy.service.Encodable;
import by.epam.pharmacy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {
    private static Logger logger = LogManager.getLogger();
    private Encodable encoder = new SHAConverter();

    private ArrayList<User> getUsersList() throws DaoException {
        ArrayList<User> users = new ArrayList<>();
        try (UserDao userDao = new UserDao()) {
            users = userDao.findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return users;
    }

    public boolean checkLogin(String login, String password) throws LogicException {
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
        try (UserDao userDao = new UserDao()) {
            String shaLogin = encoder.encode(login);
            User user = userDao.findUserByLogin(shaLogin);
            return user.getAccessLevel();
        } catch (DaoException e) {
            throw new LogicException("DaoException", e);
        }
    }


    public boolean checkUserExist(String login) throws LogicException {
        boolean exist = false;
        ArrayList<User> list = new ArrayList();
        try {
            list = getUsersList();

            String shalogin = encoder.encode(login);
            for (User user : list) {
                String loginDB = user.getLogin();
                logger.info(loginDB + '\n' + shalogin);
                if (shalogin.equals(loginDB)) {
                    exist = true;
                }
            }
        } catch (DaoException e) {
            throw new LogicException("DaoException", e);
        }
        return exist;
    }

    public boolean createUser(String login, String password) throws LogicException {
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
}