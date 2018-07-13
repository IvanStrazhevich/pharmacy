package by.epam.pharmacy.service;

import by.epam.pharmacy.controller.AttributeEnum;
import by.epam.pharmacy.controller.PagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomePageHandler implements RequestHandler {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.needLogin";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        if (request.getSession().getAttribute(AttributeEnum.LOGGED.getValue()) != null) {
            page = PagesEnum.WELCOME_PAGE.getPage();
        } else {
            request.setAttribute(AttributeEnum.NEED_LOGIN.getValue(), ResourceManager.INSTANCE.getString(MESSAGE));
            page = PagesEnum.LOGIN_PAGE.getPage();
        }
        return page;
    }
}

