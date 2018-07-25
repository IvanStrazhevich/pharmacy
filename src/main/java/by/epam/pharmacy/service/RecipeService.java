package by.epam.pharmacy.service;

import by.epam.pharmacy.controller.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface RecipeService {
    void createRecipe(SessionRequestContent sessionRequestContent) throws ServiceException;
    void showRecipes(SessionRequestContent sessionRequestContent) throws ServiceException;
}
