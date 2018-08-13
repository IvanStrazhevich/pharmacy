package by.epam.pharmacy.command.impl.user;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.ClientService;
import by.epam.pharmacy.service.impl.ClientServiceImpl;

/**
 *
 */
public class EditUserDataPageCommand implements RequestCommand<SessionRequestContent> {
    private ClientService clientService = new ClientServiceImpl();

    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        try {
            clientService.findClientDetail(content);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagePath.EDIT_USER_DATA_PAGE.getPage();
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}

