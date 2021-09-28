package com.nary.tests.narytreetraversal.narytree_locators;

import org.openqa.selenium.By;

public class NaryTree {
    public static final By OPEN_BEACON=By.xpath("//button[@id='area-to-close']");
    public static final By WIDGET_BUTTON_FRAME=By.xpath("//iframe[contains(@class,'solvemate solvemate-widget-button solvemate-show')]");
    public static final By WIDGET_HIDE_FRAME=By.xpath("//iframe[contains(@class,'solvemate solvemate-widget solvemate-hide update-translation') and contains(@title,'Solvemate Widget')]");
    public static final By OPTIONS=By.xpath("//div[@class='choice active']/div/button/div");
    public static final By THUMBS_UP=By.xpath("//button[@class='sc-hKFxyN sc-eJocfa sc-amiJK iKcknJ gkyOsE fZjFyC']/div/span[@class='thumbs-up']");
    public static final By LAST_SELECTED_OPTION=By.xpath("//div[@class='choice selected active']/div/button[@class='sc-hKFxyN sc-eJocfa sc-amiJK iKcknJ cGrYkE eGHtCG']/div");
    public static final By SUBMIT_BUTTON=By.xpath("//button[@class='sc-hKFxyN sc-eJocfa sc-amiJK iKcknJ gkyOsE fZjFyC']/div[contains(text(),'Submit')]");
    public static final By MESSAGE_FIELD=By.id("message");
    public static final By EMAIL_FIELD=By.id("email");
}
