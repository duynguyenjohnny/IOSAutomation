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
     * Click on Cupid Tab in the Footer
     * @throws Exception
     */
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


    /**
     * Turn on or off the Cupid Feature
     * @param on true or false
     * @throws Exception
     */
    public void TurnCupidFeatureOnOff(boolean on) throws Exception{
        try{
            if (on){
                if (getHelper().isElementPresent(CupidElement.switch_CupidFeatureOFF())){
                    WebElement WEcupidFeatureOFF = getHelper().findElement(CupidElement.switch_CupidFeatureOFF());
                    WEcupidFeatureOFF.click();
                }else{
                    System.out.println("Cupid feature is already ON");
                }
            }else{
                if (getHelper().isElementPresent(CupidElement.switch_CupidFeatureON())){
                    WebElement WEcupidFeatureON = getHelper().findElement(CupidElement.switch_CupidFeatureON());
                    WEcupidFeatureON.click();
                }else{
                    System.out.println("Cupid feature is already OFF");
                }
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("FAILED: " + ex.getMessage());
        }

    }

}