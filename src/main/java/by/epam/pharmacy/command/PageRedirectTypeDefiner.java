package by.epam.pharmacy.command;

import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.PharmacyServletException;
import by.epam.pharmacy.service.AttributeEnum;
import by.epam.pharmacy.service.PagesEnum;
import by.epam.pharmacy.service.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class PageRedirectTypeDefiner {
    HashMap<String, RequestHandler> servletMap;
    private static Logger logger = LogManager.getLogger();

    public PageRedirectTypeDefiner() {
        try {
            servletMap = MapCreator.getInstance().getServletMap();
        } catch (PharmacyServletException e) {
            logger.fatal(e);
            throw new RuntimeException(e);
        }
    }

    public void redirectToPage(HttpServletRequest request, HttpServletResponse response, SessionRequestContent sessionRequestContent) throws IOException, ServletException {
        String action = sessionRequestContent.getRequestParameters().get(AttributeEnum.ACTION.getAttribute());
        if (action != null) {
            RequestHandler requestHandler = servletMap.get(action);
            logger.debug(action);
            String page = null;
            try {
                if (action.equals(CommandEnum.INVALIDATE_SESSION.getCommand())) {
                    page = requestHandler.execute(request);
                    if (request.getRequestDispatcher(page) != null) {
                        response.sendRedirect(request.getContextPath() + page);
                        //request.getRequestDispatcher(page).forward(request, response);
                    }
                } else {
                    page = requestHandler.execute(sessionRequestContent);
                    if (page != null) {
                        sessionRequestContent.insertAttributes(request);
                        if (request.getRequestDispatcher(page) != null) {
                            request.getRequestDispatcher(page).forward(request, response);
                        }
                    } else {
                        if (request.getRequestDispatcher(PagesEnum.ERROR_PAGE.getPage()) != null) {
                            response.sendRedirect(request.getContextPath() + PagesEnum.ERROR_PAGE.getPage());
                        }
                    }
                }
            } catch (CommandException e) {
                throw new ServletException(e);
            }
        }
    }
}
