package by.epam.pharmacy.service;

import by.epam.pharmacy.web.PagesEnum;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterPageHandler implements RequestHandler {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        WelcomePageHandler.langDefinition(request);
        return PagesEnum.REGISTER_PAGE.getValue();
    }
}
