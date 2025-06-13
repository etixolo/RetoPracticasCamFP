package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getResourceAsStream("/config.properties")) {
            if (input == null) throw new RuntimeException("config.properties no encontrado");
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error cargando configuración", e);
        }
    }

    public static String getDbUrl() { return props.getProperty("db.url"); }
    public static String getDbUser() { return props.getProperty("db.user"); }
    public static String getDbPassword() { return props.getProperty("db.password"); }
    public static String getApiKey() { return props.getProperty("api.key"); }
}
