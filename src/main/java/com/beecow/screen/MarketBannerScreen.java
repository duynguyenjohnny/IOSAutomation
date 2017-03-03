package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import io.appium.java_client.AppiumDriver;

import static com.beecow.model.CommonElement.failed;
import static com.beecow.model.MarketBannerElement.*;
import org.testng.Assert;

/**
 * Created by hangpham on 2017-02-07.
 */
public class MarketBannerScreen extends CommonScreenObjects{

    public MarketBannerScreen(AppiumDriver driver) {
        super(driver);
    }

    public void verifyBannerDisplayed(){
        getHelper().isElementEnabled(getImageBannerID());
    }

    public void swipeBannerLeft(){
        getHelper().findElement(getImageBannerID());
        getSwipe().swipeRightToLeftElement(getHelper().findElement(getImageBannerID()));
    }

    public void swipeBannerRight(){
        getHelper().findElement(getImageBannerID());
        getSwipe().swipeLeftToRightElement(getHelper().findElement(getImageBannerID()));
    }

}
