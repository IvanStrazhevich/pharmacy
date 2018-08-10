package by.epam.pharmacy.command.order;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.OrderService;
import by.epam.pharmacy.service.impl.OrderServiceImpl;

/**
 *
 */
public class EditOrderCommand implements RequestCommand<SessionRequestContent> {
    private OrderService orderService = new OrderServiceImpl();

    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        try {
            orderService.showOrder(content);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagePath.EDIT_ORDER_PAGE.getPage();
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}

