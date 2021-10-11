package com.nary.tests.narytreetraversal.core;

import com.nary.tests.narytreetraversal.model.Browser;
import com.nary.tests.narytreetraversal.utils.SystemPropertyUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final String GECKO_DRIVER = "webdriver.gecko.driver";
    private static final String CHROME_DRIVER = "webdriver.chrome.driver";

    public static synchronized WebDriver createDriver(String browser){
        WebDriver webDriver = null;
        final Browser browserType = Browser.getBrowserByString(browser);
        switch (browserType) {
            case FIREFOX:
                System.setProperty(GECKO_DRIVER, SystemPropertyUtil.getGeckoDriverPath());
                System.setProperty("webdriver.http.factory", "apache");

                ProfilesIni profile = new ProfilesIni();
                FirefoxProfile testprofile = profile.getProfile("test");
                testprofile.setAcceptUntrustedCertificates(true);
                testprofile.setAssumeUntrustedCertificateIssuer(true);
                testprofile.setPreference("security.insecure_field_warning.contextual.enabled", false);

                DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
                desiredCapabilities.setCapability(FirefoxDriver.PROFILE, testprofile);

                webDriver =  new FirefoxDriver(desiredCapabilities);
                break;
            case CHROME:
                System.setProperty(CHROME_DRIVER, SystemPropertyUtil.getChromeDriverPath());
                System.setProperty("webdriver.http.factory", "apache");
                webDriver=new ChromeDriver();
                break;
        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return new EventFiringWebDriver(webDriver);
    }

    public static void closeBrowser(WebDriver driver) {
        driver.quit();
    }

}

