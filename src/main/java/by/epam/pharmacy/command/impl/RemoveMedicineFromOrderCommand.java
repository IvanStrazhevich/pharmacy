package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagesEnum;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.controller.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.OrderService;
import by.epam.pharmacy.service.impl.OrderServiceImpl;

public class RemoveMedicineFromOrderCommand implements RequestCommand<SessionRequestContent> {
    OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        try {
            orderService.removeMedicineFromOrder(sessionRequestContent);
            orderService.showOrder(sessionRequestContent);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagesEnum.EDIT_ORDER_PAGE.getPage();
    }
}
