package by.epam.pharmacy.service.impl;

import by.epam.pharmacy.command.AttributeEnum;
import by.epam.pharmacy.controller.SessionRequestContent;
import by.epam.pharmacy.dao.impl.MedicineDao;
import by.epam.pharmacy.entity.Medicine;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.MedicineService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MedicineServiceImpl implements MedicineService {
    Logger logger = LogManager.getLogger();

    /**
     * Finds all entries put ArrayList result in request
     *
     * @param sessionRequestContent
     * @throws ServiceException
     */
    @Override
    public void findAllMedicines(SessionRequestContent sessionRequestContent) throws ServiceException {
        try (MedicineDao medicineDao = new MedicineDao()) {
            ArrayList<Medicine> medicines = medicineDao.findAll();
            sessionRequestContent.getRequestAttributes().put(AttributeEnum.MEDICINES.getAttribute(), medicines);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds entries by name put ArrayList result in request
     *
     * @param sessionRequestContent
     * @throws ServiceException
     */
    @Override
    public void findMedicinesByName(SessionRequestContent sessionRequestContent) throws ServiceException {
        try (MedicineDao medicineDao = new MedicineDao()) {
            if (sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_NAME.getAttribute()) != null) {
                String medName = sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_NAME.getAttribute()).toString();
                ArrayList<Medicine> medicines = medicineDao.findMedicineByName(medName);
                logger.info(medicines);
                sessionRequestContent.getRequestAttributes().put(AttributeEnum.MEDICINES.getAttribute(), medicines);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds entry by id put in result in request
     *
     * @param sessionRequestContent
     * @throws ServiceException
     */
    @Override
    public void findMedicineById(SessionRequestContent sessionRequestContent) throws ServiceException {
        try (MedicineDao medicineDao = new MedicineDao()) {
            if (sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_ID.getAttribute()) != null) {
                int medId = Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_ID.getAttribute()).toString());
                Medicine medicine = medicineDao.findEntityById(medId);
                logger.info(medicine);
                sessionRequestContent.getRequestAttributes().put(AttributeEnum.MEDICINE.getAttribute(), medicine);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Removes entry from database forever by its id
     * @param sessionRequestContent
     * @throws ServiceException
     */
    @Override
    public void removeMedicineFromDtaBase(SessionRequestContent sessionRequestContent) throws ServiceException {
        try (MedicineDao medicineDao = new MedicineDao()) {
            if (sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_ID.getAttribute()) != null) {
                int medId = Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_ID.getAttribute()).toString());
                medicineDao.deleteById(medId);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

        /**
         * Creates or updates entry
         * @param sessionRequestContent
         * @throws ServiceException
         */
        @Override
        public void createOrUpdateMedicine(SessionRequestContent sessionRequestContent) throws ServiceException {
            try (MedicineDao medicineDao = new MedicineDao()) {
                ArrayList<Medicine> medicines = new ArrayList<>();
                Medicine medicine = new Medicine();
                if(sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_ID.getAttribute())!=null){
                medicine.setMedicineId(Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_ID.getAttribute())));
                }
                medicine.setMedicineName(sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_NAME.getAttribute()));
                medicine.setDescription(sessionRequestContent.getRequestParameters().get(AttributeEnum.DESCRIPTION.getAttribute()));
                medicine.setDosage(BigDecimal.valueOf(Double.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.DOSAGE.getAttribute()))));
                medicine.setRecipeRequired(Boolean.parseBoolean(sessionRequestContent.getRequestParameters().get(AttributeEnum.RECIPE_REQ.getAttribute())));
                medicine.setPrice(BigDecimal.valueOf(Double.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.PRICE.getAttribute()))));
                medicine.setAvailable(Boolean.parseBoolean(sessionRequestContent.getRequestParameters().get(AttributeEnum.AVAILABLE.getAttribute())));
                medicine.setQuantityAtStorage(Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.QUANTITY_AT_STORAGE.getAttribute())));
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
                sessionRequestContent.getRequestAttributes().put(AttributeEnum.MEDICINES.getAttribute(),
                        medicines);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }


    }
