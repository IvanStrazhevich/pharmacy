package by.epam.pharmacy.service;

import by.epam.pharmacy.web.PagesEnum;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterPageHandler implements RequestHandler {
    private LanguageSwitchable languageSwitcher = new LanguageSwitcher();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        languageSwitcher.langSwitch(request);
        return PagesEnum.REGISTER_PAGE.getValue();
    }

    public void setLanguageSwitcher(LanguageSwitchable languageSwitcher) {
        this.languageSwitcher = languageSwitcher;
    }
}
