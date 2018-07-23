package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagesEnum;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.controller.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.UserService;
import by.epam.pharmacy.service.impl.UserServiceImpl;

public class EditAccesslevelCommand implements RequestCommand<SessionRequestContent> {
    UserService userService = new UserServiceImpl();
    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        try {
            userService.showUserAccessLvl(sessionRequestContent);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagesEnum.EDIT_USER_ACCESS_LEVEL_PAGE.getPage();
    }
}
