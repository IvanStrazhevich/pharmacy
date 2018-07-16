package by.epam.pharmacy.service;

import by.epam.pharmacy.exception.PharmacyServletException;
import by.epam.pharmacy.logic.CheckLogin;
import by.epam.pharmacy.util.ResourceManager;
import by.epam.pharmacy.util.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;

public class CheckUserHandler implements RequestHandler<SessionRequestContent> {
    private static Logger logger = LogManager.getLogger();
    CheckLogin checkLogin = new CheckLogin();
    private static final String MESSAGE = "message.wrongloginAndPass";
    private static final String MESSAGE_SUCCESS = "message.loginOk";

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws ServletException {
        String page = null;
        String login = sessionRequestContent.getRequestParameters().get(AttributeEnum.LOGIN.getAttribute());
        String password = sessionRequestContent.getRequestParameters().get(AttributeEnum.PASSWORD.getAttribute());
        try {
            if (checkLogin.checkLogin(login, password)) {
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.LOGGED.getAttribute(), AttributeEnum.LOGGED.getAttribute());
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.ACCESS_LEVEL.getAttribute(), checkLogin.checkUserAccessLevel(login));
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.LOGIN.getAttribute(), login);
                sessionRequestContent.getRequestAttributes().put(AttributeEnum.GREETING.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_SUCCESS));
                page = PagesEnum.WELCOME_PAGE.getPage();
            } else {
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.NEED_REGISTER.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE));
                page = PagesEnum.LOGIN_PAGE.getPage();
            }
        } catch (PharmacyServletException e) {
            throw new ServletException(e);
        }

        return page;
    }

}

