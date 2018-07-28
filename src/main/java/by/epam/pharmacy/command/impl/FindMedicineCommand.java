package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.command.SessionRequestContent;

public class FindMedicineCommand implements RequestCommand<SessionRequestContent> {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        return null;
    }
}
