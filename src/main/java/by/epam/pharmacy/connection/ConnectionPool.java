package by.epam.pharmacy.connection;

import by.epam.pharmacy.exception.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static Logger logger = LogManager.getLogger();
    private static ReentrantLock lock = new ReentrantLock();
    private static final int MAX_CONNECTIONS = 20;
    private static final int MIN_CONNECTIONS = 5;
    private static final int CONNECTIONS_NORM = 10;
    private static final int NORMALIZATION_LIMIT_FOR_CONNECTIONS = 15;
    private static ConnectionPool instance;
    private ConnectionCreator connectionCreator = new ConnectionCreator();
    private LinkedBlockingDeque<ProxyConnection> connectionPoolFree = new LinkedBlockingDeque<>();
    private LinkedList<ProxyConnection> connectionInUse = new LinkedList<>();


    private ConnectionPool() {
        int poolSize = connectionCreator.definePoolSize();
        for (int i = 0; i < poolSize; i++) {
            ProxyConnection proxyConnection = connectionCreator.createConnection();
            connectionPoolFree.add(proxyConnection);
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

    /**
     * Closes all connections in pool
     * @throws PoolException
     */
    public void closeAll() throws PoolException {
        try {
            int poolSize = connectionPoolFree.size() + connectionInUse.size();
            for (int i = 0; i < poolSize; i++) {
                logger.info(connectionPoolFree.size() + " i: " + i + " in pool");
                connectionPoolFree.take().getConnection().close();
            }
        } catch (SQLException | InterruptedException e) {
            throw new PoolException("Closing proxyConnection error", e);
        }
    }


    /**
     * On demand return connection to db. If number available connection becomes less then
     * MIN_CONNECTIONS creates new one and puts in pool of free connections
     * until number of connections in use reaches MAX_CONNECTIONS. If number of
     * free connections reaches NORMALIZATION_LIMIT_FOR_CONNECTION it's number reduces
     * until reaches CONNECTIONS_NORM
     * @return proxy connection
     * @throws PoolException
     */
    public ProxyConnection getConnection() throws PoolException {
        logger.debug("Connections avalable" + connectionPoolFree.size());
        ProxyConnection proxyConnection = null;
        try {
            if (connectionPoolFree.size() < MIN_CONNECTIONS && connectionInUse.size() < MAX_CONNECTIONS) {
                logger.info("Connection adding");
                connectionPoolFree.add(connectionCreator.createConnection());
            } else if (connectionPoolFree.size() > NORMALIZATION_LIMIT_FOR_CONNECTIONS) {
                optimizePool();
            }
            proxyConnection = connectionPoolFree.take();
        } catch (InterruptedException e) {
            throw new PoolException("Getting proxyConnection error", e);
        }
        logger.debug("added" + proxyConnection + " pool free: " + connectionPoolFree.size());
        connectionInUse.add(proxyConnection);
        logger.debug("pool in use: " + connectionInUse.size());

        return proxyConnection;
    }

    /**
     * Moves proxyconnection back to pool.
     * @param proxyConnection
     */
    void releaseConnection(ProxyConnection proxyConnection) {
        logger.debug("Returning connection to pool: Connection " + proxyConnection + " is in use: " + connectionInUse.contains(proxyConnection) + " in use size " + connectionInUse.size());
        connectionInUse.remove(proxyConnection);
        logger.debug("Connection returned to pool: Connection " + proxyConnection + " is in use: " + connectionInUse.contains(proxyConnection) + " in use size " + connectionInUse.size());
        connectionPoolFree.add(proxyConnection);
        logger.debug("Connections in free poll " + connectionPoolFree.size() + "Connections in use " + connectionInUse.size());
    }

    private void optimizePool() throws PoolException {
        try {
            while (connectionPoolFree.size() > CONNECTIONS_NORM) {
                logger.debug("Optimisation " + connectionPoolFree.size());
                connectionPoolFree.take().getConnection().close();
            }
        } catch (SQLException | InterruptedException e) {
            throw new PoolException("Closing proxyConnection error", e);
        }
    }

}
