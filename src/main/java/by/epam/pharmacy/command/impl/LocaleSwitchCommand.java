package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.service.LanguageSwitchable;
import by.epam.pharmacy.service.impl.LanguageSwitcherUsingContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LocaleSwitchCommand implements RequestCommand<SessionRequestContent> {
    private static Logger logger = LogManager.getLogger();
    private LanguageSwitchable<SessionRequestContent> languageSwitcher = new LanguageSwitcherUsingContent();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        languageSwitcher.langSwitch(sessionRequestContent);
        return PagePath.WELCOME_PAGE.getPage();
    }
}
