package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 */
public class WelcomePageCommand implements RequestCommand<SessionRequestContent> {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.needLogin";

    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        return PagePath.WELCOME_PAGE.getPage();
    }
}


