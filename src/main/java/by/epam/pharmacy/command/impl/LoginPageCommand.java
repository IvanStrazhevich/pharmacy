package by.epam.pharmacy.command.impl;


import by.epam.pharmacy.command.PagesEnum;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;

public class LoginPageCommand implements RequestCommand<SessionRequestContent> {
    private static final String MESSAGE = "message.needLogin";

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
       /* ResourceManager.INSTANCE.changeResource(new Locale(sessionRequestContent.getSessionAttributes().get(AttributeEnum.LANG.getAttribute()).toString()));
        if (sessionRequestContent.getSessionAttributes().get(AttributeEnum.LOGGED.getAttribute()) == null) {
            sessionRequestContent.getRequestAttributes().put(AttributeEnum.NEED_LOGIN.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE));
            page = PagesEnum.LOGIN_PAGE.getPage();
        } else {
            page =*/ return PagesEnum.LOGIN_PAGE.getPage();
       /* }
        return page;
 */   }
}
