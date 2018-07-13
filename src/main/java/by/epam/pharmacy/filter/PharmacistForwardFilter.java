package by.epam.pharmacy.filter;

import by.epam.pharmacy.controller.AttributeEnum;
import by.epam.pharmacy.controller.PagesEnum;
import by.epam.pharmacy.entity.AccessLevel;
import by.epam.pharmacy.entity.User;
import by.epam.pharmacy.service.ResourceManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(dispatcherTypes = { DispatcherType.FORWARD }, urlPatterns = { "/jsp/pharmacist/*"} )

public class PharmacistForwardFilter implements Filter {
    private static final String MESSAGE = "message.not.authorised";
    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException  {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        User user = new User();
        user.setAccessLevel(httpRequest.getSession().getAttribute(AttributeEnum.ACCESS_LEVEL.getValue()).toString());
        if(user == null || !user.getAccessLevel().equals(AccessLevel.PHARMACIST.getValue()))  {
            request.setAttribute(AttributeEnum.NOT_AUTHORISED.getValue(), ResourceManager.valueOf(MESSAGE));
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(PagesEnum.WELCOME_PAGE.getPage());
            dispatcher.forward(request, response);
        }
        chain.doFilter(request, response);
    }

    public void destroy()  {
    }
}