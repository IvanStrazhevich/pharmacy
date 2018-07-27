package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface RecipeService {
    void createRecipe(SessionRequestContent sessionRequestContent) throws ServiceException;

    void showRecipes(SessionRequestContent sessionRequestContent) throws ServiceException;

    void showRecipe(SessionRequestContent sessionRequestContent) throws ServiceException;

    void approveRecipe(SessionRequestContent sessionRequestContent) throws ServiceException;
}
