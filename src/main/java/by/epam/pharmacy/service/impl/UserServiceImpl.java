package by.epam.pharmacy.service.impl;

import by.epam.pharmacy.command.AttributeEnum;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.dao.impl.ClientDetailDao;
import by.epam.pharmacy.dao.impl.UserDao;
import by.epam.pharmacy.entity.AccessLevel;
import by.epam.pharmacy.entity.ClientDetail;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.Encodable;
import by.epam.pharmacy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {
    private static Logger logger = LogManager.getLogger();
    private Encodable encoder = new ShaConverter();

    public boolean checkLogin(String login, String password) throws ServiceException {
        Boolean logged = false;
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
        return logged;
    }

    public String checkUserAccessLevel(String login) throws ServiceException {
        try (UserDao userDao = new UserDao()) {
            String shaLogin = encoder.encode(login);
            User user = userDao.findUserByLogin(shaLogin);
            return user.getAccessLevel();
        } catch (DaoException e) {
            throw new ServiceException("DaoException", e);
        }
    }


    public boolean checkUserExist(String login) throws ServiceException {
        boolean exist = false;
        ArrayList<User> list = new ArrayList();
        list = getUsersList();

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

    public boolean createUser(String login, String password) throws ServiceException {
        String shalogin = null;
        String shaPassword = null;
        try (UserDao userDao = new UserDao();
             ClientDetailDao clientDetailDao = new ClientDetailDao()) {
            shalogin = encoder.encode(login);
            shaPassword = encoder.encode(password);
            User user = new User();
            user.setLogin(shalogin);
            user.setPassword(shaPassword);
            user.setAccessLevel(AccessLevel.CLIENT.getLevel());
            userDao.create(user);
            int userId = userDao.findLastInsertId();
            ClientDetail clientDetail = new ClientDetail();
            clientDetail.setClientId(userId);
            return clientDetailDao.create(clientDetail);
        } catch (DaoException e) {
            throw new ServiceException("DaoException", e);
        }
    }

    public void showUsersAndAccess(SessionRequestContent sessionRequestContent) throws ServiceException {
        try (UserDao userDao = new UserDao()) {
            ArrayList<User> users = userDao.findUserWithNames();
            logger.info(users);
            sessionRequestContent.getRequestAttributes().put(AttributeEnum.USERS.getAttribute(), users);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void showUserAccessLvl(SessionRequestContent sessionRequestContent) throws ServiceException {
        int id = Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.USER_ID.getAttribute()));
        logger.info(id);
        try (UserDao userDao = new UserDao();
             ClientDetailDao clientDetailDao = new ClientDetailDao()) {
            User user = userDao.findEntityById(id);
            ClientDetail clientDetail = clientDetailDao.findEntityById(id);
            user.setClientDetail(clientDetail);
            logger.info(user);
            sessionRequestContent.getRequestAttributes().put(AttributeEnum.USER.getAttribute(), user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveAccessLvl(SessionRequestContent sessionRequestContent) throws ServiceException {
        int id = Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.USER_ID.getAttribute()));
        logger.info(id);
        try (UserDao userDao = new UserDao()) {
            User user = userDao.findEntityById(id);
            logger.info("from base: " + user);
            user.setAccessLevel(sessionRequestContent.getRequestParameters().get(AttributeEnum.ACCESS_LEVEL.getAttribute()));
            userDao.update(user);
            logger.info(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public User findDefaultDoctor() throws ServiceException {
        User user = new User();
        try (UserDao userDao = new UserDao()) {
            ArrayList<User> doctors = userDao.findUsersByAccessLevel(AccessLevel.DOCTOR.getLevel());
            user = doctors.get(0);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    private ArrayList<User> getUsersList() throws ServiceException {
        ArrayList<User> users = new ArrayList<>();
        try (UserDao userDao = new UserDao()) {
            users = userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }


    public void setEncoder(Encodable encoder) {
        this.encoder = encoder;
    }
}