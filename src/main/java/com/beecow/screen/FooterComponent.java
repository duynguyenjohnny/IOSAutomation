package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import io.appium.java_client.AppiumDriver;

import static com.beecow.model.FooterElement.*;

/**
 * Created by Phuoc Ha on 01/10/2017.
 */

public class FooterComponent extends CommonScreenObjects {

    public FooterComponent(AppiumDriver driver){
        super(driver);
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
