package by.epam.pharmacy.service;

public interface LanguageSwitchable<T> {
    /**
     * @param request
     */
    void langSwitch(T request);
}

