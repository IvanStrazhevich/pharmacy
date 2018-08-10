package by.epam.pharmacy.util;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResourceManager {
    INSTANCE;
    private ResourceBundle resourceBundle;
    private final String resourceName = "message";

    /**
     *
     */
    ResourceManager() {
        resourceBundle = ResourceBundle.getBundle(resourceName, Locale.getDefault());
    }

    /**
     * @param locale
     */
    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourceName, locale);
    }

    /**
     * @param key
     */
    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}
