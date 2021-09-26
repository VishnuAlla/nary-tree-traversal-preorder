package com.nary.tests;

import com.nary.tests.narytreetraversal.core.BuildFactory;
import com.nary.tests.narytreetraversal.core.DriverFactory;
import com.nary.tests.narytreetraversal.narytree_pages.NaryTreeTraversalPage;
import com.nary.tests.narytreetraversal.utils.Helper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class NaryTreeTraversalTest {

    WebDriver driver;
    ExtentReports report;
    ExtentTest logger;

    @BeforeMethod
    public void setUp() {
        report = new ExtentReports("./Reports/NaryTreeTraversalReport.html", true);
        logger=report.startTest("Nary Tree Traversal");
        driver = DriverFactory.createDriver(BuildFactory.ConfigObject().getBrowser());
        driver.get(BuildFactory.ConfigObject().getApplicationUrl());
        logger.log(LogStatus.PASS, "Browser Launched");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test(description = "This test performs the traversal through all the possible paths in the decision tree")
    public void verifyNaryTreeTraversal() {
        NaryTreeTraversalPage naryTreeTraversalPage = PageFactory.initElements(driver, NaryTreeTraversalPage.class);
        naryTreeTraversalPage.treeTraversal();
        logger.log(LogStatus.PASS, "Covered all the paths in the decision tree");
    }

    @AfterMethod
    public void exit(ITestResult result) {
        if(result.getStatus()== ITestResult.FAILURE) {
            String path = Helper.captuteScreenshot(driver, result.getName());
            logger.log(LogStatus.FAIL, logger.addScreenCapture(path));
        }
        DriverFactory.closeBrowser(driver);
        report.endTest(logger);
        report.flush();
    }
}
