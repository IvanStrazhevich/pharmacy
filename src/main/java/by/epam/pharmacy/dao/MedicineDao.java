package by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.Medicine;
import by.epam.pharmacy.exception.DaoException;

import java.util.ArrayList;

public interface MedicineDao<T> extends AbstractDao<T>{
    /**
     * 
     * @param name 
     */
    ArrayList<Medicine> findMedicineByName(String name) throws DaoException;
    /**
     * 
     * @param entity 
     */
    boolean setUnavailableByName (Medicine entity) throws DaoException;
    /**
     * 
     * @param id 
     */
    boolean setUnavailableById(Integer id) throws  DaoException;
}

