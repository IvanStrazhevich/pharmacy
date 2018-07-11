package by.epam.pharmacy.dao;

import by.epam.pharmacy.connection.ProxyConnectionPool;
import by.epam.pharmacy.dao.impl.UserDao;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.ProxyPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class UserDaoTest {
    private UserDao userDao;
    private User user;
    private static Logger logger = LogManager.getLogger();
    private ProxyConnectionPool proxyConnectionPool;

    @BeforeClass
    public void beforeClass() {
        user = new User();
        user.setLogin("john@gmail.com");
        user.setPassword("pass");
        proxyConnectionPool = ProxyConnectionPool.getConnectionPool();

    }

    @AfterClass
    public void afterClass() throws ProxyPoolException {
        proxyConnectionPool.closeAll();
        proxyConnectionPool=null;

    }

    @BeforeMethod
    public void setUp() throws Exception {
        userDao = new UserDao();

    }

    @AfterMethod
    public void tearDown() throws DaoException {
        userDao.close();
    }

    @Test
    public void testFindAll() throws DaoException {
        System.out.println(userDao.findAll());

    }

    @Test
    public void testFindEntityById() throws DaoException {
        userDao.create(user);
        userDao.findEntityById(userDao.findLastInsertId());
    }

    @Test
    public void testDelete() throws DaoException {
        userDao.create(user);
        userDao.delete(user);
    }

    @Test
    public void testDeleteById() throws DaoException {
        userDao.create(user);
        userDao.deleteById(userDao.findLastInsertId());
    }

    @Test
    public void testCreate() throws DaoException {
        userDao.create(user);
        userDao.delete(user);
    }

    @Test
    public void testUpdate() throws DaoException {
        userDao.create(user);
        User expected = userDao.findEntityById(userDao.findLastInsertId());
        expected.setLogin("Mike");
        userDao.update(expected);
        logger.info("exp: " + expected);
        User actual = userDao.findEntityById(expected.getUserId());
        logger.info("act: " + actual);
        Assert.assertEquals(expected, actual);
        userDao.delete(expected);
    }
}
