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
            System.out.println("Start - Click on Cupid Tab");
            WebElement WEcupidTab = getHelper().findElement(CupidElement.tab_Cupid());
            WEcupidTab.click();
            System.out.println("End - Click on Cupid Tab successfully");
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[clickCupidTab] - Can't find Element: " + CupidElement.tab_Cupid() + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[clickCupidTab] - FAILED: " + ex.getMessage());
        }

    }

    /**
     * Turn on or off the Cupid Feature
     * @param on true mean turn on, false mean turn off
     * @throws Exception
     */
    public void TurnCupidFeatureOnOff(boolean on) throws Exception{
        try{
            if (on){
                if (getHelper().isElementPresent(CupidElement.switch_CupidFeatureOFF())){
                    System.out.println("Cupid feature is OFF, turn it ON");
                    WebElement WEcupidFeatureOFF = getHelper().findElement(CupidElement.switch_CupidFeatureOFF());
                    WEcupidFeatureOFF.click();
                }else{
                    System.out.println("Cupid feature is already ON");
                }
            }else{
                if (getHelper().isElementPresent(CupidElement.switch_CupidFeatureON())){
                    System.out.println("Cupid feature is ON, turn it OFF");
                    WebElement WEcupidFeatureON = getHelper().findElement(CupidElement.switch_CupidFeatureON());
                    WEcupidFeatureON.click();
                }else{
                    System.out.println("Cupid feature is already OFF");
                }
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[TurnCupidFeatureOnOff] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[TurnCupidFeatureOnOff] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Verify hint Cupid: Would you like to make new friends? Turn on Cupid feature exist or not exist
     * @param exist true mean exist, false mean not exist
     * @throws Exception
     */
    public void VerifyCupidHint(boolean exist) throws Exception{
        try{
            if (exist){
                if (getHelper().isElementPresent(CupidElement.hint_TurnONCupid())){
                    System.out.println("[VerifyCupidHint] success: Expected [exist], Actual [Exist]");
                }else{
                    Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                    throw new Exception("[VerifyCupidHint] FAILED: Expected [exist], Actual [Not Exist]");
                }
            }else{
                if (getHelper().isElementPresent(CupidElement.hint_TurnONCupid())){
                    Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                    throw new Exception("[VerifyCupidHint] FAILED: Expected [Not Exist], Actual [Exist]");
                }else{
                    System.out.println("VerifyCupidHint success: Expected [Not Exist], Actual [Not Exist]");
                }
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[VerifyCupidHint] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[VerifyCupidHint] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Input My Alias  in screen Create Cupid Profile
     * @param aliasName Alias name need to input
     * @throws Exception
     */
    public void InputMyAlias(String aliasName) throws Exception{
        try{
            WebElement WEinput_CupidAlias = getHelper().findElement(CupidElement.input_CupidAlias());
            WEinput_CupidAlias.sendKeys(aliasName);
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[InputMyAlias] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[InputMyAlias] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Select Gender  in screen Create Cupid Profile
     * @param Gender: Man or Woman
     * @throws Exception
     */
    public void SelectGender(String Gender) throws Exception{
        try{
            if (Gender.toLowerCase().equals("man")) {
                if (getHelper().isElementPresent(CupidElement.select_CupidGender_Man())){
                    WebElement WEselect_CupidGender_Man = getHelper().findElement(CupidElement.select_CupidGender_Man());
                    WEselect_CupidGender_Man.click();
                }
            }else if(Gender.toLowerCase().equals("woman")){
                if (getHelper().isElementPresent(CupidElement.select_CupidGender_Woman())){
                    WebElement WEselect_CupidGender_Woman = getHelper().findElement(CupidElement.select_CupidGender_Woman());
                    WEselect_CupidGender_Woman.click();
                }
            }else{
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[SelectGender] Input Parameter Failed: Gender must be Man or Woman");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[SelectGender] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[SelectGender] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Select Looking For in screen Create Cupid Profile
     * @param LookingFor Man or Woman or Both
     * @throws Exception
     */
    public void SelectLookingFor(String LookingFor) throws Exception{
        try{
            if (LookingFor.toLowerCase().equals("man")) {
                if (getHelper().isElementPresent(CupidElement.select_CupidLookingFor_Man())){
                    WebElement WEselect_CupidLookingFor_Man = getHelper().findElement(CupidElement.select_CupidLookingFor_Man());
                    WEselect_CupidLookingFor_Man.click();
                }
            }else if(LookingFor.toLowerCase().equals("woman")){
                if (getHelper().isElementPresent(CupidElement.select_CupidLookingFor_Woman())){
                    WebElement WEselect_CupidLookingFor_Woman = getHelper().findElement(CupidElement.select_CupidLookingFor_Woman());
                    WEselect_CupidLookingFor_Woman.click();
                }
            }else if(LookingFor.toLowerCase().equals("both")){
                if (getHelper().isElementPresent(CupidElement.select_CupidLookingFor_Both())){
                    WebElement WEselect_CupidLookingFor_Both = getHelper().findElement(CupidElement.select_CupidLookingFor_Both());
                    WEselect_CupidLookingFor_Both.click();
                }
            }else {
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[SelectLookingFor] Input Parameter Failed: Looking For must be Man or Woman or Both");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[SelectLookingFor] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[SelectLookingFor] FAILED: " + ex.getMessage());
        }
    }
}