package by.epam.pharmacy.controller;


import by.epam.pharmacy.exception.PharmacyServletException;
import by.epam.pharmacy.service.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "PharmacyServlet",
        urlPatterns = {"/WelcomePage", "/UploadPage", "/UploadResultPage", "/LoginPage",
                "/CheckLogin", "/RegisterUser", "/RegisterPage"})
@MultipartConfig(location = ""//The directory location where files will be stored
        , fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
public class PharmacyServlet extends HttpServlet {
    Logger logger = LogManager.getLogger();
    HashMap<String, RequestHandler> servletMap;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            servletMap = MapCreator.getInstance().getServletMap();
        } catch (PharmacyServletException e) {
            logger.fatal(e);
            throw new RuntimeException(e);
        }
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        String action = request.getParameter("action");
        if (null != action) {
            RequestHandler requestHandler = servletMap.get(action);
            logger.debug(action);
            String page = requestHandler.execute(request, response);
            if (page != null) {
                if (request.getRequestDispatcher(page) != null) {
                    //response.sendRedirect(request.getContextPath() + page);
                    request.getRequestDispatcher(page).forward(request, response);
                }
            } else {
                if (request.getRequestDispatcher(PagesEnum.ERROR_PAGE.getPage()) != null) {
                    response.sendRedirect(request.getContextPath() + PagesEnum.ERROR_PAGE.getPage());
                    //request.getRequestDispatcher(PagesEnum.ERROR_PAGE.getPage()).forward(request, response);
                }
            }
        } else if (request.getRequestDispatcher(PagesEnum.ERROR_PAGE.getPage()) != null) {
            response.sendRedirect(request.getContextPath() + PagesEnum.ERROR_PAGE.getPage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }
}

