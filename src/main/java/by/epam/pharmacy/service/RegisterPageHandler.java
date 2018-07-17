package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;

public class RegisterPageHandler implements RequestHandler<SessionRequestContent> {
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        return PagesEnum.REGISTER_PAGE.getPage();
    }
}
