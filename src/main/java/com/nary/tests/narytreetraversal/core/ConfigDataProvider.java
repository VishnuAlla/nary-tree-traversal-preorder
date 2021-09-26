package com.nary.tests.narytreetraversal.core;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {
    Properties prop;
    FileInputStream configFile;
    public ConfigDataProvider(){
        try {
            configFile = new FileInputStream("src/main/resources/config.properties");
            prop = new Properties();
            prop.load(configFile);
        }
        catch (Exception e) {
            System.out.println("Error details: "+e.getMessage());
        }
    }

    public String getApplicationUrl() {
        String url=prop.getProperty("url");
        return url;
    }

    public String getBrowser(){
        String browser=prop.getProperty("browser");
        return browser;
    }
}
