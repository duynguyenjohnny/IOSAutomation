package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import io.appium.java_client.AppiumDriver;

import static com.beecow.model.HomeElement.getActivityMainLocator;

/**
 * Created by PhuocHa on 01/10/2017.
 */

public class HomeScreen extends CommonScreenObjects
{
    public FooterComponent footerComponent;
    public HomeScreen(AppiumDriver driver){
        super(driver);
        footerComponent = new FooterComponent(driver);
    }

    public void clickHomeTabView() {
        footerComponent.clickHomeTabView();
    }

    public void clickMarketTabView() {
        footerComponent.clickMarketTabView();
    }

    public void clickCupidTabView() {
        footerComponent.clickCupidTabView();
    }

    public void clickMessagesTabView() {
        footerComponent.clickMessagesTabView();
    }

    public void verifyHomePageDisplayed() {
        getHelper().waitElementIsDisplayed(getHelper().byLocator(getActivityMainLocator()));
//        getHelper().waitElementIsDisplayed(getHelper().byLocator(getActivityMainLocator()));
        System.out.println("Home Page show with activity main page is loaded");
    }
}