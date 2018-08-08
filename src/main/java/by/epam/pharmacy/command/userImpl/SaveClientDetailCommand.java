package by.epam.pharmacy.command.userImpl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.ClientService;
import by.epam.pharmacy.service.impl.ClientServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 */
public class SaveClientDetailCommand implements RequestCommand<SessionRequestContent> {
    private static final String UPLOAD_DIR = "uploads";
    ClientService clientService = new ClientServiceImpl();
    Logger logger = LogManager.getLogger();

    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        /*String applicationPath = request.getServletContext().getRealPath("");
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
        }*/
        try {
            if(clientService.createClientDetail(content)){
                return PagePath.CLIENT_DETAIL_PAGE.getPage();
            } else {
                clientService.findClientDetail(content);
                return PagePath.EDIT_USER_DATA_PAGE.getPage();
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}

