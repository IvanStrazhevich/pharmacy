package by.epam.pharmacy.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
    private static Logger logger = LogManager.getLogger();
    private Properties properties = new Properties();

    int definePoolsize() {
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("connection.properties"));
        } catch (IOException e) {
            logger.error("Property file not found ", e);
            throw new RuntimeException("Property file not found ", e);
        }
        return Integer.valueOf(properties.getProperty("poolsize"));
    }

    SecureConnection createConnection() {
        SecureConnection secureConnection = new SecureConnection();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("connection.properties"));
            String url = properties.getProperty("url");
            secureConnection.setConnection(DriverManager.getConnection(url, properties));
        } catch (SQLException e) {
            logger.error("Data base is not reachable", e);
            throw new RuntimeException("Data base is not reachable", e);
        } catch (IOException e) {
            logger.error("Property file not found ", e);
            throw new RuntimeException("Property file not found ", e);
        }
        return secureConnection;
    }
}
