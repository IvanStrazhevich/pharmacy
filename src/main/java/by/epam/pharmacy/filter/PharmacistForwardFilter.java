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
import java.io.IOException;

@WebFilter(dispatcherTypes = {
        DispatcherType.FORWARD,
        DispatcherType.REQUEST,
        DispatcherType.INCLUDE}, urlPatterns = {"/jsp/pharmacist/*"})

public class PharmacistForwardFilter implements Filter {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.not.authorised";

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        logger.info("Pharmacist page filter Works");
        if (!httpRequest.getSession().getAttribute(AttributeEnum.ACCESS_LEVEL.getAttribute()).equals(AccessLevel.PHARMACIST.getValue())) {
            request.setAttribute(AttributeEnum.NOT_AUTHORISED.getAttribute(), ResourceManager.valueOf(MESSAGE));
            logger.info(request.getAttribute(AttributeEnum.NOT_AUTHORISED.getAttribute()));
            if (httpRequest.getRequestDispatcher(PagesEnum.WELCOME_PAGE.getPage()) != null) {
               httpRequest.getRequestDispatcher(httpRequest.getContextPath() + PagesEnum.WELCOME_PAGE.getPage()).forward(request, response);
            }
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}