package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import com.beecow.model.CupidElement;
import com.beecow.utils.Result;
import com.beecow.utils.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.Reporter;

import static com.beecow.model.CommonElement.failed;
import static com.beecow.model.MarketCategoriesElement.*;

/**
 * Created by hangpham on 2017-03-01.
 */
public class MarketCategoriesScreen extends CommonScreenObjects {


    public MarketCategoriesScreen(AppiumDriver driver) {
        super(driver);
    }

    public void clickSeeAllBtnAtCate() throws Exception {
        try{
            getHelper().findElement(getSeeAllBtnCateLevel0()).click();
        }
        catch (Exception ex){
            result.setResult(failed);
            throw new Exception("Can not find element - "+ex.getMessage());
        }
    }

    public void checkCategoriesSectionExpand(){
        result.setExpectation("");


        result.check();
    }
    public void VerifyStatusOfChooseButton(boolean enabled) throws Exception {
        String kwName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            if (enabled) {
                if (getHelper().isElementPresent(CupidElement.btn_ChooseEnabled())) {
                    System.out.println("[VerifyStatusOfChooseButton] success: Expected [enabled], Actual [enbaled]");
                } else {
                    Result.Fail(kwName, "Expected [Enable], Actual [Disabled or Not Exist]");
                }
            } else {
                if (getHelper().isElementPresent(CupidElement.btn_ChooseDisabled())) {
                    System.out.println("VerifyStatusOfChooseButton success: Expected [Disabled], Actual [Disabled]");
                } else {
                    Result.Fail(kwName, "Expected [Disabled], Actual [Enabled or Not Exist]");
                }
            }
        } catch (NoSuchElementException noElement) {
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[VerifyStatusOfChooseButton] Can't find Element: " + noElement.getMessage());
        } catch (Exception ex) {
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception(ex.getMessage());
        }
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
    public void testswipe(){
        swipe.swipeLeftToRightElement(getHelper().findElement("resourceID::item_banner_img_banner"));
    }
}


