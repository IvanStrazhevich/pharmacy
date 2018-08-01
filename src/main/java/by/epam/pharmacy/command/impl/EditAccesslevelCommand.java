package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.UserService;
import by.epam.pharmacy.service.impl.UserServiceImpl;

/**
 * 
 */
public class EditAccesslevelCommand implements RequestCommand<SessionRequestContent> {
    private UserService userService = new UserServiceImpl();
    /**
     * 
     * @param content 
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        try {
            userService.showUserAccessLvl(content);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagePath.EDIT_USER_ACCESS_LEVEL_PAGE.getPage();
    }
}

