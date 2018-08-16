package by.epam.pharmacy.controller;


import by.epam.pharmacy.command.SessionRequestContent;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebServlet(name = "PharmacyServlet",
        urlPatterns = {"/ErrorPage","/LoginPage","/RegisterPage",
                "/EditUserDataPage","/ClientDetailPage",
                "/WelcomePage", "/MissedFilePage", "/RecipeApprovalPage",
                "/RecipeListPage", "/EditMedicinePage",
                "/EditUserAccessLevelPage", "/UserListPage",
                "/EditOrderPage", "/FindMedicinePage",
                "/OrderPage", "/HeaderPage","/FooterPage",
                "/CheckLogin",
                "/index", "/MedicineListPage","/PaymentPage"})
@MultipartConfig(location = ""//The directory location where files will be stored
        , fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 10
        , maxRequestSize = 1024 * 1024 * 5 * 5)
public class PharmacyServlet extends HttpServlet {
    private PageRouter pageRouter = new PageRouter();

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        SessionRequestContent content = new SessionRequestContent();
        content.extractValues(request);
        pageRouter.redirectToPage(request, response, content);
    }

    /**
     * @param request
     * @param response
     * @see HttpServlet
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    /**
     * @param request
     * @param response
     * @see HttpServlet
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }
}


