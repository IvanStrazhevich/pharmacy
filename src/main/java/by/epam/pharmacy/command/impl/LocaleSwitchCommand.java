package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagesEnum;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.controller.SessionRequestContent;
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
        return PagesEnum.WELCOME_PAGE.getPage();
    }
}
