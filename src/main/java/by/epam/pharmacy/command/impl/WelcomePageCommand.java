package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagesEnum;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.controller.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WelcomePageCommand implements RequestCommand<SessionRequestContent> {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.needLogin";

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        /*if (sessionRequestContent.getSessionAttributes().get(AttributeEnum.LOGGED.getAttribute()) != null) {
            page =*/ return PagesEnum.WELCOME_PAGE.getPage();
        /*} else {
            sessionRequestContent.getRequestAttributes().put(AttributeEnum.NEED_LOGIN.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE));
            page = PagesEnum.LOGIN_PAGE.getPage();
        }
        return page;*/
    }
}

