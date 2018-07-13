package by.epam.pharmacy.service;


import by.epam.pharmacy.controller.AttributeEnum;
import by.epam.pharmacy.controller.PagesEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class LoginPageHandler implements RequestHandler {
    private static final String MESSAGE = "message.needLogin";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        ResourceManager.INSTANCE.changeResource(new Locale(request.getSession().getAttribute(AttributeEnum.LANG.getValue()).toString()));
        if (request.getSession().getAttribute(AttributeEnum.LOGGED.getValue()) == null) {
            request.setAttribute(AttributeEnum.NEED_LOGIN.getValue(), ResourceManager.INSTANCE.getString(MESSAGE));
            page = PagesEnum.LOGIN_PAGE.getPage();
        } else {
            page = PagesEnum.WELCOME_PAGE.getPage();
        }
        return page;
    }
}
