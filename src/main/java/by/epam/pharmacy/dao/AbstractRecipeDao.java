package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

import java.util.ArrayList;

public interface AbstractRecipeDao<T> extends AbstractDao<T> {
    T findRecipeByClientMedicineQuantity(Integer clientId, Integer medicineId, Integer medicineQuantity) throws DaoException;
    ArrayList<T> findAllWithDetails() throws DaoException;
    T findEntityByIdWithDetails(Integer recipeId) throws DaoException;
}
