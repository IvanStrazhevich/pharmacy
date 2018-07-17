package by.epam.pharmacy.listener;

import by.epam.pharmacy.connection.ConnectionPool;
import by.epam.pharmacy.exception.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class PharmacyContextListener implements ServletContextListener {
    private static Logger logger = LogManager.getLogger();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            logger.debug("Driver not registered",e);
        }
        ConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().closeAll();
        } catch (PoolException e) {
            e.printStackTrace();
        } finally {
            DriverManager.drivers().forEach(s -> {
                logger.info("Closing Drivers");
                try {
                    DriverManager.deregisterDriver(s);
                } catch (SQLException e) {
                    logger.debug("Driver not de-registered",e);
                }
            });
        }
    }
}
