package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.MedicineService;
import by.epam.pharmacy.service.impl.MedicineServiceImpl;

public class SaveMedicineCommand implements RequestCommand<SessionRequestContent> {
    private MedicineService medicineService = new MedicineServiceImpl();
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        try {
            medicineService.createOrUpdateMedicine(content);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagePath.MEDICINE_LIST_PAGE.getPage();
    }
}
