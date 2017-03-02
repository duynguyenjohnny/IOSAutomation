package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import com.beecow.utils.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.*;

import static com.beecow.model.MarketCategoriesElement.getMainViewWithSearchAndFooter;

/**
 * Created by hangpham on 2017-03-01.
 */
public class MarketCategoriesScreen extends CommonScreenObjects {


    public MarketCategoriesScreen(AppiumDriver driver) {
        super(driver);
    }

    public void swipeToElementThenStop() {
        WebElement element = getHelper().findElement(getMainViewWithSearchAndFooter());

        for (int i = 0; i < 10; i++) {
            if (getHelper().isElementPresent("text::Spotlights")) {
                System.out.println("Found element!!!");
                WebElement el = getHelper().findElement("text::Spotlights");
                swipe.swipingDownFromElementToBottom(el, element);
                break;
            } else {
                swipe.swipingUpFromBottomToTop(element);
            }
        }
        for (int i = 0; i < 10; i++) {
            if (getHelper().isElementPresent("text::Categories")) {
                System.out.println("Found element!!!");
                WebElement el = getHelper().findElement("text::Categories");
                swipe.swipingDownFromElementToBottom(el, element);
                break;
            } else {
                swipe.swipingDownFromBottomToTop(element);
            }
        }
    }
}


