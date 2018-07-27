package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.OrderService;
import by.epam.pharmacy.service.impl.OrderServiceImpl;

public class ChangeQuantity implements RequestCommand<SessionRequestContent> {
        private OrderService orderService = new OrderServiceImpl();
        @Override
        public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
            try {
                orderService.changeQuantity(sessionRequestContent);
                orderService.showOrder(sessionRequestContent);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        return PagePath.EDIT_ORDER_PAGE.getPage();
    }
}
