package com.nary.tests.narytreetraversal.utils;

public class SystemPropertyUtil {
    private static final String GECKO_DRIVER = "GeckoDriver";
    private static final String CHROME_DRIVER = "ChromeDriver";

    public static String getGeckoDriverPath() {
        return getEnvironmentalVariable(GECKO_DRIVER);
    }

    public static String getChromeDriverPath() {
        return getEnvironmentalVariable(CHROME_DRIVER);
    }

    private static String getEnvironmentalVariable(String propertyName) {
        final String getenv = System.getenv(propertyName);
        return getenv;
    }
}
