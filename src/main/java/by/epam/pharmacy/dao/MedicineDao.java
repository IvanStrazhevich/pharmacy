package by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.Medicine;
import by.epam.pharmacy.exception.DaoException;

import java.util.ArrayList;

public interface MedicineDao<T> extends AbstractDao<T> {
    /**
     * Finds Medicine by its name
     *
     * @param name
     * @return ArrayList of Medicines
     */
    ArrayList<Medicine> findMedicineByName(String name) throws DaoException;

    /**
     * Set available to false on medicine with exact id value
     * @param id
     * @return true if settled false if exeption
     */
    boolean setUnavailableById(Integer id) throws DaoException;

    /**
     * Find all elements followed record number shift no more then rawNumber
     * @param shift record number followed to select from database
     * @param rawNumber quantity of elements needed to show
     * @return ArrayList of Medicines
     * @throws DaoException
     */
    ArrayList<Medicine> findAllLimit(int shift, int rawNumber) throws DaoException;

    /**
     * @param entity
     */
    boolean setUnavailableByName(Medicine entity) throws DaoException;

}

