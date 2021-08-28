package com.sdmctech.offline.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: Larkin
 * @Date: 2021/6/15 18:21
 */
public class PropertiesUtil {
    public  Properties initProperties(String propertiesFilePath){
        Properties properties = new Properties ();
        System.out.println (propertiesFilePath);
        try  {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return properties;
    }
}
