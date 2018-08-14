package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface MedicineService {

    /**
     * @param content
     */
    void findAllMedicinesLimit(SessionRequestContent content) throws ServiceException;

    /**
     * @param content
     */
    void findAllMedicines(SessionRequestContent content) throws ServiceException;

    /**
     * @param content
     */
    void findMedicinesByName(SessionRequestContent content) throws ServiceException;

    /**
     * @param content
     */
    void createOrUpdateMedicine(SessionRequestContent content) throws ServiceException;

    /**
     * @param content
     */
    void findMedicineById(SessionRequestContent content) throws ServiceException;

    /**
     * @param content
     */
    void removeMedicineFromDataBase(SessionRequestContent content) throws ServiceException;

    /**
     * @param content
     * @throws ServiceException
     */
    void removeMedicineFromAvailableList(SessionRequestContent content) throws ServiceException;

    /**
     * @param content
     * @return boolean
     * @throws ServiceException
     */
    boolean validateForCreateOrUpdateMedicine(SessionRequestContent content) throws ServiceException;
}

