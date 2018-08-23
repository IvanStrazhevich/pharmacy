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

/**
 *
 */
public class PageRouter {
    private HashMap<String, RequestCommand> servletMap;
    private static Logger logger = LogManager.getLogger();

    PageRouter() {
        try {
            servletMap = CommandMapper.getInstance().getServletMap();
        } catch (PharmacyServletException e) {
            logger.fatal(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Depend on CommandType defines param type for RequestCommand, then forwards or redirects to page
     *
     * @param request
     * @param response
     * @param content
     */
    void redirectToPage(HttpServletRequest request, HttpServletResponse response, SessionRequestContent content) throws IOException, ServletException {
        String action = content.getRequestParameters().get(AttributeName.ACTION.getAttribute());
        String page = null;
        if (action != null) {
            RequestCommand requestCommand = servletMap.get(action);
            logger.debug(action);
            if (action.equals(CommandType.UPLOAD_PHOTO.getCommand())) {
                try {
                    page = requestCommand.execute(request);
                    logger.debug(page);
                    if (page != null) {
                        if (request.getRequestDispatcher(page) != null) {
                            logger.debug("upload forwarded");
                            request.getRequestDispatcher(page).forward(request, response);
                        }
                    } else {
                        if (request.getRequestDispatcher(PagePath.MISSED_FILE_PAGE.getPage()) != null) {
                            response.sendRedirect(request.getContextPath() + PagePath.MISSED_FILE_PAGE.getPage());
                        }
                    }
                } catch (CommandException e) {
                    logger.error(e.getCause());
                    throw new ServletException(e);
                }
            } else {
                try {
                    page = requestCommand.execute(content);
                    logger.debug(page);
                    if (page != null) {
                        content.insertAttributes(request);
                        if (request.getRequestDispatcher(page) != null) {
                            logger.debug("forwarded");
                            request.getRequestDispatcher(page).forward(request, response);
                        }
                    } else {
                        if (request.getRequestDispatcher(PagePath.ERROR_PAGE.getPage()) != null) {
                            response.sendRedirect(request.getContextPath() + PagePath.ERROR_PAGE.getPage());
                        }
                    }
                } catch (CommandException e) {
                    logger.error(e.getCause());
                    throw new ServletException(e);
                }
            }
        }
    }
}

