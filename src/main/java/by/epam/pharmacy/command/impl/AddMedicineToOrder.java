package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.MedicineService;
import by.epam.pharmacy.service.OrderService;
import by.epam.pharmacy.service.impl.MedicineServiceImpl;
import by.epam.pharmacy.service.impl.OrderServiceImpl;

public class AddMedicineToOrder implements RequestCommand<SessionRequestContent> {
    private OrderService orderService = new OrderServiceImpl();
    private MedicineService medicineService = new MedicineServiceImpl();
    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        try {
            orderService.addMedicineToOrder(sessionRequestContent);
            medicineService.findAllMedicines(sessionRequestContent);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagePath.MEDICINE_LIST_PAGE.getPage();
    }
}
