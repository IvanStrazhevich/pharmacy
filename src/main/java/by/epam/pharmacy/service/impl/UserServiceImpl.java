package by.epam.pharmacy.service.impl;

import by.epam.pharmacy.command.AttributeName;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.dao.impl.ClientDetailDaoImpl;
import by.epam.pharmacy.dao.impl.UserDaoImpl;
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

/**
 * 
 */
public class UserServiceImpl implements UserService {
    private static Logger logger = LogManager.getLogger();
    private Encodable encoder = new ShaConverter();

    /**
     * 
     * @param login 
     * @param password 
     */
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

    /**
     * 
     * @param login 
     */
    public String checkUserAccessLevel(String login) throws ServiceException {
        try (UserDaoImpl userDao = new UserDaoImpl()) {
            String shaLogin = encoder.encode(login);
            User user = userDao.findUserByLogin(shaLogin);
            return user.getAccessLevel();
        } catch (DaoException e) {
            throw new ServiceException("DaoException", e);
        }
    }


    /**
     * 
     * @param login 
     */
    public boolean checkUserExist(String login) throws ServiceException {
        boolean exist = false;
        ArrayList<User> list = new ArrayList();
        list = getUsersList();

        String shalogin = encoder.encode(login);
        for (User user : list) {
            String loginDB = user.getLogin();
            logger.debug(loginDB + '\n' + shalogin);
            if (shalogin.equals(loginDB)) {
                exist = true;
            }
        }
        return exist;
    }

    /**
     * 
     * @param login 
     * @param password 
     */
    public boolean createUser(String login, String password) throws ServiceException {
        String shalogin = null;
        String shaPassword = null;
        try (UserDaoImpl userDao = new UserDaoImpl();
             ClientDetailDaoImpl clientDetailDao = new ClientDetailDaoImpl()) {
            shalogin = encoder.encode(login);
            shaPassword = encoder.encode(password);
            User user = new User();
            user.setLogin(shalogin);
            user.setPassword(shaPassword);
            user.setAccessLevel(AccessLevel.CLIENT.getLevel());
            userDao.create(user);
            logger.info("user created");
            int userId = userDao.findLastInsertId();
            logger.info("id extracted");
            ClientDetail clientDetail = new ClientDetail();
            clientDetail.setClientId(userId);
            return clientDetailDao.create(clientDetail);
        } catch (DaoException e) {
            throw new ServiceException("DaoException", e);
        }
    }

    /**
     * 
     * @param content 
     */
    public void showUsersAndAccess(SessionRequestContent content) throws ServiceException {
        try (UserDaoImpl userDao = new UserDaoImpl()) {
            ArrayList<User> users = userDao.findUserWithNames();
            logger.info(users);
            content.getRequestAttributes().put(AttributeName.USERS.getAttribute(), users);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 
     * @param content 
     */
    @Override
    public void showUserAccessLvl(SessionRequestContent content) throws ServiceException {
        int id = Integer.valueOf(content.getRequestParameters().get(AttributeName.USER_ID.getAttribute()));
        logger.info(id);
        try (UserDaoImpl userDao = new UserDaoImpl();
             ClientDetailDaoImpl clientDetailDao = new ClientDetailDaoImpl()) {
            User user = userDao.findEntityById(id);
            ClientDetail clientDetail = clientDetailDao.findEntityById(id);
            user.setClientDetail(clientDetail);
            logger.info(user);
            content.getRequestAttributes().put(AttributeName.USER.getAttribute(), user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 
     * @param content 
     */
    @Override
    public void saveAccessLvl(SessionRequestContent content) throws ServiceException {
        int id = Integer.valueOf(content.getRequestParameters().get(AttributeName.USER_ID.getAttribute()));
        logger.info(id);
        try (UserDaoImpl userDao = new UserDaoImpl()) {
            User user = userDao.findEntityById(id);
            logger.info("from base: " + user);
            user.setAccessLevel(content.getRequestParameters().get(AttributeName.ACCESS_LEVEL.getAttribute()));
            userDao.update(user);
            logger.info(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 
     */
    public User findDefaultDoctor() throws ServiceException {
        User user = new User();
        try (UserDaoImpl userDao = new UserDaoImpl()) {
            ArrayList<User> doctors = userDao.findUsersByAccessLevel(AccessLevel.DOCTOR.getLevel());
            user = doctors.get(0);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    /**
     * 
     */
    private ArrayList<User> getUsersList() throws ServiceException {
        ArrayList<User> users = new ArrayList<>();
        try (UserDaoImpl userDao = new UserDaoImpl()) {
            users = userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }


    /**
     * 
     * @param encoder 
     */
    public void setEncoder(Encodable encoder) {
        this.encoder = encoder;
    }
}
