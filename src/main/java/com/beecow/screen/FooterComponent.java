package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.NoSuchElementException;
import org.testng.ITestResult;
import org.testng.Reporter;

import static com.beecow.model.CommonElement.failed;
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

    public void clickMarketTabView() throws Exception {
        try {
            getHelper().findElement(getTabMarketLocator()).click();

        } catch (NoSuchElementException noElement) {
            result.setResult(failed);
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[VerifyStatusOfChooseButton] Can't find Element: " + noElement.getMessage());
        } catch (Exception ex) {
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception(ex.getMessage());
        }
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
