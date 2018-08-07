package by.epam.pharmacy.command.recipeImpl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.OrderService;
import by.epam.pharmacy.service.RecipeService;
import by.epam.pharmacy.service.impl.OrderServiceImpl;
import by.epam.pharmacy.service.impl.RecipeServiceImpl;

/**
 *
 */
public class ApproveRecipeCommand implements RequestCommand<SessionRequestContent> {
    private RecipeService recipeService = new RecipeServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        try {
            if (recipeService.approveRecipe(content)) {
                recipeService.showRecipes(content);
                return PagePath.RECIPE_LIST_PAGE.getPage();
            } else {
                recipeService.showRecipe(content);
                orderService.showOrder(content);
                return PagePath.RECIPE_APPROVAL_PAGE.getPage();
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }


    }

    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
}

