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

public class RegisterUserCommand implements RequestCommand<SessionRequestContent> {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE_USER_EXIST = "message.userExist";
    private static final String MESSAGE_USER_REGISTERED = "message.userRegistered";
    private static final String MESSAGE_USER_NOT_REGISTERED = "message.userNotRegistered";
    private UserService userService = new UserServiceImpl();


    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        String login = sessionRequestContent.getRequestParameters().get(AttributeName.LOGIN.getAttribute());
        String password = sessionRequestContent.getRequestParameters().get(AttributeName.PASSWORD.getAttribute());
        String page = null;
        try {
            if (userService.checkUserExist(login)) {
                sessionRequestContent.getRequestAttributes().put(AttributeName.USER_EXIST.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_USER_EXIST));
                page = PagePath.REGISTER_PAGE.getPage();
            } else if (userService.createUser(login, password)) {
                logger.debug("registered");
                sessionRequestContent.getSessionAttributes().put(AttributeName.LOGGED.getAttribute(), AttributeName.LOGGED.getAttribute());
                sessionRequestContent.getRequestAttributes().put(AttributeName.USER_REGISTERED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_USER_REGISTERED));
                sessionRequestContent.getSessionAttributes().put(AttributeName.ACCESS_LEVEL.getAttribute(), userService.checkUserAccessLevel(login));
                sessionRequestContent.getSessionAttributes().put(AttributeName.LOGIN.getAttribute(), login);
                page = PagePath.WELCOME_PAGE.getPage();
            } else {
                logger.debug("not registered");
                sessionRequestContent.getRequestAttributes().put(AttributeName.USER_NOT_REGISTERED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_USER_NOT_REGISTERED));
                page = PagePath.REGISTER_PAGE.getPage();
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }

    public void setRegistrar(UserServiceImpl userService) {
        this.userService = userService;
    }
}
