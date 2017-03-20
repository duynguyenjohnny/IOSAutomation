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
    String sNameMethod;
    public FooterComponent(AppiumDriver driver){
        super(driver);
    }
    //check fail when not found element
    public void checkFail(String observation){
        result.setResult(failed);
        result.setObservation(observation);
        result.check();
    }
    // Footer
    public void clickHomeTabView() {
        result.setExpectation("Click Tab view [Home]");
        sNameMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            getHelper().findElement(getTabHomeLocator()).click();
        } catch (Exception ex) {
            checkFail("Tab view [Home] not found " + ex.getMessage());
        }
    }
    public void clickMarketTabView() throws Exception {
        result.setExpectation("Click Tab view [Market]");
        sNameMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            getHelper().findElement(getTabMarketLocator()).click();
        } catch (Exception ex) {
            checkFail("Tab view [Market] not found " + ex.getMessage());
        }
    }
    public void clickMessagesTabView() {
        result.setExpectation("Click Tab view [Messages]");
        sNameMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            getHelper().findElement(getTabMessagesLocator()).click();
        } catch (Exception ex) {
            checkFail("Tab view [Messages] not found " + ex.getMessage());
        }
    }
    public void clickCupidTabView() {
        result.setExpectation("Click Tab view [Cupid]");
        sNameMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            getHelper().findElement(getTabCupidLocator()).click();
        } catch (Exception ex) {
            checkFail("Tab view [Cupid] not found " + ex.getMessage());
        }
    }
    public void clickMoreTabView() {
        result.setExpectation("Click Tab view [More]");
        sNameMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            getHelper().findElement(getTabMoreLocator()).click();
        } catch (Exception ex) {
            checkFail("Tab view [More] not found " + ex.getMessage());
        }
    }
}