package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.controller.SessionRequestContent;

public class PayOrderCommand implements RequestCommand<SessionRequestContent> {
    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        return null;
    }
}
