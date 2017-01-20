package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import com.beecow.model.CupidElement;
import io.appium.java_client.AppiumDriver;
import org.jboss.netty.channel.ExceptionEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.Reporter;
import testlink.api.java.client.TestLinkAPIException;

import java.util.HashMap;

import static com.beecow.component.Constant.*;
import static com.beecow.model.CommonElement.failed;
import static com.beecow.model.CommonElement.screenShot_login;
import static com.beecow.model.CupidElement.*;
import static com.beecow.model.CommonElement.*;


public class CupidScreen extends CommonScreenObjects{

    public CupidScreen(AppiumDriver driver){
        super(driver);

    }

    /**
     * report pass/fail for each screen
     * @param testCaseID need to update result
     */
    public void checkReportTestLinkLogin(String testCaseID) {
        String sResult = result.getResult();
        if (sResult.equals("p")) {
            try {
                System.out.println("Result: " + sResult);
                getHelper().takeScreenshot(screenShot_login,"Cupid_passed\\", testCaseID);
                getHelper().updateTestLinkResult(TEST_CUPID_PROJECT, TEST_CUPID_PLAN, testCaseID, TEST_CUPID_BUILD, null, sResult);
            } catch (TestLinkAPIException e) {
                e.printStackTrace();
            }

        } else {
            try {
                System.out.println("Result: " + sResult);
                getHelper().takeScreenshot(screenShot_login,"Cupid_failed\\", testCaseID);
                getHelper().updateTestLinkResult(TEST_CUPID_PROJECT, TEST_CUPID_PLAN, testCaseID, TEST_CUPID_BUILD, null, sResult);
            } catch (TestLinkAPIException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }


    public void clickCupidTab() throws Exception{
        try{
            WebElement WEcupidTab = getHelper().findElement(CupidElement.tab_Cupid());
            WEcupidTab.click();
            System.out.print("Click on Cupid Tab successfully");
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("Can't find Element: " + CupidElement.tab_Cupid() + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("FAILED: " + ex.getMessage());
        }

    }

}