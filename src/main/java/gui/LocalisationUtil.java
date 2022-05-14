package gui;

import java.util.ResourceBundle;

public class LocalisationUtil {
    private static final String BUNDLE_NAME = "localisations";

    public static String getText(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        return resourceBundle.getString(key);
    }

}
