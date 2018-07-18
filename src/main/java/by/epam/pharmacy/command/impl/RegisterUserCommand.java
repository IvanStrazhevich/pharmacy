package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.AttributeEnum;
import by.epam.pharmacy.command.PagesEnum;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.controller.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.LogicException;
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
        String login = sessionRequestContent.getRequestParameters().get(AttributeEnum.LOGIN.getAttribute());
        String password = sessionRequestContent.getRequestParameters().get(AttributeEnum.PASSWORD.getAttribute());
        String page = null;
        try {
            if (userService.checkUserExist(login)) {
                sessionRequestContent.getRequestAttributes().put(AttributeEnum.USER_EXIST.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_USER_EXIST));
                page = PagesEnum.REGISTER_PAGE.getPage();
            } else if (userService.createUser(login, password)) {
                logger.debug("registered");
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.LOGGED.getAttribute(), AttributeEnum.LOGGED.getAttribute());
                sessionRequestContent.getRequestAttributes().put(AttributeEnum.USER_REGISTERED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_USER_REGISTERED));
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.ACCESS_LEVEL.getAttribute(), userService.checkUserAccessLevel(login));
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.LOGIN.getAttribute(), login);
                page = PagesEnum.WELCOME_PAGE.getPage();
            } else {
                logger.debug("not registered");
                sessionRequestContent.getRequestAttributes().put(AttributeEnum.USER_NOT_REGISTERED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_USER_NOT_REGISTERED));
                page = PagesEnum.REGISTER_PAGE.getPage();
            }
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return page;
    }

    public void setRegistrar(UserServiceImpl userService) {
        this.userService = userService;
    }
}
