package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import com.beecow.model.CupidElement;
import com.beecow.utils.Result;
import com.beecow.utils.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static com.beecow.model.CommonElement.failed;
import static com.beecow.model.MarketCategoriesElement.*;

/**
 * Created by hangpham on 2017-03-01.
 */
public class MarketCategoriesScreen extends CommonScreenObjects {

    String line = "--------------------------------------------------";
    String sNameMethod;

    public MarketCategoriesScreen(AppiumDriver driver) {
        super(driver);
    }

    //check fail when not found element
    public void checkFail(String observation){
        result.setResult(failed);
        result.setObservation(observation);
        result.check();
    }

    public void clickSeeAllBtnAtCate() throws Exception {
        sNameMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            getHelper().findElement(getSeeAllBtnCateLevel0()).click();
//            getHelper().findElement("resourceID::124").click();
        } catch (Exception ex) {
            checkFail(ex.getMessage());
            throw new Exception("Can not find element - " + ex.getMessage());
        }
    }

    public void checkCategoriesSectionExpand(){
        //wipe to see all
//        swipingToElemntThenStop(getHelper().findElement(getSeeAllBtnCateLevel0()));

        result.setExpectation("Categories expand full item product when user clicks [See all]");
        if(!getHelper().findElement("resourceID::fragment_market_tv_see_more").getText().equals("See less")){
            result.setResult(failed);
            result.setObservation("[See less] text does not exist!!!");
            result.setExpectation("[See less] should be shown when users click [See all]");
        }
        result.check();
    }

    

    public void swipingToElemntThenStop(WebElement element){
        WebElement element1 = getHelper().findElement(getMainViewWithSearchAndFooter());

        for (int i = 0; i < 10; i++) {
            if (getHelper().isElementPresent("text::See less")) {
                System.out.println("Found element!!!");
                break;
            } else {
                getSwipe().swipingUpFromBottomToTop(element);
            }
        }
    }
    public void swipeToElementAndScrollElementToTopThenStopDemo() {
        WebElement element = getHelper().findElement(getMainViewWithSearchAndFooter());

        for (int i = 0; i < 10; i++) {
            if (getHelper().isElementPresent("text::Spotlights")) {
                System.out.println("Found element!!!");
                WebElement el = getHelper().findElement("text::Spotlights");
                getSwipe().swipingDownFromElementToBottom(el, element);
                break;
            } else {
                getSwipe().swipingUpFromBottomToTop(element);
            }
        }
        for (int i = 0; i < 10; i++) {
            if (getHelper().isElementPresent("text::Categories")) {
                System.out.println("Found element!!!");
                WebElement el = getHelper().findElement("text::Categories");
                getSwipe().swipingDownFromElementToBottom(el, element);
                break;
            } else {
                getSwipe().swipingDownFromBottomToTop(element);
            }
        }
    }
    public void testswipe(){
        getSwipe().swipeLeftToRightElement(getHelper().findElement("resourceID::item_banner_img_banner"));
    }
}


