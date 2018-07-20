package by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.Medicine;
import by.epam.pharmacy.exception.DaoException;

import java.util.ArrayList;

public interface AbstractMedicineDao<T> extends AbstractDao<T>{
    ArrayList<Medicine> findMedicineByName(String name) throws DaoException;
    boolean setUnavailableByName (Medicine entity) throws DaoException;
    boolean setUnavailableById(Integer id) throws  DaoException;
}
