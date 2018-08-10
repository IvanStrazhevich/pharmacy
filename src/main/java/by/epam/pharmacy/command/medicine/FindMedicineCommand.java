package by.epam.pharmacy.command.medicine;

import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;

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

