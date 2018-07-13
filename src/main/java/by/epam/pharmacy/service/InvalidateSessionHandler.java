package by.epam.pharmacy.service;

import by.epam.pharmacy.controller.PagesEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InvalidateSessionHandler implements RequestHandler {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return PagesEnum.WELCOME_PAGE.getPage();
    }
}
