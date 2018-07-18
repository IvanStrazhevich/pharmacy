package by.epam.pharmacy.filter;

import by.epam.pharmacy.command.AttributeEnum;
import by.epam.pharmacy.command.PagesEnum;
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
        DispatcherType.INCLUDE
}, urlPatterns = {"/jsp/doctor/*"})

public class DoctorForwardFilter implements Filter {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.not.authorised";
    private String indexPath;

    public void init(FilterConfig fConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        logger.info("Doctor page filter Works");
        if (httpRequest.getSession().getAttribute(AttributeEnum.ACCESS_LEVEL.getAttribute()) == null || !httpRequest.getSession().getAttribute(AttributeEnum.ACCESS_LEVEL.getAttribute()).equals(AccessLevel.DOCTOR.getLevel())) {
            ((HttpServletRequest) request).getSession().setAttribute(AttributeEnum.NOT_AUTHORISED.getAttribute(),
                    ResourceManager.INSTANCE.getString(MESSAGE));
            httpResponse.sendRedirect(httpRequest.getContextPath() + PagesEnum.INDEX_PAGE.getPage());
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}