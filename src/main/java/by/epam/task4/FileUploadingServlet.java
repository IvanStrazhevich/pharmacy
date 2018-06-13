package by.epam.task4;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet(urlPatterns = {"/upload/*"})
@MultipartConfig(location = ""//The directory location where files will be stored
        , fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        System.out.println("Upload File Directory = " + fileSaveDir.getAbsolutePath());
//request.getParts().stream().forEach(part -> {
//    try {
//        part.write(uploadFilePath + File.separator + part.getSubmittedFileName());
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//});
        for (Part part : request.getParts()) {
            if (part.getSubmittedFileName() != null) {
                part.write(uploadFilePath + File.separator + part.getSubmittedFileName());
                //   part.write("d:\\temp\\" + part. getSubmittedFileName());
                response.getWriter().print(part.getSubmittedFileName() + " upload successfully");
            }
        }
        // InputStream in = request.getInputStream();

//        File file = new File(uploadFilePath + File.separator + part.getSubmittedFileName());
//        OutputStream out = new FileOutputStream(file);
//        byte[] buffer = new byte[1024];
//
//        int len =0;
//        while((len=in.read(buffer))!=-1){
//            out.write(buffer, 0, len);
//        }
//     //   bao.close();
//        out.close();
//        in.close();

    }
}

