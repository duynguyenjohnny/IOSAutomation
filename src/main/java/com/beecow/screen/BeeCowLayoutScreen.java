package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import io.appium.java_client.AppiumDriver;

import static com.beecow.model.BeeCowLayoutElement.*;

/**
 * Created by Phuoc Ha on 01/10/2017.
 */

public class BeeCowLayoutScreen extends CommonScreenObjects {

    public BeeCowLayoutScreen(AppiumDriver driver){
        super(driver);
    }

    // Header

    public void search(String value) {
        clickInputSearch();
        InputTextSearch(value);
    }

    public void waitUntilSearchHeaderIsDisplayed() {
        getHelper().waitElementByID(getInputSearchLocator());
    }

    public void clickInputSearch() {
        getHelper().findElement(getInputSearchLocator()).click();
    }

    public void InputTextSearch(String value) {
        getHelper().findElement(getInputSearchLocator()).sendKeys(value);
    }

    public void clickIvMenu() {
        getHelper().findElement(getIvMenuLocator()).click();
    }

    // Footer

    public void clickHomeTabView() {
        getHelper().findElement(getTabHomeLocator()).click();
    }

    public void clickMarketTabView() {
        getHelper().findElement(getTabMarketLocator()).click();
    }

    public void clickMessagesTabView() {
        getHelper().findElement(getTabMessagesLocator()).click();
    }

    public void clickCupidTabView() {
        getHelper().findElement(getTabCupidLocator()).click();
    }

    public void clickProfileTabView() {
        getHelper().findElement(getTabProfileLocator()).click();
    }
}
