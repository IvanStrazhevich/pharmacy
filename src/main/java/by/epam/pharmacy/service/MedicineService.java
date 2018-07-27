package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface MedicineService {
    void findAllMedicines(SessionRequestContent sessionRequestContent) throws ServiceException;
    void findMedicinesByName(SessionRequestContent sessionRequestContent) throws ServiceException;
    void createOrUpdateMedicine(SessionRequestContent sessionRequestContent) throws ServiceException;
    void findMedicineById(SessionRequestContent sessionRequestContent) throws ServiceException;
    void removeMedicineFromDtaBase(SessionRequestContent sessionRequestContent) throws ServiceException;
}
