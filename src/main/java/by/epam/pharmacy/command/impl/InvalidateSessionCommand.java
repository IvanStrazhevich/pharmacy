package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 */
public class InvalidateSessionCommand implements RequestCommand<HttpServletRequest> {
    /**
     * 
     * @param request 
     */
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return PagePath.INDEX_PAGE.getPage();
    }
}

