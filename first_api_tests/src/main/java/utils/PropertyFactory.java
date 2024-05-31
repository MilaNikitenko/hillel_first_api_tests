package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for loading properties from a file.
 */

public class PropertyFactory {
    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream input = PropertyFactory.class.getClassLoader().getResourceAsStream("test.properties")) {
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find " + "test.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error loading properties file: " + "test.properties", ex);
        }
    }
    public static String getBaseUriLinkProperty() {
        return properties.getProperty("baseUriLink");
    }
    public static String getColleaguePath() {
        return properties.getProperty("colleaguePath");
    }
    public static String getRegisterPath() {
        return properties.getProperty("registerPath");
    }
}
