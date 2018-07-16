package by.epam.pharmacy.service;

import by.epam.pharmacy.util.SessionRequestContent;
import by.epam.pharmacy.util.LanguageSwitchable;
import by.epam.pharmacy.util.LanguageSwitcherUsingContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LocaleSwitchHandler implements RequestHandler<SessionRequestContent>{
    private static Logger logger = LogManager.getLogger();
    private LanguageSwitchable<SessionRequestContent> languageSwitcher = new LanguageSwitcherUsingContent();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        languageSwitcher.langSwitch(sessionRequestContent);
        return PagesEnum.WELCOME_PAGE.getPage();
    }
}
