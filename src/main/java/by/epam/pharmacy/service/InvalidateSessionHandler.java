package by.epam.pharmacy.service;

import javax.servlet.http.HttpServletRequest;


public class InvalidateSessionHandler implements RequestHandler<HttpServletRequest> {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return PagesEnum.WELCOME_PAGE.getPage();
    }
}
