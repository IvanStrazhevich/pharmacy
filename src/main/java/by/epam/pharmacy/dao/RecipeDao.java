package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DaoException;

import java.util.ArrayList;

public interface RecipeDao<T> extends AbstractDao<T> {
    /**
     * Find Recipe by params
     * @param clientId         id of client
     * @param medicineId       id of medicine
     * @param medicineQuantity quantity of medicine
     * @throws DaoException
     */
    T findRecipeByClientMedicineQuantity(Integer clientId, Integer medicineId, Integer medicineQuantity) throws DaoException;

    /**
     * Find all recipes with details provided
     * @return ArrayList of recipes
     * @throws DaoException
     */
    ArrayList<T> findAllWithDetails() throws DaoException;

    /**
     * Find recipe with details by its id
     * @param recipeId
     * @throws DaoException
     */
    T findEntityByIdWithDetails(Integer recipeId) throws DaoException;
}

