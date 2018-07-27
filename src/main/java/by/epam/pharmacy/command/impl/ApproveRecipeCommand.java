package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagesEnum;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.RecipeService;
import by.epam.pharmacy.service.impl.RecipeServiceImpl;

public class ApproveRecipeCommand implements RequestCommand<SessionRequestContent> {
    RecipeService recipeService = new RecipeServiceImpl();
    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        try {
            recipeService.approveRecipe(sessionRequestContent);
            recipeService.showRecipes(sessionRequestContent);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PagesEnum.RECIPE_LIST_PAGE.getPage();
    }
}
