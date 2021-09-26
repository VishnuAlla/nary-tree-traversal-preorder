package com.nary.tests.narytreetraversal.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Helper {
    public static String captuteScreenshot(WebDriver driver, String screenshotname) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        String destFile = "./Screenshots/"+screenshotname+System.currentTimeMillis()+".png";
        try {
            FileUtils.copyFile(srcFile, new File(destFile));
        } catch (IOException e) {
            System.out.println("Error during capturing screenshot"+e.getMessage());
        }
        return destFile;
    }
}
