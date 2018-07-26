package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagesEnum;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.controller.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.RecipeService;
import by.epam.pharmacy.service.impl.RecipeServiceImpl;

public class EditRecipeCommand implements RequestCommand<SessionRequestContent> {
    RecipeService recipeService = new RecipeServiceImpl();
    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        try {
            recipeService.showRecipe(sessionRequestContent);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagesEnum.RECIPE_APPROVAL_PAGE.getPage();
    }
}
