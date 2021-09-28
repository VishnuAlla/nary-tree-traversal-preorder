package com.nary.tests.narytreetraversal.narytree_pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.nary.tests.narytreetraversal.narytree_locators.NaryTree.*;

import java.util.List;

public class NaryTreeTraversalPage {
    WebDriver driver;
    String emailAddress="venusvishnu@gmail.com";

    public NaryTreeTraversalPage(WebDriver driver){this.driver=driver;}

    public void treeTraversal()  {
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(WIDGET_BUTTON_FRAME));
        WebElement beacon_button=driver.findElement(WIDGET_BUTTON_FRAME);
        driver.switchTo().frame(beacon_button);

        WebElement openButton=driver.findElement(OPEN_BEACON);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", openButton);
        //Clicking on the Beacon button
        openButton.click();
        System.out.println("Clicked on the chatbot");

        driver.switchTo().defaultContent();

        WebDriverWait wait1 = new WebDriverWait(driver,4);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(WIDGET_HIDE_FRAME));
        WebElement widget_hide=driver.findElement(WIDGET_HIDE_FRAME);
        driver.switchTo().frame(widget_hide);
        System.out.println("Switched to the chat iframe");

        List<WebElement> parent_node_options = driver.findElements(OPTIONS);
        huntThumbsUp(parent_node_options.get(0));
    }

    public void huntThumbsUp(WebElement option){
        //Checking if the current option is thumbs up or not
        //if it is thumbsup, print end of the flow message
        try{
        WebElement thumbsupIcon=option.findElement(THUMBS_UP);
        if(thumbsupIcon!=null){
            if(thumbsupIcon.getAttribute("class").equals("thumbs-up")){
                System.out.println("Found thumbs up--->End of the flow");
                return;
            }
        }}
        // if the option is not thumbs up button, cover all its child options and sibling/peer options
        catch (NoSuchElementException nse)
        {
            System.out.println("Called hunt on --> "+option.getText());
            WebElement lastClicked=processChild(option);
            if(lastClicked==null)
                return;
            processSibling(lastClicked);
        }
    }

    public void processSibling(WebElement option){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", option);
        option.click();
        List<WebElement> siblings = driver.findElements(OPTIONS);
        boolean next=false;
        for(WebElement sibling:siblings){
            if(next==true){
                huntThumbsUp(sibling);
                break;
            }
            if(sibling.getText().equals(option.getText()))
                next=true;
        }
    }

    public WebElement processChild(WebElement option){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", option);
        option.click();

        List<WebElement> clickedOptions = driver.findElements(LAST_SELECTED_OPTION);
        int clickedSize=clickedOptions.size()-1;
        WebElement lastClicked=clickedOptions.get(clickedSize);

        List<WebElement> children = driver.findElements(OPTIONS);
        if(children.size()==0) {
            return null;
        }
        if(children.size()==1){
            List<WebElement> submitButton=option.findElements(SUBMIT_BUTTON);
            if(submitButton.size()>0) {
                try{
                    WebElement emailField=driver.findElement(EMAIL_FIELD);
                    if(emailField!=null){
                        emailField.sendKeys(emailAddress);
                    }
                }catch (NoSuchElementException noSuchElementException) {}

                driver.findElement(MESSAGE_FIELD).sendKeys("Sample Message");
                submitButton.get(0).click();
                return lastClicked;
            }
        }
        WebElement child=children.get(0);
        huntThumbsUp(child);
        return lastClicked;
    }

}
