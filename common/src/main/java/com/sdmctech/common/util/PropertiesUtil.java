package com.sdmctech.common.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    public static Properties initProperties(String propertiesFilePath){
        Properties properties = new Properties ();
        System.out.println (propertiesFilePath);
        try  {

            properties.load(PropertiesUtil.class.getClass().getResourceAsStream(propertiesFilePath));
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return properties;
    }
}
