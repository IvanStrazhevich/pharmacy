package by.epam.pharmacy.command.impl.user;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.ClientService;
import by.epam.pharmacy.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class UploadPhoto implements RequestCommand<HttpServletRequest> {
    private ClientService clientService = new ClientServiceImpl();

    /**
     * @param request
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            clientService.uploadPhoto(request);
            clientService.findClientDetailFromPhotoUpload(request);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagePath.EDIT_USER_DATA_PAGE.getPage();
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}
