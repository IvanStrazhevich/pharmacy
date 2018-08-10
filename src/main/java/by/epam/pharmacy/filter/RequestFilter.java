package by.epam.pharmacy.filter;

import by.epam.pharmacy.command.AttributeName;
import by.epam.pharmacy.command.CommandType;
import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class RequestFilter implements Filter {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE_NOT_ATHORISED = "message.not.authorised";
    private static final String START_POINT = "/pharmacy/";

    public void init(FilterConfig fConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        logger.info("Check Request filter Works");
        logger.info(httpRequest.getMethod());
        String action = httpRequest.getParameter("action");
        String path = httpRequest.getRequestURI();
        logger.info(path);
        if (httpRequest.getMethod().equals("GET")
                && !path.startsWith(httpRequest.getContextPath() + PagePath.INDEX_PAGE.getPage())
                && !path.equals(START_POINT)) {
            logger.info(action);
            for (CommandType command : CommandType.values()) {
                logger.info(command.getCommand());
                if (action.equals(command.getCommand())) {
                    httpRequest.getSession().setAttribute(AttributeName.NOT_AUTHORISED.getAttribute(),
                            ResourceManager.INSTANCE.getString(MESSAGE_NOT_ATHORISED));
                    if (httpRequest.getRequestDispatcher(PagePath.INDEX_PAGE.getPage()) != null) {
                        httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.INDEX_PAGE.getPage());
                    }
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}
