package by.epam.pharmacy.command.medicine;

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
        String page = null;
        try {
            if (medicineService.validateForCreateOrUpdateMedicine(content)) {
                medicineService.createOrUpdateMedicine(content);
                page = PagePath.MEDICINE_LIST_PAGE.getPage();
            } else {
                medicineService.findMedicineById(content);
                page = PagePath.EDIT_MEDICINE.getPage();
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }

    public void setMedicineService(MedicineService medicineService) {
        this.medicineService = medicineService;
    }
}

