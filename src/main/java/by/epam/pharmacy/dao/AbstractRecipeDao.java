package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

public interface AbstractRecipeDao<T> extends AbstractDao<T> {
    T findRecipeByClientrMedicineQuantity(Integer clientId, Integer medicineId,Integer medicineQuantity) throws DaoException;
}
