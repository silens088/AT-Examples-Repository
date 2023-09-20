package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiConfigLoader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            InputStream stream = ApiConfigLoader.class.getClassLoader().getResourceAsStream("ApiConfig.properties");
            if (stream == null) {
                throw new IOException("File 'ApiConfig.properties' not found in the resources directory");
            }
            properties.load(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
