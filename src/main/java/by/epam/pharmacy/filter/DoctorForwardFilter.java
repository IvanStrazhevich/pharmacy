package by.epam.pharmacy.filter;

import by.epam.pharmacy.entity.AccessLevel;
import by.epam.pharmacy.service.AttributeEnum;
import by.epam.pharmacy.service.PagesEnum;
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
        }, urlPatterns = {"/jsp/doctor/*"})

public class DoctorForwardFilter implements Filter {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.not.authorised";

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        logger.info("Doctor page filter Works");
        logger.info((httpRequest.getSession().getAttribute(AttributeEnum.ACCESS_LEVEL.getAttribute())));
        logger.info(AccessLevel.DOCTOR.getValue());
        logger.info(httpRequest.getContextPath() + PagesEnum.WELCOME_PAGE.getPage());
        logger.info(!httpRequest.getSession().getAttribute(AttributeEnum.ACCESS_LEVEL.getAttribute()).equals(AccessLevel.DOCTOR.getValue()));
        if (!httpRequest.getSession().getAttribute(AttributeEnum.ACCESS_LEVEL.getAttribute()).equals(AccessLevel.DOCTOR.getValue())) {
            httpRequest.setAttribute(AttributeEnum.NOT_AUTHORISED.getAttribute(), ResourceManager.valueOf(MESSAGE));
            logger.info(httpRequest.getAttribute(AttributeEnum.NOT_AUTHORISED.getAttribute()));
            httpServletResponse.sendRedirect(httpRequest.getContextPath() + PagesEnum.WELCOME_PAGE.getPage());
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}