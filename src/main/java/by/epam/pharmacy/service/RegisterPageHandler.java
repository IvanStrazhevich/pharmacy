package by.epam.pharmacy.service;

import by.epam.pharmacy.controller.PagesEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterPageHandler implements RequestHandler {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PagesEnum.REGISTER_PAGE.getValue();
    }
}
