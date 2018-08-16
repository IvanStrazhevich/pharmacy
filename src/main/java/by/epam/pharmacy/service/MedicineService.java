package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface MedicineService {

    /**
     * Finds all entries limited by 5 on page put ArrayList result in request
     *
     * @param content
     */
    void findAllMedicinesLimit(SessionRequestContent content) throws ServiceException;

    /**
     * Finds all entries put ArrayList result in request
     *
     * @param content
     * @throws ServiceException
     */
    void findAllMedicines(SessionRequestContent content) throws ServiceException;


    /**
     * Creates or updates entry
     *
     * @param content
     * @throws ServiceException
     */
    void createOrUpdateMedicine(SessionRequestContent content) throws ServiceException;

    /**
     * Finds entry by id put in result in request
     *
     * @param content
     * @throws ServiceException
     */
    void findMedicineById(SessionRequestContent content) throws ServiceException;

    /**
     * Removes entry from database forever by its id
     *
     * @param content
     * @throws ServiceException
     */
    void removeMedicineFromDataBase(SessionRequestContent content) throws ServiceException;

    /**
     * Set available to medicine by its id
     *
     * @param content
     * @throws ServiceException
     */
    void removeMedicineFromAvailableList(SessionRequestContent content) throws ServiceException;

    /**
     * Validate incoming params
     *
     * @param content from request
     * @return boolean
     * @throws ServiceException
     */
    boolean validateForCreateOrUpdateMedicine(SessionRequestContent content) throws ServiceException;


    /**
     * @param content
     */
    void findMedicinesByName(SessionRequestContent content) throws ServiceException;
}

