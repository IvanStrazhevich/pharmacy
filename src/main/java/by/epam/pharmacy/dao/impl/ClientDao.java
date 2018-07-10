package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.entity.Client;
import by.epam.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao extends AbstractDaoImpl<Client> {
    private static final String SELECT_ALL_PSTM = "select cl_id, cl_name, cl_lastname from client";
    private static final String SELECT_BY_ID_PSTM = "cl_id, cl_name, cl_lastname from client where cl_id = ?";
    private static final String INSERT_PSTM = "insert into client(cl_name, cl_lastname) values(?,?)";
    private static final String DELETE_PSTM = "delete from client where cl_id = ?";
    private static final String UPDATE_PSTM = "update user set cl_name = ?, cl_lastname = ? where cl_id = ?";
    private static Logger logger = LogManager.getLogger();
    private ProxyConnection proxyConnection;

    public ClientDao() throws DaoException {
        proxyConnection = super.proxyConnection;
    }

    @Override
    public List<Client> findAll() throws DaoException {
        ArrayList<Client> clientList = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_ALL_PSTM)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Client client = new Client();
                client.setClientId(resultSet.getInt(1));
                client.setName(resultSet.getString(2));
                client.setLastname(resultSet.getString(3));
                clientList.add(client);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception on find all", e);
        }
        return clientList;
    }

    @Override
    public Client findEntityById(int id) throws DaoException {
        Client client = new Client();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ID_PSTM)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            client.setClientId(resultSet.getInt(1));
            client.setName(resultSet.getString(2));
            client.setLastname(resultSet.getString(3));
        } catch (SQLException e) {
            throw new DaoException("Exception on find all", e);
        }
        return client;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_PSTM)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception on delete", e);
        }
    }

    @Override
    public boolean delete(Client entity) throws DaoException {
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_PSTM)) {
            preparedStatement.setInt(1, entity.getClientId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception on delete", e);
        }
    }

    @Override
    public boolean create(Client entity) throws DaoException {
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(INSERT_PSTM)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getLastname());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception on create", e);
        }
    }

    @Override
    public boolean update(Client entity) throws DaoException {
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_PSTM)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getLastname());
            preparedStatement.setInt(4, entity.getClientId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception on update", e);
        }
    }
}
