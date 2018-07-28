package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface MedicineService {
    void findAllMedicines(SessionRequestContent content) throws ServiceException;
    void findMedicinesByName(SessionRequestContent content) throws ServiceException;
    void createOrUpdateMedicine(SessionRequestContent content) throws ServiceException;
    void findMedicineById(SessionRequestContent content) throws ServiceException;
    void removeMedicineFromDtaBase(SessionRequestContent content) throws ServiceException;
}
