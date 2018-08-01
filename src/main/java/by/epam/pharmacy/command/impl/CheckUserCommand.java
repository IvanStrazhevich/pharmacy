package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.AttributeName;
import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.UserService;
import by.epam.pharmacy.service.impl.UserServiceImpl;
import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 */
public class CheckUserCommand implements RequestCommand<SessionRequestContent> {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.wrongloginAndPass";
    private static final String MESSAGE_SUCCESS = "message.loginOk";
    private UserService userService = new UserServiceImpl();


    /**
     * 
     * @param content 
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        try {
            String page = null;
            String login = content.getRequestParameters().get(AttributeName.LOGIN.getAttribute());
            String password = content.getRequestParameters().get(AttributeName.PASSWORD.getAttribute());
            if (userService.checkLogin(login, password)) {
                content.getSessionAttributes().put(AttributeName.LOGGED.getAttribute(), AttributeName.LOGGED.getAttribute());
                content.getSessionAttributes().put(AttributeName.ACCESS_LEVEL.getAttribute(), userService.checkUserAccessLevel(login));
                content.getSessionAttributes().put(AttributeName.LOGIN.getAttribute(), login);
                content.getRequestAttributes().put(AttributeName.GREETING.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_SUCCESS));
                page = PagePath.WELCOME_PAGE.getPage();
            } else {
                content.getSessionAttributes().put(AttributeName.NEED_REGISTER.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE));
                page = PagePath.LOGIN_PAGE.getPage();
            }
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}


