package by.epam.pharmacy.service;

import by.epam.pharmacy.web.PagesEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InvalidateSessionHandler implements RequestHandler {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        return PagesEnum.WELCOME_PAGE.getValue();
    }
}
