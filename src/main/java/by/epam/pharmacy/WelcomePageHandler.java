package by.epam.pharmacy;

import by.epam.pharmacy.web.AttributeEnum;
import by.epam.pharmacy.web.PagesEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

public class WelcomePageHandler implements RequestHandler {
    private static final String MESSAGE = "message.needLogin";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceManager.INSTANCE.changeResource(new Locale(Config.FMT_LOCALE));
        String page = null;
        if (request.getSession().getAttribute(AttributeEnum.LOGGED.getValue()) != null) {
            page = PagesEnum.WELCOME_PAGE.getValue();
        } else {
            request.setAttribute(AttributeEnum.NEED_LOGIN.getValue(), ResourceManager.INSTANCE.getString(MESSAGE));
            page = PagesEnum.LOGIN_PAGE.getValue();
        }
        return page;
    }
}
