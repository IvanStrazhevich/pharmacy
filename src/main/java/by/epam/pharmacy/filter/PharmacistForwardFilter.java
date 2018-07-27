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

@WebFilter(dispatcherTypes = {
        DispatcherType.FORWARD,
        DispatcherType.REQUEST,
        DispatcherType.INCLUDE}, urlPatterns = {"/jsp/pharmacist/*",
        "/EditMedicinePage", "/EditUserAccessLevel", "/UserListPage"})

public class PharmacistForwardFilter implements Filter {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.not.authorised";

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        logger.info("Pharmacist page filter Works");
        if (httpRequest.getSession().getAttribute(AttributeName.ACCESS_LEVEL.getAttribute()) == null
                || !httpRequest.getSession().getAttribute(AttributeName.ACCESS_LEVEL.getAttribute())
                .equals(AccessLevel.PHARMACIST.getLevel())) {
            ((HttpServletRequest) request).getSession().setAttribute(AttributeName.NOT_AUTHORISED.getAttribute(),
                    ResourceManager.INSTANCE.getString(MESSAGE));
            httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.INDEX_PAGE.getPage());
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}