package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.RecipeService;
import by.epam.pharmacy.service.impl.RecipeServiceImpl;

public class ApproveRecipeCommand implements RequestCommand<SessionRequestContent> {
    private RecipeService recipeService = new RecipeServiceImpl();
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        try {
            recipeService.approveRecipe(content);
            recipeService.showRecipes(content);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PagePath.RECIPE_LIST_PAGE.getPage();
    }
}
