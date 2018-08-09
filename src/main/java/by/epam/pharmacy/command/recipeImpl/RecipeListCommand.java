package by.epam.pharmacy.command.recipeImpl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.RecipeService;
import by.epam.pharmacy.service.impl.RecipeServiceImpl;

/**
 *
 */
public class RecipeListCommand implements RequestCommand<SessionRequestContent> {
    private RecipeService recipeService = new RecipeServiceImpl();

    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        try {
            recipeService.showRecipes(content);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagePath.RECIPE_LIST_PAGE.getPage();
    }

    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
}

