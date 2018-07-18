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

public class CheckUserCommand implements RequestCommand<SessionRequestContent> {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.wrongloginAndPass";
    private static final String MESSAGE_SUCCESS = "message.loginOk";
    private UserService userService = new UserServiceImpl();


    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        try {
            String page = null;
            String login = sessionRequestContent.getRequestParameters().get(AttributeEnum.LOGIN.getAttribute());
            String password = sessionRequestContent.getRequestParameters().get(AttributeEnum.PASSWORD.getAttribute());
            if (userService.checkLogin(login, password)) {
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.LOGGED.getAttribute(), AttributeEnum.LOGGED.getAttribute());
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.ACCESS_LEVEL.getAttribute(), userService.checkUserAccessLevel(login));
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.LOGIN.getAttribute(), login);
                sessionRequestContent.getRequestAttributes().put(AttributeEnum.GREETING.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_SUCCESS));
                page = PagesEnum.WELCOME_PAGE.getPage();
            } else {
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.NEED_REGISTER.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE));
                page = PagesEnum.LOGIN_PAGE.getPage();
            }
            return page;
        } catch (LogicException e) {
            throw new CommandException(e);
        }
    }
}

