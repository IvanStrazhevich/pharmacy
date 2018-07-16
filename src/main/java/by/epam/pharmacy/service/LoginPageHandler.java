package by.epam.pharmacy.service;


import by.epam.pharmacy.util.SessionRequestContent;
import by.epam.pharmacy.util.ResourceManager;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Locale;

public class LoginPageHandler implements RequestHandler<SessionRequestContent> {
    private static final String MESSAGE = "message.needLogin";

    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws ServletException, IOException {
        String page = null;
        ResourceManager.INSTANCE.changeResource(new Locale(sessionRequestContent.getSessionAttributes().get(AttributeEnum.LANG.getAttribute()).toString()));
        if (sessionRequestContent.getSessionAttributes().get(AttributeEnum.LOGGED.getAttribute()) == null) {
            sessionRequestContent.getRequestAttributes().put(AttributeEnum.NEED_LOGIN.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE));
            page = PagesEnum.LOGIN_PAGE.getPage();
        } else {
            page = PagesEnum.WELCOME_PAGE.getPage();
        }
        return page;
    }
}
