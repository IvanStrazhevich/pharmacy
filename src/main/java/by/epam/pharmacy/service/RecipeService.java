package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface RecipeService {
    /**
     * 
     * @param content 
     */
    void createRecipe(SessionRequestContent content) throws ServiceException;

    /**
     * 
     * @param content 
     */
    void showRecipes(SessionRequestContent content) throws ServiceException;

    /**
     * 
     * @param content 
     */
    void showRecipe(SessionRequestContent content) throws ServiceException;

    /**
     * 
     * @param content 
     */
    void approveRecipe(SessionRequestContent content) throws ServiceException;
}

