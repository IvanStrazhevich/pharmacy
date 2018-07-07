package by.epam.pharmacy.dao;

import by.epam.pharmacy.connection.ProxyConnectionPool;
import by.epam.pharmacy.dao.impl.AuthentificationDao;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.ProxyPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class AuthorDaoTest {
    private AuthentificationDao authentificationDao;
    private User user;
    private static Logger logger = LogManager.getLogger();
    private ProxyConnectionPool proxyConnectionPool;

    @BeforeClass
    public void beforeClass() {
        user = new User();
        user.setAuLogin("john@gmail.com");
        user.setAuPassword("pass");
        proxyConnectionPool = ProxyConnectionPool.getConnectionPool();

    }

    @AfterClass
    public void afterClass() throws ProxyPoolException {
        proxyConnectionPool.closeAll();
        proxyConnectionPool=null;

    }

    @BeforeMethod
    public void setUp() throws Exception {
        authentificationDao = new AuthentificationDao();

    }

    @AfterMethod
    public void tearDown() throws DaoException {
        authentificationDao.close();
    }

    @Test
    public void testFindAll() throws DaoException {
        System.out.println(authentificationDao.findAll());

    }

    @Test
    public void testFindEntityById() throws DaoException {
        authentificationDao.create(user);
        authentificationDao.findEntityById(authentificationDao.findLastInsertId());
    }

    @Test
    public void testDeleteByObject() throws DaoException {
        authentificationDao.create(user);
        authentificationDao.delete(user);
    }

    @Test
    public void testDeleteById() throws DaoException {
        authentificationDao.create(user);
        authentificationDao.delete(authentificationDao.findLastInsertId());
    }

    @Test
    public void testCreate() throws DaoException {
        authentificationDao.create(user);
    }

    @Test
    public void testUpdate() throws DaoException {
        authentificationDao.create(user);
        User expected = authentificationDao.findEntityById(authentificationDao.findLastInsertId());
        expected.setAuLogin("Mike");
        authentificationDao.update(expected);
        logger.info("exp: " + expected);
        User actual = authentificationDao.findEntityById(expected.getClientClId());
        logger.info("act: " + actual);
        Assert.assertEquals(expected, actual);
    }
}
