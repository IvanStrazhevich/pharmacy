package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagesEnum;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.controller.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.MedicineService;
import by.epam.pharmacy.service.OrderService;
import by.epam.pharmacy.service.impl.MedicineServiceImpl;
import by.epam.pharmacy.service.impl.OrderServiceImpl;

public class AddMedicineToOrder implements RequestCommand<SessionRequestContent> {
    OrderService orderService = new OrderServiceImpl();
    MedicineService medicineService = new MedicineServiceImpl();
    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        try {
            orderService.addMedicineToOrder(sessionRequestContent);
            medicineService.findAllMedicines(sessionRequestContent);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagesEnum.MEDICINE_LIST_PAGE.getPage();
    }
}
