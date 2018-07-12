package by.epam.pharmacy.service;

import by.epam.pharmacy.controller.PagesEnum;
import by.epam.pharmacy.util.LanguageSwitchable;
import by.epam.pharmacy.util.LanguageSwitcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LocaleSwitchHandler implements RequestHandler{
    private static Logger logger = LogManager.getLogger();
    private LanguageSwitchable languageSwitcher = new LanguageSwitcher();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        /*String locale = request.getParameter(AttributeEnum.LANG.getValue());
        request.getSession().setAttribute(AttributeEnum.LANG.getValue(), locale);*/
        languageSwitcher.langSwitch(request);
        return PagesEnum.WELCOME_PAGE.getValue();
    }
}
