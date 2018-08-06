package by.epam.pharmacy.command.medicineImpl;

import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.command.SessionRequestContent;

/**
 *
 */
public class FindMedicineCommand implements RequestCommand<SessionRequestContent> {
    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        return null;
    }
}

