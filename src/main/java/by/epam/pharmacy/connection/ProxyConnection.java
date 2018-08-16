package by.epam.pharmacy.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Proxy for connection
 */
public class ProxyConnection implements Connection {
    private static Logger logger = LogManager.getLogger();
    private Connection connection;

    ProxyConnection() {
    }

    /**
     * Returns connection to pool
     */
    public void close() {
        logger.info("closing proxy connection");
        ConnectionPool.getInstance().releaseConnection(this);
    }


    /**
     * @return real connection to the data base
     */
    Connection getConnection() {
        return connection;
    }


    /**
     * sets real connection to database as field of ProxyConnection
     * @param connection
     */
    void setConnection(Connection connection) {
        this.connection = connection;
    }


    /***********************************************************************************
     * Other delegated methods from Connection follow
     * @see Connection
     */

    /**
     * Delegated from Connection
     *
     * @param sql String with prepared statement
     * @return prepared statement
     */

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    /**
     *
     */
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    /**
     * @param sql
     */
    public CallableStatement prepareCall(String sql) throws SQLException {
        return connection.prepareCall(sql);
    }

    /**
     * @param sql
     */
    public String nativeSQL(String sql) throws SQLException {
        return connection.nativeSQL(sql);
    }

    /**
     * @param autoCommit
     */
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    /**
     *
     */
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    /**
     *
     */
    public void commit() throws SQLException {
        connection.commit();
    }

    /**
     *
     */
    public void rollback() throws SQLException {
        connection.rollback();
    }

    /**
     *
     */
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    /**
     *
     */
    public DatabaseMetaData getMetaData() throws SQLException {
        return connection.getMetaData();
    }

    /**
     * @param readOnly
     */
    public void setReadOnly(boolean readOnly) throws SQLException {
        connection.setReadOnly(readOnly);
    }

    /**
     *
     */
    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }

    /**
     * @param catalog
     */
    public void setCatalog(String catalog) throws SQLException {
        connection.setCatalog(catalog);
    }

    /**
     *
     */
    public String getCatalog() throws SQLException {
        return connection.getCatalog();
    }

    /**
     * @param level
     */
    public void setTransactionIsolation(int level) throws SQLException {
        connection.setTransactionIsolation(level);
    }

    /**
     *
     */
    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }

    /**
     *
     */
    public SQLWarning getWarnings() throws SQLException {
        return connection.getWarnings();
    }

    /**
     *
     */
    public void clearWarnings() throws SQLException {
        connection.clearWarnings();
    }

    /**
     * @param resultSetType
     * @param resultSetConcurrency
     */
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency);
    }

    /**
     * @param sql
     * @param resultSetType
     * @param resultSetConcurrency
     */
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    /**
     * @param sql
     * @param resultSetType
     * @param resultSetConcurrency
     */
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    /**
     *
     */
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return connection.getTypeMap();
    }

    /**
     * @param map
     */
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        connection.setTypeMap(map);
    }

    /**
     * @param holdability
     */
    public void setHoldability(int holdability) throws SQLException {
        connection.setHoldability(holdability);
    }

    /**
     *
     */
    public int getHoldability() throws SQLException {
        return connection.getHoldability();
    }

    /**
     *
     */
    public Savepoint setSavepoint() throws SQLException {
        return connection.setSavepoint();
    }

    /**
     * @param name
     */
    public Savepoint setSavepoint(String name) throws SQLException {
        return connection.setSavepoint(name);
    }

    /**
     * @param savepoint
     */
    public void rollback(Savepoint savepoint) throws SQLException {
        connection.rollback(savepoint);
    }

    /**
     * @param savepoint
     */
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        connection.releaseSavepoint(savepoint);
    }

    /**
     * @param resultSetType
     * @param resultSetConcurrency
     * @param resultSetHoldability
     */
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * @param sql
     * @param resultSetType
     * @param resultSetConcurrency
     * @param resultSetHoldability
     */
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * @param sql
     * @param resultSetType
     * @param resultSetConcurrency
     * @param resultSetHoldability
     */
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * @param sql
     * @param autoGeneratedKeys
     */
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return connection.prepareStatement(sql, autoGeneratedKeys);
    }

    /**
     * @param sql
     * @param columnIndexes
     */
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return connection.prepareStatement(sql, columnIndexes);
    }

    /**
     * @param sql
     * @param columnNames
     */
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return connection.prepareStatement(sql, columnNames);
    }

    /**
     *
     */
    public Clob createClob() throws SQLException {
        return connection.createClob();
    }

    /**
     *
     */
    public Blob createBlob() throws SQLException {
        return connection.createBlob();
    }

    /**
     *
     */
    public NClob createNClob() throws SQLException {
        return connection.createNClob();
    }

    /**
     *
     */
    public SQLXML createSQLXML() throws SQLException {
        return connection.createSQLXML();
    }

    /**
     * @param timeout
     */
    public boolean isValid(int timeout) throws SQLException {
        return connection.isValid(timeout);
    }

    /**
     * @param name
     * @param value
     */
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        connection.setClientInfo(name, value);
    }

    /**
     * @param properties
     */
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        connection.setClientInfo(properties);
    }

    /**
     * @param name
     */
    public String getClientInfo(String name) throws SQLException {
        return connection.getClientInfo(name);
    }

    /**
     *
     */
    public Properties getClientInfo() throws SQLException {
        return connection.getClientInfo();
    }

    /**
     * @param typeName
     * @param elements
     */
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return connection.createArrayOf(typeName, elements);
    }

    /**
     * @param typeName
     * @param attributes
     */
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return connection.createStruct(typeName, attributes);
    }

    /**
     * @param schema
     */
    public void setSchema(String schema) throws SQLException {
        connection.setSchema(schema);
    }

    /**
     *
     */
    public String getSchema() throws SQLException {
        return connection.getSchema();
    }

    /**
     * @param executor
     */
    public void abort(Executor executor) throws SQLException {
        connection.abort(executor);
    }

    /**
     * @param executor
     * @param milliseconds
     */
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        connection.setNetworkTimeout(executor, milliseconds);
    }

    /**
     *
     */
    public int getNetworkTimeout() throws SQLException {
        return connection.getNetworkTimeout();
    }

    /**
     *
     */
    public void beginRequest() throws SQLException {
        connection.beginRequest();
    }

    /**
     *
     */
    public void endRequest() throws SQLException {
        connection.endRequest();
    }

    /**
     * @param shardingKey
     * @param superShardingKey
     * @param timeout
     */
    public boolean setShardingKeyIfValid(ShardingKey shardingKey, ShardingKey superShardingKey, int timeout) throws SQLException {
        return connection.setShardingKeyIfValid(shardingKey, superShardingKey, timeout);
    }

    /**
     * @param shardingKey
     * @param timeout
     */
    public boolean setShardingKeyIfValid(ShardingKey shardingKey, int timeout) throws SQLException {
        return connection.setShardingKeyIfValid(shardingKey, timeout);
    }

    /**
     * @param shardingKey
     * @param superShardingKey
     */
    public void setShardingKey(ShardingKey shardingKey, ShardingKey superShardingKey) throws SQLException {
        connection.setShardingKey(shardingKey, superShardingKey);
    }

    /**
     * @param shardingKey
     */
    public void setShardingKey(ShardingKey shardingKey) throws SQLException {
        connection.setShardingKey(shardingKey);
    }

    /**
     * @param iface
     */
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return connection.unwrap(iface);
    }

    /**
     * @param iface
     */
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return connection.isWrapperFor(iface);
    }
}

