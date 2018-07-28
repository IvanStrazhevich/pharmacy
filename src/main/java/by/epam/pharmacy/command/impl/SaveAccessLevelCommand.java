package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.UserService;
import by.epam.pharmacy.service.impl.UserServiceImpl;

public class SaveAccessLevelCommand implements RequestCommand<SessionRequestContent> {
    private UserService userService = new UserServiceImpl();
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        try {
            userService.saveAccessLvl(content);
            userService.showUsersAndAccess(content);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagePath.USER_LIST_PAGE.getPage();
    }
}
