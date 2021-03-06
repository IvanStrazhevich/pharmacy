package by.epam.pharmacy.service.impl;

import by.epam.pharmacy.command.AttributeName;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.dao.impl.MedicineDaoImpl;
import by.epam.pharmacy.entity.Medicine;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.MedicineService;
import by.epam.pharmacy.util.InputValidator;
import by.epam.pharmacy.util.InputValidatorImpl;
import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 */
public class MedicineServiceImpl implements MedicineService {
    private static final String MESSAGE_DELETED = "message.medicineDeleted";
    private static final String MESSAGE_NOT_DELETED = "message.medicineNotDeleted";
    private static final String MESSAGE_NOT_AVAILABLE = "message.medicineNotAvailable";
    private static final String MESSAGE_VALIDATION = "message.validationError";
    private static final int VARCHAR_45 = 45;
    private InputValidator validator = new InputValidatorImpl();
    private static Logger logger = LogManager.getLogger();


    /**
     * Finds all entries limited by 5 on page put ArrayList result in request
     *
     * @param content
     */
    @Override
    public void findAllMedicinesLimit(SessionRequestContent content) throws ServiceException {
        int rawQuantity = Integer.valueOf(content.getRequestParameters().get(
                AttributeName.RAW_NUMBER.getAttribute()));
        int shift = Integer.valueOf(content.getRequestParameters().get(
                AttributeName.SHIFT.getAttribute()));
        try (MedicineDaoImpl medicineDao = new MedicineDaoImpl()) {
            ArrayList<Medicine> medicines = medicineDao.findAllLimit(shift, rawQuantity);
            content.getRequestAttributes().put(AttributeName.MEDICINES.getAttribute(), medicines);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds all entries put ArrayList result in request
     *
     * @param content
     * @throws ServiceException
     */
    @Override
    public void findAllMedicines(SessionRequestContent content) throws ServiceException {
        try (MedicineDaoImpl medicineDao = new MedicineDaoImpl()) {
            ArrayList<Medicine> medicines = medicineDao.findAll();
            content.getRequestAttributes().put(AttributeName.MEDICINES.getAttribute(), medicines);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds entries by name put ArrayList result in request
     *
     * @param content
     * @throws ServiceException
     */
    @Override
    public void findMedicinesByName(SessionRequestContent content) throws ServiceException {
        try (MedicineDaoImpl medicineDao = new MedicineDaoImpl()) {
            if (content.getRequestParameters().get(AttributeName.MEDICINE_NAME.getAttribute()) != null) {
                String medName = content.getRequestParameters().get(AttributeName.MEDICINE_NAME.getAttribute()).toString();
                ArrayList<Medicine> medicines = medicineDao.findMedicineByName(medName);
                logger.info(medicines);
                content.getRequestAttributes().put(AttributeName.MEDICINES.getAttribute(), medicines);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds entry by id put in result in request
     *
     * @param content
     * @throws ServiceException
     */
    @Override
    public void findMedicineById(SessionRequestContent content) throws ServiceException {
        try (MedicineDaoImpl medicineDao = new MedicineDaoImpl()) {
            if (content.getRequestParameters().get(AttributeName.MEDICINE_ID.getAttribute()) != null) {
                int medId = Integer.valueOf(content.getRequestParameters().get(AttributeName.MEDICINE_ID.getAttribute()).toString());
                Medicine medicine = medicineDao.findEntityById(medId);
                logger.info(medicine);
                content.getRequestAttributes().put(AttributeName.MEDICINE.getAttribute(), medicine);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Removes entry from database forever by its id
     *
     * @param content
     * @throws ServiceException
     */
    @Override
    public void removeMedicineFromDataBase(SessionRequestContent content) throws ServiceException {
        try (MedicineDaoImpl medicineDao = new MedicineDaoImpl()) {
            if (content.getRequestParameters().get(AttributeName.MEDICINE_ID.getAttribute()) != null) {
                int medId = Integer.valueOf(content.getRequestParameters().get(AttributeName.MEDICINE_ID.getAttribute()).toString());
                if (medicineDao.deleteById(medId)) {
                    content.getRequestAttributes().put(AttributeName.MEDICINE_DELETED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_DELETED));
                } else {
                    content.getRequestAttributes().put(AttributeName.MEDICINE_DELETED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_NOT_DELETED));
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Set available to medicine by its id
     *
     * @param content
     * @throws ServiceException
     */
    @Override
    public void removeMedicineFromAvailableList(SessionRequestContent content) throws ServiceException {
        try (MedicineDaoImpl medicineDao = new MedicineDaoImpl()) {
            if (content.getRequestParameters().get(AttributeName.MEDICINE_ID.getAttribute()) != null) {
                int medId = Integer.valueOf(content.getRequestParameters().get(AttributeName.MEDICINE_ID.getAttribute()).toString());
                medicineDao.setUnavailableById(medId);
                content.getRequestAttributes().put(AttributeName.MEDICINE_DELETED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_NOT_AVAILABLE));
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Validate incoming params
     *
     * @param content from request
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean validateForCreateOrUpdateMedicine(SessionRequestContent content) throws ServiceException {
        boolean validated = false;
        StringBuffer validationMessage = new StringBuffer(ResourceManager.INSTANCE.getString(MESSAGE_VALIDATION));
        Medicine medicineTemp = new Medicine();
        if (content.getRequestParameters().get(AttributeName.MEDICINE_ID.getAttribute()) != null) {
            medicineTemp.setMedicineId(Integer.valueOf(content.getRequestParameters().get(AttributeName.MEDICINE_ID.getAttribute())));
        }
        medicineTemp.setMedicineName(content.getRequestParameters().get(AttributeName.MEDICINE_NAME.getAttribute()));
        medicineTemp.setDescription(content.getRequestParameters().get(AttributeName.DESCRIPTION.getAttribute()));
        if (!content.getRequestParameters().get(AttributeName.DOSAGE.getAttribute()).equals("")) {
            medicineTemp.setDosage(new BigDecimal(content.getRequestParameters().get(AttributeName.DOSAGE.getAttribute())));
        }
        medicineTemp.setRecipeRequired(Boolean.parseBoolean(content.getRequestParameters().get(AttributeName.RECIPE_REQ.getAttribute())));
        if (!content.getRequestParameters().get(AttributeName.PRICE.getAttribute()).equals("")) {
            medicineTemp.setPrice(new BigDecimal(content.getRequestParameters().get(AttributeName.PRICE.getAttribute())));
        }
        medicineTemp.setAvailable(Boolean.parseBoolean(content.getRequestParameters().get(AttributeName.AVAILABLE.getAttribute())));
        if (!content.getRequestParameters().get(AttributeName.QUANTITY_AT_STORAGE.getAttribute()).equals("")) {
            medicineTemp.setQuantityAtStorage(Integer.valueOf(content.getRequestParameters().get(AttributeName.QUANTITY_AT_STORAGE.getAttribute())));
        }
        if (!validator.validateText(content.getRequestParameters().get(AttributeName.MEDICINE_NAME.getAttribute()))
                || !validator.validateLength(VARCHAR_45, content.getRequestParameters().get(AttributeName.MEDICINE_NAME.getAttribute()))) {
            validationMessage.append(AttributeName.MEDICINE_NAME.getAttribute());
        } else if (!validator.validateText(content.getRequestParameters().get(AttributeName.DESCRIPTION.getAttribute()))) {
            validationMessage.append((AttributeName.DESCRIPTION.getAttribute()));
        } else if (!validator.validateDecimal(content.getRequestParameters().get(AttributeName.DOSAGE.getAttribute()))) {
            validationMessage.append(AttributeName.DOSAGE.getAttribute());
        } else if (!validator.validateBoolean(content.getRequestParameters().get(AttributeName.RECIPE_REQ.getAttribute()))) {
            validationMessage.append(AttributeName.RECIPE_REQ.getAttribute());
        } else if (!validator.validateDecimal(content.getRequestParameters().get(AttributeName.PRICE.getAttribute()))) {
            validationMessage.append(AttributeName.PRICE.getAttribute());
        } else if (!validator.validateBoolean(content.getRequestParameters().get(AttributeName.AVAILABLE.getAttribute()))) {
            validationMessage.append(AttributeName.AVAILABLE.getAttribute());
        } else if (!validator.validateInteger(content.getRequestParameters().get(AttributeName.QUANTITY_AT_STORAGE.getAttribute()))) {
            validationMessage.append(AttributeName.QUANTITY_AT_STORAGE.getAttribute());
        } else {
            validated = true;
        }
        content.getRequestAttributes().put(AttributeName.MEDICINE.getAttribute(), medicineTemp);
        content.getRequestAttributes().put(AttributeName.VALIDATION_ERROR.getAttribute(), (validationMessage.toString()));
        return validated;

    }


    /**
     * Creates or updates entry
     *
     * @param content
     * @throws ServiceException
     */
    @Override
    public void createOrUpdateMedicine(SessionRequestContent content) throws ServiceException {
        try (MedicineDaoImpl medicineDao = new MedicineDaoImpl()) {
            ArrayList<Medicine> medicines = new ArrayList<>();
            Medicine medicine = new Medicine();
            medicine.setMedicineId(Integer.valueOf(content.getRequestParameters().get(AttributeName.MEDICINE_ID.getAttribute())));
            medicine.setMedicineName(content.getRequestParameters().get(AttributeName.MEDICINE_NAME.getAttribute()));
            medicine.setDescription(content.getRequestParameters().get(AttributeName.DESCRIPTION.getAttribute()));
            medicine.setDosage(new BigDecimal(content.getRequestParameters().get(AttributeName.DOSAGE.getAttribute())));
            medicine.setRecipeRequired(Boolean.parseBoolean(content.getRequestParameters().get(AttributeName.RECIPE_REQ.getAttribute())));
            medicine.setPrice(new BigDecimal(content.getRequestParameters().get(AttributeName.PRICE.getAttribute())));
            medicine.setAvailable(Boolean.parseBoolean(content.getRequestParameters().get(AttributeName.AVAILABLE.getAttribute())));
            medicine.setQuantityAtStorage(Integer.valueOf(content.getRequestParameters().get(AttributeName.QUANTITY_AT_STORAGE.getAttribute())));
            logger.info("Medicine: " + medicine);
            int id = medicine.getMedicineId();
            logger.info(id);
            if (id == 0) {
                logger.info("not exist, do create");
                medicineDao.create(medicine);
            } else {
                logger.info("exist, do update");
                medicineDao.update(medicine);
            }
            medicines.addAll(medicineDao.findAll());
            logger.info("Output list: " + medicines);
            content.getRequestAttributes().put(AttributeName.MEDICINES.getAttribute(),
                    medicines);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}

