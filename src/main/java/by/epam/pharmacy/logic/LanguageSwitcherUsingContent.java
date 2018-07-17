package by.epam.pharmacy.logic;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.service.AttributeEnum;
import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

public class LanguageSwitcherUsingContent implements LanguageSwitchable<SessionRequestContent> {
    private static Logger logger = LogManager.getLogger();
    private void langDef(String lang, SessionRequestContent sessionRequestContent) {
        switch (lang) {
            case "be_BY":
                logger.info("set page on be_BY");
                ResourceManager.INSTANCE.changeResource(new Locale("be", "BY"));
                Locale.setDefault(new Locale("be","BY"));
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.LANG.getAttribute(), "be_BY");
                break;
            case "ru_RU":
                logger.info("set page on ru_RU");
                ResourceManager.INSTANCE.changeResource(new Locale("ru", "RU"));
                Locale.setDefault(new Locale("ru","RU"));
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.LANG.getAttribute(), "ru_RU");
                break;
            case "en_US":
                logger.info("set page on en_US");
                ResourceManager.INSTANCE.changeResource(new Locale("en", "US"));
                Locale.setDefault(new Locale("en","US"));
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.LANG.getAttribute(), "en_US");
                break;
            default:
                logger.info("set default be_BY");
                ResourceManager.INSTANCE.changeResource(new Locale("be", "BY"));
                Locale.setDefault(new Locale("be","BY"));
                sessionRequestContent.getSessionAttributes().put(AttributeEnum.LANG.getAttribute(), "be_BY");
        }
    }
    @Override
    public void langSwitch(SessionRequestContent sessionRequestContent) {
        String lang = null;
        if (null != sessionRequestContent.getRequestParameters().get(AttributeEnum.LANG.getAttribute())) {
            logger.info(sessionRequestContent.getRequestParameters().get(AttributeEnum.LANG.getAttribute()));
            lang = sessionRequestContent.getRequestParameters().get(AttributeEnum.LANG.getAttribute()).toString();
            langDef(lang, sessionRequestContent);
        } else if (null != sessionRequestContent.getSessionAttributes().get(AttributeEnum.LANG.getAttribute())) {
            logger.info(sessionRequestContent.getSessionAttributes().get(AttributeEnum.LANG.getAttribute()));
            lang = sessionRequestContent.getSessionAttributes().get(AttributeEnum.LANG.getAttribute()).toString();
            langDef(lang, sessionRequestContent);
        } else {
            logger.info("no such session attribute, set default be_BY");
            ResourceManager.INSTANCE.changeResource(new Locale("be", "BY"));
            sessionRequestContent.getSessionAttributes().put(AttributeEnum.LANG.getAttribute(), "be_BY");
        }
    }
}

