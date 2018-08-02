package by.epam.pharmacy.command.impl;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.service.LanguageSwitchable;
import by.epam.pharmacy.service.impl.LanguageSwitcherUsingContent;


/**
 *
 */
public class LocaleSwitchCommand implements RequestCommand<SessionRequestContent> {
    private LanguageSwitchable<SessionRequestContent> languageSwitcher = new LanguageSwitcherUsingContent();

    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) {
        languageSwitcher.langSwitch(content);
        return PagePath.WELCOME_PAGE.getPage();
    }

    public void setLanguageSwitcher(LanguageSwitchable<SessionRequestContent> languageSwitcher) {
        this.languageSwitcher = languageSwitcher;
    }
}

