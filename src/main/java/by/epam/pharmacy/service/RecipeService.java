package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface RecipeService {
    void createRecipe(SessionRequestContent content) throws ServiceException;

    void showRecipes(SessionRequestContent content) throws ServiceException;

    void showRecipe(SessionRequestContent content) throws ServiceException;

    void approveRecipe(SessionRequestContent content) throws ServiceException;
}
