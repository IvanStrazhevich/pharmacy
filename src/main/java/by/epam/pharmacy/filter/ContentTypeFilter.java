package by.epam.pharmacy.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *
 */
@WebFilter(dispatcherTypes = {
        DispatcherType.FORWARD,
        DispatcherType.REQUEST,
        DispatcherType.INCLUDE}, urlPatterns = {"/*"})

public class ContentTypeFilter implements Filter {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.not.authorised";

    /**
     * @param fConfig
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

    /**
     * @param request
     * @param response
     * @param chain
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
    }

    /**
     *
     */
    public void destroy() {
    }
}

