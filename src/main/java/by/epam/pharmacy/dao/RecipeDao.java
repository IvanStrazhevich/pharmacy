package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

import java.util.ArrayList;

public interface RecipeDao<T> extends AbstractDao<T> {
    /**
     * 
     * @param clientId 
     * @param medicineId 
     * @param medicineQuantity 
     */
    T findRecipeByClientMedicineQuantity(Integer clientId, Integer medicineId, Integer medicineQuantity) throws DaoException;
    /**
     * 
     */
    ArrayList<T> findAllWithDetails() throws DaoException;
    /**
     * 
     * @param recipeId 
     */
    T findEntityByIdWithDetails(Integer recipeId) throws DaoException;
}

