package by.epam.pharmacy.filter;

import by.epam.pharmacy.command.AttributeEnum;
import by.epam.pharmacy.command.PagesEnum;
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
}, urlPatterns = {"/jsp/*"})
public class LoginForwardFilter implements Filter {

    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.needLogin";
    private String indexPath;

    public void init(FilterConfig fConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        logger.info("Check Login filter Works");
        String path = ((HttpServletRequest) request).getRequestURI();
        if (path.startsWith(httpRequest.getContextPath() + PagesEnum.LOGIN_PAGE.getPage())
                || path.startsWith(httpRequest.getContextPath() + PagesEnum.REGISTER_PAGE.getPage())
                || path.startsWith(httpRequest.getContextPath() + PagesEnum.WELCOME_PAGE.getPage())) {
            chain.doFilter(request, response);
        } else {
            if (httpRequest.getSession().getAttribute(AttributeEnum.LOGGED.getAttribute()) == null
                    || !httpRequest.getSession().getAttribute(AttributeEnum.LOGGED.getAttribute()).equals(AttributeEnum.LOGGED.getAttribute())) {
                ((HttpServletRequest) request).getSession().setAttribute(AttributeEnum.NEED_LOGIN.getAttribute(),
                        ResourceManager.INSTANCE.getString(MESSAGE));
                httpResponse.sendRedirect(httpRequest.getContextPath() + PagesEnum.INDEX_PAGE.getPage());
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    public void destroy() {
    }
}
