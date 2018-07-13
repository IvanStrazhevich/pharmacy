package by.epam.pharmacy.connection;

import by.epam.pharmacy.exception.ProxyPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static Logger logger = LogManager.getLogger();
    private static final int MAX_CONNECTIONS = 20;
    private static final int MIN_CONNECTIONS = 5;
    private static final int CONNECTIONS_NORM =10;
    private static final int NORMALIZATION_LIMIT_FOR_CONNECTIONS = 15;
    private static ConnectionPool instance;
    private ConnectionCreator connectionCreator = new ConnectionCreator();
    private LinkedBlockingDeque<SecureConnection> connectionPoolFree = new LinkedBlockingDeque<>();
    private LinkedList<SecureConnection> connectionInUse = new LinkedList<>();
    private static ReentrantLock lock = new ReentrantLock();

    private ConnectionPool() {
        int poolsize = connectionCreator.definePoolsize();
        for (int i = 0; i < poolsize; i++) {
            SecureConnection secureConnection = connectionCreator.createConnection();
            connectionPoolFree.add(secureConnection);
        }
    }
    public static ConnectionPool getInstance() {
        if (null == instance) {
            try {
                lock.lock();
                if (null == instance) {
                    instance = new ConnectionPool();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public void closeAll() throws ProxyPoolException {
        try {
            int poolsize = connectionPoolFree.size()+connectionInUse.size();
            for (int i = 0; i < poolsize; i++) {
                logger.info(connectionPoolFree.size() + " i: " + i + " in pool");
                connectionPoolFree.take().getConnection().close();
            }
        } catch (SQLException | InterruptedException e) {
            throw new ProxyPoolException("Closing secureConnection error", e);
        }
    }

    private void optimizePool() throws ProxyPoolException {
        try {
            while (connectionPoolFree.size() > CONNECTIONS_NORM) {
                logger.debug("Optimisation " + connectionPoolFree.size());
                connectionPoolFree.take().getConnection().close();
            }
        } catch (SQLException | InterruptedException e) {
            throw new ProxyPoolException("Closing secureConnection error", e);
        }
    }

    public SecureConnection getConnection() throws ProxyPoolException {
        logger.debug("Connections avalable" + connectionPoolFree.size());
        SecureConnection secureConnection = null;
        try {
            if (connectionPoolFree.size() < MIN_CONNECTIONS && connectionInUse.size() < MAX_CONNECTIONS) {
                logger.info("Connection adding");
                connectionPoolFree.add(connectionCreator.createConnection());
            } else if (connectionPoolFree.size() > NORMALIZATION_LIMIT_FOR_CONNECTIONS) {
                optimizePool();
            }
            secureConnection = connectionPoolFree.take();
        } catch (InterruptedException e) {
            throw new ProxyPoolException("Getting secureConnection error", e);
        }
        logger.debug("added" + secureConnection + " pool free: " + connectionPoolFree.size());
        connectionInUse.add(secureConnection);
        logger.debug("pool in use: " + connectionInUse.size());

        return secureConnection;
    }

    void releaseConnection(SecureConnection secureConnection) {
        logger.debug("Returning connection to pool: Connection " + secureConnection + " is in use: " + connectionInUse.contains(secureConnection) + " in use size " + connectionInUse.size());
        connectionInUse.remove(secureConnection);
        logger.debug("Connection returned to pool: Connection " + secureConnection + " is in use: " + connectionInUse.contains(secureConnection) + " in use size " + connectionInUse.size());
        connectionPoolFree.add(secureConnection);
        logger.debug("Connections in free poll " + connectionPoolFree.size() + "Connections in use " + connectionInUse.size());
    }
}
