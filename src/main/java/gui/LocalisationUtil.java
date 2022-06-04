package gui;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalisationUtil {
    private static final String BUNDLE_NAME = "localisations";
    private static Locale locale = Locale.getDefault();

    public static String getText(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        return resourceBundle.getString(key);
    }

    public static void setLocale(Locale locale){
        if (locale != null) {
            LocalisationUtil.locale = locale;
        }
    }

}
