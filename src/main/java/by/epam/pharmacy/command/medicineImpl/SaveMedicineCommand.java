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
public class SaveMedicineCommand implements RequestCommand<SessionRequestContent> {
    private MedicineService medicineService = new MedicineServiceImpl();

    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        try {
            if (medicineService.validateForCreateOrUpdateMedicine(content)) {
                medicineService.createOrUpdateMedicine(content);
                return PagePath.MEDICINE_LIST_PAGE.getPage();
            } else {
                medicineService.findMedicineById(content);
                return PagePath.EDIT_MEDICINE.getPage();
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    public void setMedicineService(MedicineService medicineService) {
        this.medicineService = medicineService;
    }
}

