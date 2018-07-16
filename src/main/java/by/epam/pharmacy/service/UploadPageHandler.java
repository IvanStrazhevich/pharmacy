package by.epam.pharmacy.service;

import by.epam.pharmacy.util.SessionRequestContent;
import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UploadPageHandler implements RequestHandler<SessionRequestContent> {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.needLogin";

    @Override
    public String execute(SessionRequestContent sessionRequestContent){
        String page = null;
        if (sessionRequestContent.getSessionAttributes().get(AttributeEnum.LOGGED.getAttribute()) != null) {
            page = PagesEnum.UPLOAD_PAGE.getPage();
        } else {
            sessionRequestContent.getRequestAttributes().put(AttributeEnum.NEED_LOGIN.getAttribute(),
                    ResourceManager.INSTANCE.getString(MESSAGE));
            page = PagesEnum.LOGIN_PAGE.getPage();
        }
        return page;
    }

}
