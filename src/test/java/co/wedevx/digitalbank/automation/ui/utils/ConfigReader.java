package co.wedevx.digitalbank.automation.ui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private  static Properties properties;
    //static initializer run the block only once for the whole project
    //instance initializer runs the block for every obj creation for the class
    static  {
        //filepath -> is a directory of your properties file
        String filePath = "src/test/resources/properties/digitalbank.properties";

        //this is a class that enabled you to read files

        FileInputStream input = null;

        try {
            input = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(input);
            input.close();

        } catch (IOException e) {
            System.out.println("File not found");
        }
        finally {
            try {
                assert input != null;
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getPropertiesValue(String key) {


        return properties.getProperty(key);
    }

}
