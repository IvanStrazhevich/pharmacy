package by.epam.pharmacy.command.userImpl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.UserService;
import by.epam.pharmacy.service.impl.UserServiceImpl;

/**
 *
 */
public class InvalidateSessionCommand implements RequestCommand<SessionRequestContent> {
    private UserService userService = new UserServiceImpl();

    /**
     * @param content
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        try {
            userService.invalidateSession(content);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagePath.INDEX_PAGE.getPage();
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

