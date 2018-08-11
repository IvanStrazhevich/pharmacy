package by.epam.pharmacy.command.user;

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
    private static Logger logger = LogManager.getLogger();
    private static final String UPLOAD_DIR = "uploads";
    private ClientService clientService = new ClientServiceImpl();

    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String page = null;
        try {
            if (clientService.validateForCreateClientDetail(content)) {
                clientService.createClientDetail(content);
                page = PagePath.CLIENT_DETAIL_PAGE.getPage();
            } else {
                clientService.findClientDetail(content);
                page = PagePath.EDIT_USER_DATA_PAGE.getPage();
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}

