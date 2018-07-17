package by.epam.pharmacy.service;

import by.epam.pharmacy.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class FileReadHandler implements RequestHandler<HttpServletRequest> {
    private static final String UPLOAD_DIR = "uploads";
    Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + UPLOAD_DIR;
        String filename = null;
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        try{
        if (null != request.getParts()) {
            for (Part part : request.getParts()) {
                if (null != part.getSubmittedFileName()) {
                    part.write(uploadFilePath + File.separator + part.getSubmittedFileName());
                    filename = part.getSubmittedFileName();
                }
            }
        }
        } catch (ServletException e) {
            throw new CommandException("ServletException",e);
        } catch (IOException e) {
            throw new CommandException("IOException",e);
        }
        return PagesEnum.UPLOAD_RESULT_PAGE.getPage();
    }
}
