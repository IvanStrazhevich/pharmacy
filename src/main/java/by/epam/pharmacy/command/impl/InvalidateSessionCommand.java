package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagesEnum;
import by.epam.pharmacy.command.RequestCommand;

import javax.servlet.http.HttpServletRequest;


public class InvalidateSessionCommand implements RequestCommand<HttpServletRequest> {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return PagesEnum.INDEX_PAGE.getPage();
    }
}
