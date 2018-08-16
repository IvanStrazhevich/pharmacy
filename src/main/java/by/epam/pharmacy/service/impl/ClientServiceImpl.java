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
import by.epam.pharmacy.util.InputValidator;
import by.epam.pharmacy.util.InputValidatorImpl;
import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public class ClientServiceImpl implements ClientService {
    private static final String MESSAGE_FILE_VALIDATION = "message.fileTooLarge";
    private static final String MAX_FILE_SIZE_TEXT = "10 MB";
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 10;
    private static Logger logger = LogManager.getLogger();
    private static final String UPLOAD_DIR = "upload/avatars/";
    private static final String MESSAGE_VALIDATION = "message.validationError";
    private static final String MESSAGE_NO_FILE_FOUND = "message.forgotfile";
    private static final String SEPARATOR = " ";
    private static final int VARCHAR45 = 45;
    private Encodable encoder = new ShaConverter();
    private InputValidator validator = new InputValidatorImpl();


    /**
     * Find client detail record on params from request
     * @param request
     */
    public void findClientDetailFromPhotoUpload(HttpServletRequest request) throws ServiceException {
        try (ClientDetailDaoImpl clientDetailDao = new ClientDetailDaoImpl()) {
            if (request.getSession().getAttribute(AttributeName.LOGIN.getAttribute()) != null) {
                int clientId = findClientId(request.getSession().getAttribute(AttributeName.LOGIN.getAttribute()).toString());
                ClientDetail clientDetail = clientDetailDao.findEntityById(clientId);
                logger.debug(clientDetail);
                request.getSession().setAttribute(AttributeName.USER.getAttribute(), clientDetail);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Upload photo to application
     * @param request
     * @throws ServiceException
     */
    @Override
    public void uploadPhoto(HttpServletRequest request) throws ServiceException {
        StringBuffer validationMessage = new StringBuffer();
        String applicationPath = request.getServletContext().getRealPath("");
        int clientId = findClientId(request.getSession().getAttribute(AttributeName.LOGIN.getAttribute()).toString());
        String userUploadDir = UPLOAD_DIR + request.getSession().getAttribute(AttributeName.LOGIN.getAttribute()).toString();
        String uploadFilePath = applicationPath + userUploadDir;
        String filename = null;
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        try (ClientDetailDaoImpl clientDetailDao = new ClientDetailDaoImpl()) {
            ClientDetail clientDetail = new ClientDetail();
            if (null != request.getParts()) {
                for (Part part : request.getParts()) {
                    if (null != part.getSubmittedFileName()) {
                        part.write(uploadFilePath + File.separator + part.getSubmittedFileName());
                        filename = part.getSubmittedFileName();
                    }
                }
            }
            String photo = /*getClass().getResource("").getPath() + */userUploadDir + File.separator + filename;
            logger.debug(photo);
            File file = new File(applicationPath + userUploadDir + File.separator + filename);
            if (file != null) {
                logger.debug(file.length());
                if (file.length() <= MAX_FILE_SIZE) {
                    request.getSession().removeAttribute(AttributeName.PHOTO.getAttribute());
                    logger.debug(photo);
                    clientDetail.setPhoto(photo);
                    clientDetail.setClientId(clientId);
                    logger.debug(clientDetail);
                    clientDetailDao.updatePhoto(clientDetail);
                } else {
                    request.setAttribute(AttributeName.VALIDATION_ERROR.getAttribute(), (validationMessage.append(ResourceManager.INSTANCE.getString(MESSAGE_FILE_VALIDATION)+MAX_FILE_SIZE_TEXT)));
                }
            } else {
                request.setAttribute(AttributeName.VALIDATION_ERROR.getAttribute(), (validationMessage.append(ResourceManager.INSTANCE.getString(MESSAGE_NO_FILE_FOUND))));
            }
        } catch (ServletException e) {
            throw new ServiceException("ServletException while download", e);
        } catch (IOException e) {
            logger.debug("IO HAPPANNED");
            request.setAttribute(AttributeName.VALIDATION_ERROR.getAttribute(), (validationMessage.append(ResourceManager.INSTANCE.getString(MESSAGE_NO_FILE_FOUND))));
            throw new ServiceException("IOException", e);
        } catch (DaoException e) {
            throw new ServiceException("Dao exception at save", e);
        }

    }

    /**
     * @param login
     */
    public int findClientId(String login) throws ServiceException {
        try (UserDaoImpl userDao = new UserDaoImpl()) {
            String shaLogin = encoder.encode(login);
            User user = userDao.findUserByLogin(shaLogin);
            logger.debug("User id is " + user.getUserId());
            return user.getUserId();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find client detail record
     * @param content from request
     */
    public void findClientDetail(SessionRequestContent content) throws ServiceException {
        try (ClientDetailDaoImpl clientDetailDao = new ClientDetailDaoImpl()) {
            if (content.getSessionAttributes().get(AttributeName.LOGIN.getAttribute()) != null) {
                int clientId = findClientId(content.getSessionAttributes().get(AttributeName.LOGIN.getAttribute()).toString());
                ClientDetail clientDetail = clientDetailDao.findEntityById(clientId);
                logger.debug(clientDetail);
                content.getRequestAttributes().put(AttributeName.USER.getAttribute(),
                        clientDetail);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Validate incoming params
     * @param content from request
     * @return true if validated
     * @throws ServiceException
     */
    @Override
    public boolean validateForCreateClientDetail(SessionRequestContent content) throws ServiceException {
        boolean validated = false;
        StringBuffer validationMessage = new StringBuffer(ResourceManager.INSTANCE.getString(MESSAGE_VALIDATION));
        ClientDetail clientDetailTemp = new ClientDetail();
        clientDetailTemp.setClientId(findClientId(content.getSessionAttributes().get(AttributeName.LOGIN.getAttribute()).toString()));
        clientDetailTemp.setName(content.getRequestParameters().get(AttributeName.NAME.getAttribute()));
        clientDetailTemp.setLastname(content.getRequestParameters().get(AttributeName.LASTNAME.getAttribute()));
        clientDetailTemp.setEmail(content.getRequestParameters().get(AttributeName.EMAIL.getAttribute()));
        clientDetailTemp.setPhone(content.getRequestParameters().get(AttributeName.PHONE.getAttribute()));
        clientDetailTemp.setPostcode(content.getRequestParameters().get(AttributeName.POSTCODE.getAttribute()));
        clientDetailTemp.setCountry(content.getRequestParameters().get(AttributeName.COUNTRY.getAttribute()));
        clientDetailTemp.setCity(content.getRequestParameters().get(AttributeName.CITY.getAttribute()));
        clientDetailTemp.setAddress(content.getRequestParameters().get(AttributeName.ADDRESS.getAttribute()));
        if (!validator.validateText(content.getRequestParameters().get(AttributeName.NAME.getAttribute()))
                || !validator.validateLength(VARCHAR45, content.getRequestParameters().get(AttributeName.NAME.getAttribute()))) {
            validationMessage.append(AttributeName.NAME.getAttribute() + SEPARATOR);
        } else if (!validator.validateWord(content.getRequestParameters().get(AttributeName.LASTNAME.getAttribute()))
                || !validator.validateLength(VARCHAR45, content.getRequestParameters().get(AttributeName.LASTNAME.getAttribute()))) {
            validationMessage.append(AttributeName.LASTNAME.getAttribute() + SEPARATOR);
        } else if (!validator.validateEmail(content.getRequestParameters().get(AttributeName.EMAIL.getAttribute()))) {
            validationMessage.append(AttributeName.EMAIL.getAttribute() + SEPARATOR);
        } else if (!validator.validatePhone(content.getRequestParameters().get(AttributeName.PHONE.getAttribute()))) {
            validationMessage.append(AttributeName.PHONE.getAttribute() + SEPARATOR);
        } else if (!validator.validatePostcode(content.getRequestParameters().get(AttributeName.POSTCODE.getAttribute()))) {
            validationMessage.append(AttributeName.POSTCODE.getAttribute() + SEPARATOR);
        } else if (!validator.validateText(content.getRequestParameters().get(AttributeName.COUNTRY.getAttribute()))
                || !validator.validateLength(VARCHAR45, content.getRequestParameters().get(AttributeName.CITY.getAttribute()))) {
            validationMessage.append(AttributeName.COUNTRY.getAttribute() + SEPARATOR);
        } else if (!validator.validateText(content.getRequestParameters().get(AttributeName.CITY.getAttribute()))
                || !validator.validateLength(VARCHAR45, content.getRequestParameters().get(AttributeName.COUNTRY.getAttribute()))) {
            validationMessage.append(AttributeName.CITY.getAttribute() + SEPARATOR);
        } else if (!validator.validateText(content.getRequestParameters().get(AttributeName.ADDRESS.getAttribute()))
                || !validator.validateLength(VARCHAR45, content.getRequestParameters().get(AttributeName.ADDRESS.getAttribute()))) {
            validationMessage.append(AttributeName.ADDRESS.getAttribute());
        } else {
            validated = true;
        }
        content.getRequestAttributes().put(AttributeName.USER.getAttribute(), clientDetailTemp);
        content.getRequestAttributes().put(AttributeName.VALIDATION_ERROR.getAttribute(), (validationMessage.toString()));
        return validated;
    }

    /**
     * Create or update Client Detail
     * @param content from request
     */
    public void createOrUpdateClientDetails(SessionRequestContent content) throws ServiceException {
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
            logger.debug("Client detail: " + clientDetail);
            int id = clientDetail.getClientId();
            logger.debug(clientDetail.getClientId());
            logger.debug("if exist: " + clientDetailDao.findEntityById(clientDetail.getClientId()));
            if (clientDetailDao.findEntityById(id).getClientId() == 0) {
                logger.debug("not exist, do create");
                clientDetailDao.create(clientDetail);
            } else {
                logger.debug("exist, do update");
                clientDetailDao.update(clientDetail);
            }
            details.add(clientDetailDao.findEntityById(clientDetail.getClientId()));
            logger.debug("Output list: " + details);
            content.getRequestAttributes().put(AttributeName.USER.getAttribute(),
                    clientDetailDao.findEntityById(clientDetail.getClientId()));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Setteles encoder implementation
     * @param encoder
     */
    public void setEncoder(Encodable encoder) {
        this.encoder = encoder;
    }
}

