package by.epam.pharmacy.service;

import by.epam.pharmacy.web.AttributeEnum;
import by.epam.pharmacy.web.PagesEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

public class WelcomePageHandler implements RequestHandler {
    private static final String MESSAGE = "message.needLogin";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        langDefinition(request);
        String page = null;
        if (request.getSession().getAttribute(AttributeEnum.LOGGED.getValue()) != null) {
            page = PagesEnum.WELCOME_PAGE.getValue();
        } else {
            request.setAttribute(AttributeEnum.NEED_LOGIN.getValue(), ResourceManager.INSTANCE.getString(MESSAGE));
            page = PagesEnum.LOGIN_PAGE.getValue();
        }
        return page;
    }

    static void langDefinition(HttpServletRequest request) {
        if (null != request.getSession().getAttribute(AttributeEnum.LANG.getValue())) {
            switch (request.getSession().getAttribute(AttributeEnum.LANG.getValue()).toString()) {
                case "be_BY":
                    Config.set(request, Config.FMT_LOCALE, new java.util.Locale("be", "BY"));
                    ResourceManager.INSTANCE.changeResource(new Locale("be", "BY"));
                    request.getSession().setAttribute(AttributeEnum.LANG.getValue(), "be_BY");
                    break;
                case "ru_RU":
                    Config.set(request, Config.FMT_LOCALE, new java.util.Locale("ru", "RU"));
                    ResourceManager.INSTANCE.changeResource(new Locale("ru", "RU"));
                    request.getSession().setAttribute(AttributeEnum.LANG.getValue(), "ru_RU");
                    break;
                case "en_US":
                    Config.set(request, Config.FMT_LOCALE, new java.util.Locale("en", "US"));
                    ResourceManager.INSTANCE.changeResource(new Locale("en", "US"));
                    request.getSession().setAttribute(AttributeEnum.LANG.getValue(), "en_US");
                    break;
                    default:
                        Config.set(request, Config.FMT_LOCALE, new java.util.Locale("be", "BY"));
                        ResourceManager.INSTANCE.changeResource(new Locale("be", "BY"));
                        request.getSession().setAttribute(AttributeEnum.LANG.getValue(), "be_BY");

            }
        } else {
            Config.set(request, Config.FMT_LOCALE, new java.util.Locale("be", "BY"));
            ResourceManager.INSTANCE.changeResource(new Locale("be", "BY"));
            request.getSession().setAttribute(AttributeEnum.LANG.getValue(), "be_BY");
        }
    }
}
