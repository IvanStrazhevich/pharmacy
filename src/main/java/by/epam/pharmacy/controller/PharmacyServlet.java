package by.epam.pharmacy.controller;


import by.epam.pharmacy.command.SessionRequestContent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 */
@WebServlet(name = "PharmacyServlet",
        urlPatterns = {"/WelcomePage", "/EditUserDataPage", "/ClientDetailPage", "/LoginPage",
                "/MedicineListPage", "/EditMedicinePage", "/UserListPage","/EditAccessLevelPage",
                "/CheckLogin", "/RegisterUser", "/RegisterPage", "/EditOrderPage",
                "/RecipeApprovalPage", "/RecipeListPage"})
/*@MultipartConfig(location = ""//The directory location where files will be stored
        , fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)*/
public class PharmacyServlet extends HttpServlet {
    private PageRouter pageRouter = new PageRouter();

    /**
     * 
     * @param request 
     * @param response 
     */
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        SessionRequestContent sessionRequestContent = new SessionRequestContent();
        sessionRequestContent.extractValues(request);
        pageRouter.redirectToPage(request, response, sessionRequestContent);
    }

    /**
     * 
     * @param request 
     * @param response 
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    /**
     * 
     * @param request 
     * @param response 
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }
}


