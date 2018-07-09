package by.epam.pharmacy.service;

import by.epam.pharmacy.web.AttributeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

public class LanguageSwitcher implements LanguageSwitchable{
    private static Logger logger = LogManager.getLogger();
     private void langDef(String lang, HttpServletRequest request) {
        switch (lang) {
            case "be_BY":
                logger.info("set page on be_BY");
                Config.set(request, Config.FMT_LOCALE, new java.util.Locale("be", "BY"));
                ResourceManager.INSTANCE.changeResource(new Locale("be", "BY"));
                request.getSession().setAttribute(AttributeEnum.LANG.getValue(), "be_BY");
                break;
            case "ru_RU":
                logger.info("set page on ru_RU");
                Config.set(request, Config.FMT_LOCALE, new java.util.Locale("ru", "RU"));
                ResourceManager.INSTANCE.changeResource(new Locale("ru", "RU"));
                request.getSession().setAttribute(AttributeEnum.LANG.getValue(), "ru_RU");
                break;
            case "en_US":
                logger.info("set page on en_US");
                Config.set(request, Config.FMT_LOCALE, new java.util.Locale("en", "US"));
                ResourceManager.INSTANCE.changeResource(new Locale("en", "US"));
                request.getSession().setAttribute(AttributeEnum.LANG.getValue(), "en_US");
                break;
            default:
                logger.info("set default be_BY");
                Config.set(request, Config.FMT_LOCALE, new java.util.Locale("be", "BY"));
                ResourceManager.INSTANCE.changeResource(new Locale("be", "BY"));
                request.getSession().setAttribute(AttributeEnum.LANG.getValue(), "be_BY");
        }
    }
     @Override
     public void langSwitch(HttpServletRequest request) {
        String lang = null;
        if (null != request.getParameter(AttributeEnum.LANG.getValue())) {
            logger.info(request.getParameter(AttributeEnum.LANG.getValue()));
            lang = request.getParameter(AttributeEnum.LANG.getValue()).toString();
            langDef(lang, request);
        } else if (null != request.getSession().getAttribute(AttributeEnum.LANG.getValue())) {
            logger.info(request.getSession().getAttribute(AttributeEnum.LANG.getValue()));
            lang = request.getSession().getAttribute(AttributeEnum.LANG.getValue()).toString();
            langDef(lang, request);
        } else {
            logger.info("no such session attribute, set default be_BY");
            Config.set(request, Config.FMT_LOCALE, new java.util.Locale("be", "BY"));
            ResourceManager.INSTANCE.changeResource(new Locale("be", "BY"));
            request.getSession().setAttribute(AttributeEnum.LANG.getValue(), "be_BY");
        }
    }
}
