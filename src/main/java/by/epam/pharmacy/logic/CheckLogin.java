package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.impl.UserDao;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.EncriptingException;
import by.epam.pharmacy.exception.PharmacyServletException;
import by.epam.pharmacy.util.Encodable;
import by.epam.pharmacy.util.SHAConverter;

import java.util.ArrayList;

public class CheckLogin {
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

    public boolean checkLogin(String login, String password) throws PharmacyServletException {
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
            throw new PharmacyServletException("DaoException", e);
        } catch (EncriptingException e) {
            throw new PharmacyServletException("Encription exception", e);
        }
        return logged;
    }
    public String checkUserAccessLevel(String login) throws PharmacyServletException {
        try (UserDao userDao = new UserDao()){
            String shaLogin = encoder.encode(login);
            User user = userDao.findUserByLogin(shaLogin);
            return user.getAccessLevel();
        } catch (DaoException e) {
            throw new PharmacyServletException("DaoException", e);
        } catch (EncriptingException e) {
            throw new PharmacyServletException("Encription exception", e);
        }
    }
}