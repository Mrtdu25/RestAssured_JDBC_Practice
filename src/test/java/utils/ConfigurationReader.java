package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    public static Properties properties=new Properties();

    static {
        try {
            FileInputStream file=new FileInputStream("Configuration.properties");
            properties.load(file);
            file.close();
        } catch (Exception e) {
            System.out.println("Properties not found "+e.getMessage());
            e.printStackTrace();
        }

    }

    public static String getProperty(String keyWord){

        return properties.getProperty(keyWord);
    }




}
