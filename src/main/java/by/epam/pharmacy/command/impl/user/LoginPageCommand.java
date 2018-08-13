package by.epam.pharmacy.command.impl.user;


import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;

/**
 *
 */
public class LoginPageCommand implements RequestCommand<SessionRequestContent> {

    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) {
        return PagePath.LOGIN_PAGE.getPage();
    }
}

