package common;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Klasa lokalizacyjna dla projektu.
 */
public class LocalizationUtil {
    private static final String BUNDLE_NAME = "localizations";
    private static Locale locale = Locale.getDefault();

    /**
     * Pobiera tekst lokalizacji.
     * @param key Klucz lokalizacji.
     * @return Tekst w odpowiednim języku dla lokalizacji.
     */
    public static String getText(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        return resourceBundle.getString(key);
    }

    /**
     * Ustawia lokalizację projektu.
     * @param locale Lokalizacja.
     */
    public static void setLocale(Locale locale){
        if (locale != null) {
            LocalizationUtil.locale = locale;
        }
    }
}