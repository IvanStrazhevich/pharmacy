package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagesEnum;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;

public class RegisterPageCommand implements RequestCommand<SessionRequestContent> {
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        return PagesEnum.REGISTER_PAGE.getPage();
    }
}
