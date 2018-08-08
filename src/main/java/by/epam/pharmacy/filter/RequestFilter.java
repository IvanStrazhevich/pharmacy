package by.epam.pharmacy.filter;

import by.epam.pharmacy.command.AttributeName;
import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(dispatcherTypes = {
        DispatcherType.REQUEST
}, urlPatterns = {"/jsp/*"})

public class RequestFilter implements Filter {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE_NOT_ATHORISED = "message.not.authorised";
    private static final String JSP = ".jsp";

    public void init(FilterConfig fConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        logger.info("Check Request filter Works");
        ((HttpServletRequest) request).getSession().setAttribute(AttributeName.NOT_AUTHORISED.getAttribute(),
                ResourceManager.INSTANCE.getString(MESSAGE_NOT_ATHORISED));
        httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.INDEX_PAGE.getPage());
    }

    public void destroy() {
    }
}
