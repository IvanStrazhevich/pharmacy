package by.epam.pharmacy.command.impl;


import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;

/**
 * 
 */
public class LoginPageCommand implements RequestCommand<SessionRequestContent> {
    private static final String MESSAGE = "message.needLogin";

    /**
     * 
     * @param content 
     */
    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
       /* ResourceManager.INSTANCE.changeResource(new Locale(sessionRequestContent.getSessionAttributes().get(AttributeName.LANG.getAttribute()).toString()));
        if (sessionRequestContent.getSessionAttributes().get(AttributeName.LOGGED.getAttribute()) == null) {
            sessionRequestContent.getRequestAttributes().put(AttributeName.NEED_LOGIN.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE));
            page = PagePath.LOGIN_PAGE.getPage();
        } else {
            page =*/ return PagePath.LOGIN_PAGE.getPage();
       /* }
        return page;
 */   }
}

