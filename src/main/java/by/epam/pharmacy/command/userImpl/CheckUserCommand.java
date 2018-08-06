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
public class CheckUserCommand implements RequestCommand<SessionRequestContent> {
    private UserService userService = new UserServiceImpl();

    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String page = null;
        try {
            if (userService.checkLogin(content)) {
                page = PagePath.WELCOME_PAGE.getPage();
            } else {
                page = PagePath.LOGIN_PAGE.getPage();
            }
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}


