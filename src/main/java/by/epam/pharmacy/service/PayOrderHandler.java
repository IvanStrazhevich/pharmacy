package by.epam.pharmacy.service;

import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.util.SessionRequestContent;

public class PayOrderHandler implements RequestHandler<SessionRequestContent> {
    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        return null;
    }
}
