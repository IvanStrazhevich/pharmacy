package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface RecipeService {
    /**
     * Create or update Recipe
     *
     * @param content
     */
    void createOrUpdateRecipe(SessionRequestContent content) throws ServiceException;

    /**
     * Retrieve Recipe records
     *
     * @param content
     */
    void showRecipes(SessionRequestContent content) throws ServiceException;

    /**
     * Retrieve single Recipe record
     *
     * @param content
     */
    void showRecipe(SessionRequestContent content) throws ServiceException;

    /**
     * Provide approval of recipe
     *
     * @param content
     */
    void approveRecipe(SessionRequestContent content) throws ServiceException;

    /**
     * Delete recipe from database
     *
     * @param content
     */
    void deleteRecipe(SessionRequestContent content) throws ServiceException;

    /**
     * Validate incoming data for creation of recipe
     *
     * @param content
     * @return boolean
     * @throws ServiceException
     */
    boolean validateForCreateRecipe(SessionRequestContent content) throws ServiceException;

    /**
     * Validate incoming data for approval
     *
     * @param content
     * @return boolean
     * @throws ServiceException
     */
    boolean validateForApproveRecipe(SessionRequestContent content) throws ServiceException;
}

