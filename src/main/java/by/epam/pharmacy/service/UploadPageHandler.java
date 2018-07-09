package by.epam.pharmacy.service;

import by.epam.pharmacy.web.AttributeEnum;
import by.epam.pharmacy.web.PagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadPageHandler implements RequestHandler {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "message.needLogin";
    private LanguageSwitchable languageSwitcher = new LanguageSwitcher();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        logger.info(request.getParameter(AttributeEnum.LANG.getValue()));
        languageSwitcher.langSwitch(request);
        String page = null;
        if (request.getSession().getAttribute(AttributeEnum.LOGGED.getValue()) != null) {
            page = PagesEnum.UPLOAD_PAGE.getValue();
        } else {
            request.setAttribute(AttributeEnum.NEED_LOGIN.getValue(), ResourceManager.INSTANCE.getString(MESSAGE));
            page = PagesEnum.LOGIN_PAGE.getValue();
        }
        return page;
    }
    public void setLanguageSwitcher(LanguageSwitchable languageSwitcher) {
        this.languageSwitcher = languageSwitcher;
    }
}
