package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

public interface AbstractMedicineDao<T> extends AbstractDao<T>{
    T findMedicineByName(String name) throws DaoException;
}
