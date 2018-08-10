package by.epam.pharmacy.command.user;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;

/**
 *
 */
public class RegisterPageCommand implements RequestCommand<SessionRequestContent> {
    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) {
        return PagePath.REGISTER_PAGE.getPage();
    }
}

