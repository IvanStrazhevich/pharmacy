package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.dao.ClientDetailDao;
import by.epam.pharmacy.entity.ClientDetail;
import by.epam.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ClientDetail implementations for AbstractDao
 */
public class ClientDetailDaoImpl extends AbstractDaoImpl<ClientDetail> implements ClientDetailDao<ClientDetail>{
    private static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_PSTM = "select user_id, cl_name, cl_lastname, cl_email, cl_phone, cl_postcode, cl_country, cl_city, cl_address, cl_photo from client_detail";
    private static final String SELECT_BY_ID_PSTM = "select user_id, cl_name, cl_lastname, cl_email, cl_phone, cl_postcode, cl_country, cl_city, cl_address, cl_photo from client_detail where user_id = ?";
    private static final String INSERT_PSTM = "insert into client_detail(user_id, cl_name, cl_lastname, cl_email, cl_phone, cl_postcode, cl_country, cl_city, cl_address) values(?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_PSTM = "delete from client_detail where user_id = ?";
    private static final String UPDATE_PSTM = "update client_detail set cl_name = ?, cl_lastname = ?, cl_email = ?, cl_phone = ?, cl_postcode = ?, cl_country = ?, cl_city = ?, cl_address = ? where user_id = ?";
    private static final String UPDATE_PHOTO_PSTM = "update client_detail set cl_photo = ? where user_id = ?";

    private ProxyConnection proxyConnection;

    /**
     * 
     */
    public ClientDetailDaoImpl() throws DaoException {
        proxyConnection = super.proxyConnection;
    }

    @Override
    public boolean updatePhoto(ClientDetail entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_PHOTO_PSTM)) {
            preparedStatement.setString(1, entity.getPhoto());
            preparedStatement.setInt(2,entity.getClientId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on update Photo", e);
        }
        return success;
    }



    /**
     * Finds all entries of ClientDetails in database
     * @return ArrayList of ClientDetail
     * @throws DaoException
     */
    @Override
    public ArrayList<ClientDetail> findAll() throws DaoException {
        ArrayList<ClientDetail> clientDetailList = new ArrayList<>();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_ALL_PSTM)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                ClientDetail clientDetail = new ClientDetail();
                clientDetail.setClientId(resultSet.getInt(1));
                clientDetail.setName(resultSet.getString(2));
                clientDetail.setLastname(resultSet.getString(3));
                clientDetail.setEmail(resultSet.getString(4));
                clientDetail.setPhone(resultSet.getString(5));
                clientDetail.setPostcode(resultSet.getString(6));
                clientDetail.setCountry(resultSet.getString(7));
                clientDetail.setCity(resultSet.getString(8));
                clientDetail.setAddress(resultSet.getString(9));
                clientDetail.setPhoto(resultSet.getString(10));
                clientDetailList.add(clientDetail);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception on find all", e);
        }
        return clientDetailList;
    }

    /**
     * Finds entity by it's exact id
     * @param id
     * @return exact entity
     * @throws DaoException
     */
    @Override
    public ClientDetail findEntityById(int id) throws DaoException {
        ClientDetail clientDetail = new ClientDetail();
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_BY_ID_PSTM)) {
            preparedStatement.setInt(1, id);
            logger.info(preparedStatement.execute());
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                clientDetail.setClientId(resultSet.getInt(1));
                clientDetail.setName(resultSet.getString(2));
                clientDetail.setLastname(resultSet.getString(3));
                clientDetail.setEmail(resultSet.getString(4));
                clientDetail.setPhone(resultSet.getString(5));
                clientDetail.setPostcode(resultSet.getString(6));
                clientDetail.setCountry(resultSet.getString(7));
                clientDetail.setCity(resultSet.getString(8));
                clientDetail.setAddress(resultSet.getString(9));
                clientDetail.setPhoto(resultSet.getString(10));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception on find by id", e);
        }
        return clientDetail;
    }

    /**
     * Delete entity by given id
     * @param id
     * @return true if deleted
     * @throws DaoException
     */
    @Override
    public boolean deleteById(int id) throws DaoException {
        return deleteById(id, DELETE_PSTM);
    }

    /**
     * Delete given entity
     * @param entity
     * @return true if deleted
     * @throws DaoException
     */
    @Override
    public boolean delete(ClientDetail entity) throws DaoException {
        int id = entity.getClientId();
        return deleteById(id, DELETE_PSTM);
    }

    /**
     * Put entity into database
     * @param entity
     * @return true if inserted
     * @throws DaoException
     */
    @Override
    public boolean create(ClientDetail entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(INSERT_PSTM)) {
            preparedStatement.setInt(1, entity.getClientId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getLastname());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getPhone());
            preparedStatement.setString(6, entity.getPostcode());
            preparedStatement.setString(7, entity.getCountry());
            preparedStatement.setString(8, entity.getCity());
            preparedStatement.setString(9, entity.getAddress());
            preparedStatement.execute();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on create", e);
        }
        return success;
    }

    /**
     * Update entity in database
     * @param entity
     * @return true if updated
     * @throws DaoException
     */
    @Override
    public boolean update(ClientDetail entity) throws DaoException {
        boolean success = false;
        try (PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_PSTM)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getLastname());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getPhone());
            preparedStatement.setString(5, entity.getPostcode());
            preparedStatement.setString(6, entity.getCountry());
            preparedStatement.setString(7, entity.getCity());
            preparedStatement.setString(8, entity.getAddress());
            preparedStatement.setInt(9, entity.getClientId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new DaoException("Exception on update", e);
        }
        return success;
    }
}

