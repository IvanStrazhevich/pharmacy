package by.epam.pharmacy.service;

public interface LanguageSwitchable<T> {
    /**
     * Changes default locale for app and client side
     *
     * @param t request or content
     */
    void langSwitch(T t);
}

