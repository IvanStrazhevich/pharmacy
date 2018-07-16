package by.epam.pharmacy.filter;

import by.epam.pharmacy.entity.AccessLevel;
import by.epam.pharmacy.service.AttributeEnum;
import by.epam.pharmacy.service.PagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(dispatcherTypes = {
        DispatcherType.FORWARD,
        DispatcherType.REQUEST,
        DispatcherType.INCLUDE
        }, urlPatterns = {"/jsp/doctor/*"}, initParams = {
        @WebInitParam(name = "INDEX_PATH", value = "jsp/index.jsp")

})

public class DoctorForwardFilter implements Filter {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.not.authorised";
    private String indexPath;
    public void init(FilterConfig fConfig) throws ServletException {
        indexPath = fConfig.getInitParameter("INDEX_PATH");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        logger.info("Doctor page filter Works");
        logger.info((httpRequest.getSession().getAttribute(AttributeEnum.ACCESS_LEVEL.getAttribute())));
        logger.info(AccessLevel.DOCTOR.getValue());
        logger.info(httpRequest.getContextPath() + PagesEnum.INDEX_PAGE.getPage());
        //logger.info(!httpRequest.getSession().getAttribute(AttributeEnum.ACCESS_LEVEL.getAttribute()).equals(AccessLevel.DOCTOR.getValue()));
        if (httpRequest.getSession().getAttribute(AttributeEnum.ACCESS_LEVEL.getAttribute())==null ||!httpRequest.getSession().getAttribute(AttributeEnum.ACCESS_LEVEL.getAttribute()).equals(AccessLevel.DOCTOR.getValue())) {
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