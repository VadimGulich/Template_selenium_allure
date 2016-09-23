package autoTests;

import org.testng.log4testng.Logger;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigurationVariables {

    private static final ConfigurationVariables instance = null;
    final static Logger logger = Logger.getLogger(ConfigurationVariables.class);

    private static String configFilePath = "src/test/resources/config.properties";
    private static String testDataFilePath = "src/test/resources/data.properties";
    private static Properties configurationData = new Properties();
    private static Properties testData = new Properties();

    static {
        fillMyProperties(configurationData, configFilePath);
        fillMyProperties(testData, testDataFilePath);
    }


    public static final int technicalPause = Integer.parseInt(getProperty(configurationData, "technicalPause"));
    public static final int implicitTimeWait = Integer.parseInt(getProperty(configurationData, "implicitTimeWait"));
    public static final int waitPageForLoad = Integer.parseInt(getProperty(configurationData, "waitPageForLoad"));
    public static final int longPause = Integer.parseInt(getProperty(configurationData, "longPause"));
    public static final String  baseUrl = getProperty(configurationData, "baseurl");

    public static final String  email = getProperty(testData, "email");


    private static void fillMyProperties(Properties properties, String filePath) {
        InputStreamReader input;
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(filePath);
            input = new InputStreamReader(fileInputStream, "UTF8");

            properties.load(input);
        } catch (java.io.FileNotFoundException e) {
            logger.fatal("Ошибка" + e);
        } catch (java.io.IOException e) {
            logger.fatal("Ошибка" + e);
        }
    }

    private static String getProperty(Properties properties, String propertyKey) {

        return properties.getProperty(propertyKey).toString();
    }

    public static ConfigurationVariables getInstance() {
        return instance;
    }
}
