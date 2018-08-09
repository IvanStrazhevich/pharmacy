package by.epam.pharmacy.command.medicineImpl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.MedicineService;
import by.epam.pharmacy.service.impl.MedicineServiceImpl;

/**
 * 
 */
public class EditMedicineCommand implements RequestCommand<SessionRequestContent> {
    private MedicineService medicineService = new MedicineServiceImpl();
    /**
     * 
     * @param content 
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        try {
            medicineService.findMedicineById(content);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagePath.EDIT_MEDICINE.getPage();
    }

    public void setMedicineService(MedicineService medicineService) {
        this.medicineService = medicineService;
    }
}

