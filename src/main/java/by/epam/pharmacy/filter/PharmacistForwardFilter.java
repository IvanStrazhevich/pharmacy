package by.epam.pharmacy.filter;

import by.epam.pharmacy.entity.AccessLevel;
import by.epam.pharmacy.service.AttributeEnum;
import by.epam.pharmacy.service.PagesEnum;
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
        DispatcherType.INCLUDE}, urlPatterns = {"/jsp/pharmacist/*"})

public class PharmacistForwardFilter implements Filter {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.not.authorised";

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse =(HttpServletResponse) response;
        logger.info("Pharmacist page filter Works");
        logger.info((httpRequest.getSession().getAttribute(AttributeEnum.ACCESS_LEVEL.getAttribute())));
        logger.info(AccessLevel.PHARMACIST.getValue());
        logger.info(httpRequest.getContextPath() + PagesEnum.INDEX_PAGE.getPage());
        if (httpRequest.getSession().getAttribute(AttributeEnum.ACCESS_LEVEL.getAttribute())==null
                || !httpRequest.getSession().getAttribute(AttributeEnum.ACCESS_LEVEL.getAttribute())
                .equals(AccessLevel.PHARMACIST.getValue())) {
            /*httpRequest.setAttribute(AttributeEnum.NOT_AUTHORISED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE));
            logger.info(httpRequest.getAttribute(AttributeEnum.NOT_AUTHORISED.getAttribute()));
            */
            httpResponse.sendRedirect(httpRequest.getContextPath() + PagesEnum.INDEX_PAGE.getPage());
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}