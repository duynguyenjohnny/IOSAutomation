package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import io.appium.java_client.AppiumDriver;

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

    public void clickMarketTabView() {
        footerComponent.clickMarketTabView();
    }
}