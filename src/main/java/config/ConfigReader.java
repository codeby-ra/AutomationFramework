package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    public static void loadProperties() {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
            System.out.println("✅ config.properties loaded successfully.");
        } catch (IOException e) {
            System.err.println("❌ Failed to load config.properties: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) throw new RuntimeException("❗ Property not found in config: " + key);
        return value;
    }
}
