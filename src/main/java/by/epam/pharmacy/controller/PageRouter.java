package by.epam.pharmacy.controller;

import by.epam.pharmacy.command.*;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.PharmacyServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class PageRouter {
    private HashMap<String, RequestCommand> servletMap;
    private static Logger logger = LogManager.getLogger();

    PageRouter() {
        try {
            servletMap = MapCreator.getInstance().getServletMap();
        } catch (PharmacyServletException e) {
            logger.fatal(e);
            throw new RuntimeException(e);
        }
    }

    void redirectToPage(HttpServletRequest request, HttpServletResponse response, SessionRequestContent sessionRequestContent) throws IOException, ServletException {
        String action = sessionRequestContent.getRequestParameters().get(AttributeName.ACTION.getAttribute());
        if (action != null) {
            RequestCommand requestCommand = servletMap.get(action);
            logger.debug(action);
            String page = null;
            try {
                if (action.equals(CommandType.INVALIDATE_SESSION.getCommand())) {
                    page = requestCommand.execute(request);
                    if (request.getRequestDispatcher(page) != null) {
                        response.sendRedirect(request.getContextPath() + page);
                    }
                } else {
                    page = requestCommand.execute(sessionRequestContent);
                    if (page != null) {
                        sessionRequestContent.insertAttributes(request);
                        if (request.getRequestDispatcher(page) != null) {
                            request.getRequestDispatcher(page).forward(request, response);
                        }
                    } else {
                        if (request.getRequestDispatcher(PagePath.ERROR_PAGE.getPage()) != null) {
                            response.sendRedirect(request.getContextPath() + PagePath.ERROR_PAGE.getPage());
                        }
                    }
                }
            } catch (CommandException e) {
                logger.error(e.getCause());
                if (request.getRequestDispatcher(PagePath.ERROR_PAGE.getPage()) != null) {
                    response.sendRedirect(request.getContextPath() + PagePath.ERROR_PAGE.getPage());
                }
            }
        }
    }
}
