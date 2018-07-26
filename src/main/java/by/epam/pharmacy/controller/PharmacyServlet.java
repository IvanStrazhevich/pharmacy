package by.epam.pharmacy.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PharmacyServlet",
        urlPatterns = {"/WelcomePage", "/EditUserDataPage", "/UploadResultPage", "/LoginPage",
                "/MedicineListPage", "/EditMedicinePage", "/UserListPage","/EditAccessLevelPage",
                "/CheckLogin", "/RegisterUser", "/RegisterPage", "/EditOrderPage",
                "/RecipeApprovalPage", "/RecipeListPage"})
@MultipartConfig(location = ""//The directory location where files will be stored
        , fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
public class PharmacyServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    private PageRedirectTypeDefiner pageRedirectTypeDefiner = new PageRedirectTypeDefiner();

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        SessionRequestContent sessionRequestContent = new SessionRequestContent();
        sessionRequestContent.extractValues(request);
        pageRedirectTypeDefiner.redirectToPage(request, response, sessionRequestContent);
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

