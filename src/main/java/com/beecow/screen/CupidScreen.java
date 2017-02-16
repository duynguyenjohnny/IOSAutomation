package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import com.beecow.model.CupidElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.jboss.netty.channel.ExceptionEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.Reporter;
import sun.plugin.javascript.navig.Array;
import testlink.api.java.client.TestLinkAPIException;

import java.util.HashMap;
import java.util.List;

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
            getHelper().hideKeyBoard();
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
     * @param Gender Man or Woman
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

    /**
     * Tap on Birthday Edit Text in screen Create Cupid Profile
     * @throws Exception
     */
    public void ClickOnBirthDay() throws Exception{
        try{
            if (getHelper().isElementPresent(CupidElement.select_CupidBirthday())){
                WebElement WEselect_CupidBirthday = getHelper().findElement(CupidElement.select_CupidBirthday());
                WEselect_CupidBirthday.click();
            }else{
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[ClickOnBirthDay] Not found BirthDay field");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickOnBirthDay] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickOnBirthDay] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Tap on Button OK in the Calendar
     * @throws Exception
     */
    public void ClickOnButtonCalendarOK() throws Exception{
        try{
            if (getHelper().isElementPresent(CupidElement.btn_Calendar_OK())){
                WebElement WEbtn_Calendar_OK = getHelper().findElement(CupidElement.btn_Calendar_OK());
                WEbtn_Calendar_OK.click();
            }else{
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[ClickOnButtonCalendarOK] There is no button OK");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickOnButtonCalendarOK] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickOnButtonCalendarOK] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Tap on Button Big Photo Upload in the Cupid create profile screen
     * @throws Exception
     */
    public void ClickOnBigPhotoUpload() throws Exception{
        try{
            if (getHelper().isElementPresent(CupidElement.btn_CupidSelectBigPhoto())){
                WebElement WEbtn_CupidSelectBigPhoto = getHelper().findElement(CupidElement.btn_CupidSelectBigPhoto());
                WEbtn_CupidSelectBigPhoto.click();
            }else{
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[ClickOnBigPhotoUpload] There is no button Big Photo Upload");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickOnBigPhotoUpload] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickOnBigPhotoUpload] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Choose number of photo in the screen choose photo
     * @param number
     * @throws Exception
     */
    public void ChooseImageForUpload(int number) throws Exception{
        try{
            //Verify input parameter
            if (number <= 0){
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[ChooseImageForUpload] Input parameter failed, number must be > 0, your input is [" + number + "]");
            }
            //Get number of photo
            int photo_count = driver.findElements(By.xpath("//android.widget.Button[contains(@resource-id,'item_choose_photo_dialog_btn_select')]")).size();
            if (photo_count == 0){
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[ChooseImageForUpload] There is no Photo for upload");
            }else{
                System.out.print("Number of photo is [" + photo_count + "]");
            }
            //choose photo
            List<WebElement> lstPhoto = driver.findElements(By.xpath("//android.widget.Button[contains(@resource-id,'item_choose_photo_dialog_btn_select')]"));
            for (int i = 0; i < number; i++) {
                lstPhoto.get(i).click();
            }

            //select choose button
            if (getHelper().isElementPresent(CupidElement.btn_ChooseEnabled())){
                WebElement WEbtn_ChooseEnabled = getHelper().findElement(CupidElement.btn_ChooseEnabled());
                WEbtn_ChooseEnabled.click();
            }else{
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[ChooseImageForUpload] Button choose is not enable or not found");
            }

        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ChooseImageForUpload] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ChooseImageForUpload] FAILED: " + ex.getMessage());
        }
    }

    public void SwipeDown() throws Exception{
        try{
            TouchAction tAction=new TouchAction(driver);
            Dimension dimensions = driver.manage().window().getSize();
            int screenWidth = dimensions.getWidth();
            int screenHeight = dimensions.getHeight();
            System.out.println(screenWidth + " ::::::: " + screenHeight + " ::::::: " + screenWidth/2 +  " ::::::: " +	((screenHeight/2)+100));
            Thread.sleep(1000);
            //First tap on the screen and swipe it right using moveTo function
            tAction.press(360,500).moveTo(0,100).release().perform();
            Thread.sleep(1000);

            //Second tap on the screen and swipe it left using moveTo function
//            tAction.press(endx+20,endy+20).moveTo(startx+20,starty+20).release().perform();
//            Thread.sleep(1000);
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[SwipeDown] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[SwipeDown] FAILED: " + ex.getMessage());
        }
    }
    public void swipeUpElement(AppiumDriver driver, WebElement element, int duration){
        int topY = element.getLocation().getY();
        int bottomY = topY + element.getSize().getHeight();
        int centerX = element.getLocation().getX() + (element.getSize().getWidth()/2);
        driver.swipe(centerX, bottomY, centerX, topY, duration);
    }
    /**
     * Verify button Save is enabled or not, then click on it
     * @throws Exception
     */
    public void ClickOnSaveButton() throws Exception{
        try{
            //select choose button
            if (getHelper().isElementPresent(CupidElement.btn_CupidSaveEnable())){
                WebElement WEbtn_CupidSaveEnable = getHelper().findElement(CupidElement.btn_CupidSaveEnable());
                WEbtn_CupidSaveEnable.click();
            }else{
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[ClickOnSaveButton] Button SAVE is not enable or not found");
            }

        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickOnSaveButton] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickOnSaveButton] FAILED: " + ex.getMessage());
        }
    }


}