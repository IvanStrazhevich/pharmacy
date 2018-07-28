package by.epam.pharmacy.service.impl;

import by.epam.pharmacy.command.AttributeName;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.service.LanguageSwitchable;
import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

public class LanguageSwitcherUsingContent implements LanguageSwitchable<SessionRequestContent> {
    private static Logger logger = LogManager.getLogger();
    private void langDef(String lang, SessionRequestContent content) {
        switch (lang) {
            case "be_BY":
                logger.info("set page on be_BY");
                ResourceManager.INSTANCE.changeResource(new Locale("be", "BY"));
                Locale.setDefault(new Locale("be","BY"));
                content.getSessionAttributes().put(AttributeName.LANG.getAttribute(), "be_BY");
                break;
            case "ru_RU":
                logger.info("set page on ru_RU");
                ResourceManager.INSTANCE.changeResource(new Locale("ru", "RU"));
                Locale.setDefault(new Locale("ru","RU"));
                content.getSessionAttributes().put(AttributeName.LANG.getAttribute(), "ru_RU");
                break;
            case "en_US":
                logger.info("set page on en_US");
                ResourceManager.INSTANCE.changeResource(new Locale("en", "US"));
                Locale.setDefault(new Locale("en","US"));
                content.getSessionAttributes().put(AttributeName.LANG.getAttribute(), "en_US");
                break;
            default:
                logger.info("set default be_BY");
                ResourceManager.INSTANCE.changeResource(new Locale("be", "BY"));
                Locale.setDefault(new Locale("be","BY"));
                content.getSessionAttributes().put(AttributeName.LANG.getAttribute(), "be_BY");
        }
    }
    @Override
    public void langSwitch(SessionRequestContent sessionRequestContent) {
        String lang = null;
        if (null != sessionRequestContent.getRequestParameters().get(AttributeName.LANG.getAttribute())) {
            logger.info(sessionRequestContent.getRequestParameters().get(AttributeName.LANG.getAttribute()));
            lang = sessionRequestContent.getRequestParameters().get(AttributeName.LANG.getAttribute()).toString();
            langDef(lang, sessionRequestContent);
        } else if (null != sessionRequestContent.getSessionAttributes().get(AttributeName.LANG.getAttribute())) {
            logger.info(sessionRequestContent.getSessionAttributes().get(AttributeName.LANG.getAttribute()));
            lang = sessionRequestContent.getSessionAttributes().get(AttributeName.LANG.getAttribute()).toString();
            langDef(lang, sessionRequestContent);
        } else {
            logger.info("no such session attribute, set default be_BY");
            ResourceManager.INSTANCE.changeResource(new Locale("be", "BY"));
            sessionRequestContent.getSessionAttributes().put(AttributeName.LANG.getAttribute(), "be_BY");
        }
    }
}

