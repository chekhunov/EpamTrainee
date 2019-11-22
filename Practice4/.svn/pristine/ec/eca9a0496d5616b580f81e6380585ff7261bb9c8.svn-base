package ua.nure.chekhunov.practice4;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Implementation "ResourceProperty".<br>
 * Getter value by key of the resource bundle.
 *
 * @author chekhunov.
 */
public class ResourceProperty {
    /**
     * Object ResourceBundle.
     */
    private final ResourceBundle bundle;

    /**
     * Constructor with parameters.
     *
     * @param baseName
     *            - base name of the resource bundle.
     * @param locale
     *            - locale of the resource bundle.
     */
    public ResourceProperty(final String baseName, final Locale locale) {
        bundle = ResourceBundle.getBundle(baseName, locale);
    }

    /**
     * Get value key of the resource bundle.
     *
     * @param key
     *            key of the resource bundle.
     * @return String key resource bundle.
     */
    public String getValue(final String key) {
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        } else {
            return null;
        }
    }
}
