package by.epam.pharmacy.command.orderImpl;

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
public class DemandRecipeCommand implements RequestCommand<SessionRequestContent> {
    private OrderService orderService = new OrderServiceImpl();
    private RecipeService recipeService = new RecipeServiceImpl();

    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        try {
            recipeService.createRecipe(content);
            orderService.showOrder(content);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagePath.EDIT_ORDER_PAGE.getPage();
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
}

