package common;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationUtil {
    private static final String BUNDLE_NAME = "localizations";
    private static Locale locale = Locale.getDefault();

    public static String getText(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        return resourceBundle.getString(key);
    }

    public static void setLocale(Locale locale){
        if (locale != null) {
            LocalizationUtil.locale = locale;
        }
    }
}