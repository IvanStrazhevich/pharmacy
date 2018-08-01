package by.epam.pharmacy.service.impl;

import by.epam.pharmacy.command.AttributeName;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.dao.impl.ClientDetailDaoImpl;
import by.epam.pharmacy.dao.impl.UserDaoImpl;
import by.epam.pharmacy.entity.ClientDetail;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.ClientService;
import by.epam.pharmacy.service.Encodable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * 
 */
public class ClientServiceImpl implements ClientService {
    Logger logger = LogManager.getLogger();
    private Encodable encoder = new ShaConverter();


    /**
     * 
     * @param login 
     */
    private int findClientId(String login) throws ServiceException {
        try (UserDaoImpl userDao = new UserDaoImpl()) {
            String shaLogin = encoder.encode(login);
            User user = userDao.findUserByLogin(shaLogin);
            logger.info("User id is " + user.getUserId());
            return user.getUserId();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 
     * @param content 
     */
    public void findClientDetail(SessionRequestContent content) throws ServiceException {
        try (ClientDetailDaoImpl clientDetailDao = new ClientDetailDaoImpl()) {
            if (content.getSessionAttributes().get(AttributeName.LOGIN.getAttribute())!= null) {
                int clientId = findClientId(content.getSessionAttributes().get(AttributeName.LOGIN.getAttribute()).toString());
                ClientDetail clientDetail = clientDetailDao.findEntityById(clientId);
                logger.info(clientDetail);
                content.getRequestAttributes().put(AttributeName.USER.getAttribute(),
                        clientDetail);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 
     * @param content 
     */
    public void createClientDetail(SessionRequestContent content) throws ServiceException {
        try (ClientDetailDaoImpl clientDetailDao = new ClientDetailDaoImpl()) {
            ArrayList<ClientDetail> details = new ArrayList<>();
            ClientDetail clientDetail = new ClientDetail();
            clientDetail.setName(content.getRequestParameters().get(AttributeName.NAME.getAttribute()));
            clientDetail.setLastname(content.getRequestParameters().get(AttributeName.LASTNAME.getAttribute()));
            clientDetail.setEmail(content.getRequestParameters().get(AttributeName.EMAIL.getAttribute()));
            clientDetail.setPhone(content.getRequestParameters().get(AttributeName.PHONE.getAttribute()));
            clientDetail.setPostcode(content.getRequestParameters().get(AttributeName.POSTCODE.getAttribute()));
            clientDetail.setCountry(content.getRequestParameters().get(AttributeName.COUNTRY.getAttribute()));
            clientDetail.setCity(content.getRequestParameters().get(AttributeName.CITY.getAttribute()));
            clientDetail.setAddress(content.getRequestParameters().get(AttributeName.ADDRESS.getAttribute()));
            clientDetail.setClientId(findClientId(content.getSessionAttributes().get(AttributeName.LOGIN.getAttribute()).toString()));
            logger.info("Client detail: " + clientDetail);
            int id = clientDetail.getClientId();
            logger.info(clientDetail.getClientId());
            logger.info("if exist: " + clientDetailDao.findEntityById(clientDetail.getClientId()));
            if (clientDetailDao.findEntityById(id).getClientId() == 0) {
                logger.info("not exist, do create");
                clientDetailDao.create(clientDetail);
            } else {
                logger.info("exist, do update");
                clientDetailDao.update(clientDetail);
            }
            details.add(clientDetailDao.findEntityById(clientDetail.getClientId()));
            logger.info("Output list: " + details);
            content.getRequestAttributes().put(AttributeName.USER.getAttribute(),
                    clientDetailDao.findEntityById(clientDetail.getClientId()));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 
     * @param encoder 
     */
    public void setEncoder(Encodable encoder) {
        this.encoder = encoder;
    }
}

