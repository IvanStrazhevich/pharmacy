package by.epam.pharmacy.service.impl;

import by.epam.pharmacy.command.AttributeEnum;
import by.epam.pharmacy.service.LanguageSwitchable;
import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

public class LanguageSwitcher<T> implements LanguageSwitchable<HttpServletRequest> {
    private static Logger logger = LogManager.getLogger();
     private void langDef(String lang, HttpServletRequest request) {
        switch (lang) {
            case "be_BY":
                logger.debug("set page on be_BY");
                Config.set(request, Config.FMT_LOCALE, new Locale("be", "BY"));
                ResourceManager.INSTANCE.changeResource(new Locale("be", "BY"));
                Locale.setDefault(new Locale("be","BY"));
                request.getSession().setAttribute(AttributeEnum.LANG.getAttribute(), "be_BY");
                break;
            case "ru_RU":
                logger.debug("set page on ru_RU");
                Config.set(request, Config.FMT_LOCALE, new Locale("ru", "RU"));
                ResourceManager.INSTANCE.changeResource(new Locale("ru", "RU"));
                Locale.setDefault(new Locale("ru","RU"));
                request.getSession().setAttribute(AttributeEnum.LANG.getAttribute(), "ru_RU");
                break;
            case "en_US":
                logger.debug("set page on en_US");
                Config.set(request, Config.FMT_LOCALE, new Locale("en", "US"));
                ResourceManager.INSTANCE.changeResource(new Locale("en", "US"));
                Locale.setDefault(new Locale("en","US"));
                request.getSession().setAttribute(AttributeEnum.LANG.getAttribute(), "en_US");
                break;
            default:
                logger.debug("set default be_BY");
                Config.set(request, Config.FMT_LOCALE, new Locale("be", "BY"));
                ResourceManager.INSTANCE.changeResource(new Locale("be", "BY"));
                Locale.setDefault(new Locale("be","BY"));
                request.getSession().setAttribute(AttributeEnum.LANG.getAttribute(), "be_BY");
        }
    }
     @Override
     public void langSwitch(HttpServletRequest request) {
        String lang = null;
        if (null != request.getParameter(AttributeEnum.LANG.getAttribute())) {
            logger.debug(request.getParameter(AttributeEnum.LANG.getAttribute()));
            lang = request.getParameter(AttributeEnum.LANG.getAttribute()).toString();
            langDef(lang, request);
        } else if (null != request.getSession().getAttribute(AttributeEnum.LANG.getAttribute())) {
            logger.debug(request.getSession().getAttribute(AttributeEnum.LANG.getAttribute()));
            lang = request.getSession().getAttribute(AttributeEnum.LANG.getAttribute()).toString();
            langDef(lang, request);
        } else {
            logger.debug("no such session attribute, set default be_BY");
            Config.set(request, Config.FMT_LOCALE, new Locale("be", "BY"));
            ResourceManager.INSTANCE.changeResource(new Locale("be", "BY"));
            request.getSession().setAttribute(AttributeEnum.LANG.getAttribute(), "be_BY");
        }
    }
}
