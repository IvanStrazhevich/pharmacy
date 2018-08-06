package by.epam.pharmacy.command.userImpl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.UserService;
import by.epam.pharmacy.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 */
public class RegisterUserCommand implements RequestCommand<SessionRequestContent> {
    private static Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl();

    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String page = null;
        try {
            if (userService.checkUserExist(content)) {
                page = PagePath.REGISTER_PAGE.getPage();
            } else if (userService.createUser(content)) {
                logger.debug("registered");
                page = PagePath.WELCOME_PAGE.getPage();
            } else {
                logger.debug("not registered");
                page = PagePath.REGISTER_PAGE.getPage();
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }

    /**
     * @param userService
     */
    public void setRegistrar(UserServiceImpl userService) {
        this.userService = userService;
    }
}

