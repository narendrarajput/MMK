package com.mmk.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {
	static Properties prop = new Properties();
    static InputStream input = null;
    static String value = "";

    public static String getProperty(String key) {
        try {

            input = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/mmk/config/TestDataConfig.properties");
            prop.load(input);
            value = prop.getProperty(key);


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

}
