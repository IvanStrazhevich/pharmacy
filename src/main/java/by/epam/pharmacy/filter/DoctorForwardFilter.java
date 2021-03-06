package by.epam.pharmacy.filter;

import by.epam.pharmacy.command.AttributeName;
import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.entity.AccessLevel;
import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebFilter(dispatcherTypes = {
        DispatcherType.FORWARD,
        DispatcherType.REQUEST,
        DispatcherType.INCLUDE
}, urlPatterns = {"/jsp/doctor/*"})

public class DoctorForwardFilter implements Filter {
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
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        logger.debug("Doctor page filter Works");
        if (httpRequest.getSession().getAttribute(AttributeName.ACCESS_LEVEL.getAttribute()) == null
                || !httpRequest.getSession().getAttribute(AttributeName.ACCESS_LEVEL.getAttribute())
                .equals(AccessLevel.DOCTOR.getLevel())) {
            ((HttpServletRequest) request).getSession().setAttribute(AttributeName.NOT_AUTHORISED.getAttribute(),
                    ResourceManager.INSTANCE.getString(MESSAGE));
            httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.INDEX_PAGE.getPage());
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     *
     */
    public void destroy() {
    }
}
