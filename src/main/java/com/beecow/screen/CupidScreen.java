package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import com.beecow.model.CupidElement;
import com.beecow.utils.Result;
import com.beecow.utils.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;
import org.jboss.netty.channel.ExceptionEvent;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.Reporter;
import sun.plugin.javascript.navig.Array;
import testlink.api.java.client.TestLinkAPIException;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;

import static com.beecow.component.Constant.*;
import static com.beecow.model.CommonElement.failed;
import static com.beecow.model.CommonElement.screenShot_login;
import static com.beecow.model.CupidElement.*;
import static com.beecow.model.CommonElement.*;
import static org.bytedeco.javacpp.lept.boxaaAddBox;
import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;


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
     * Select number of photo in the screen choose photo
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

        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ChooseImageForUpload] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ChooseImageForUpload] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Click on Choose button in the screen Choose image, verify it enable first before select
     * @throws Exception
     */
    public void ClickOnChooseButton() throws Exception{
        try{
            //select choose button
            if (getHelper().isElementPresent(CupidElement.btn_ChooseEnabled())){
                WebElement WEbtn_ChooseEnabled = getHelper().findElement(CupidElement.btn_ChooseEnabled());
                WEbtn_ChooseEnabled.click();
            }else{
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[ClickOnChooseButton] Button choose is not enable or not found");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickOnChooseButton] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickOnChooseButton] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Swipe on the screen base on start coordinate and end coordinate
     * @param startX Start X coordinate - range 1-> 5
     * @param startY Start X coordinate - range 1-> 10
     * @param endX End X coordinate - range 1-> 5
     * @param endY End Y coordinate - range 1-> 10
     * @param duration how fast it swipe, in mili-seconds
     * @throws Exception
     */
    public void Swipe(int startX, int startY, int endX, int endY, int duration) throws Exception{
        try{

            //Verify input parameters
            if (startX < 0 || startX > 5){
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[SwipeDown] Input parameter failed: startX must be in range 1 -> 5, your input is [" + startX + "]");
            }
            if (startY < 0 || startY > 10){
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[SwipeDown] Input parameter failed: startY must be in range 1 -> 10, your input is [" + startY + "]");
            }
            if (endX < 0 || endX > 5){
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[SwipeDown] Input parameter failed: endX must be in range 1 -> 5, your input is [" + endX + "]");
            }
            if (endY < 0 || endY > 5){
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[SwipeDown] Input parameter failed: endY must be in range 1 -> 10, your input is [" + endY + "]");
            }
            Dimension dimensions = driver.manage().window().getSize();

            int screenWidth = dimensions.getWidth();
            int screenHeight = dimensions.getHeight();

            int actualStartX = screenWidth/5*startX;
            int actualStartY = screenHeight/10*startY;
            int actualEndX = screenWidth/5*endX;
            int actualEndY = screenHeight/10*endY;
            System.out.println("Screen Width x Height (" + screenWidth + "," + screenHeight + ")");
            System.out.println("Start coordinate: [" + actualStartX + "," + actualStartY + "], End coordinate: [" + actualEndX + "," + actualEndY + "]");
            if (Utils.getInstance().isAndroidDevice()){
                ((AndroidDriver)driver).swipe(actualStartX,actualStartY,actualEndX,actualEndY,duration);
            }else if(Utils.getInstance().isIosDevice()){
                ((IOSDriver)driver).swipe(actualStartX,actualStartY,actualEndX,actualEndY,duration);
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[SwipeDown] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[SwipeDown] FAILED: " + ex.getMessage());
        }
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

    /**
     * This function will take screenshot of current screen, then use OCR to parse into Text, then verify with input text
     * @param sVerifyText Text need to verify in current screen, case sensitive
     * @param timeOut how many second need to verify (second)
     */
    public void VerifyTextInCurrentScreen(String sVerifyText, int timeOut) throws Exception{
        String sProjectPath = new File("src/report").getAbsolutePath().concat(File.separator).concat("Cupid").concat(File.separator);
        String fileScrShot = "";
        String sDevice = "Android";
        Boolean bResult = false;
        try{
            //Check type of running device
            if(Utils.getInstance().isIosDevice()){
                sDevice = "IOS";
            }

            //Take Screenshot
            for (int i = 1; i <= timeOut; i++) {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                fileScrShot = sProjectPath.concat(sDevice) + File.separator + "TempScreenShot_" + i + ".png";
                try {
                    FileUtils.copyFile(scrFile, new File(fileScrShot), true);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.err.println(e);
                }
//                Thread.sleep(100);
            }

            //Parse OCR and Verify with input parameter
            for (int j = 1; j <= timeOut; j++) {
                System.out.println("Start - Parse OCR file [" + sProjectPath.concat(sDevice) + File.separator + "TempScreenShot_" + j + ".png]");
                BytePointer outText;

                tesseract.TessBaseAPI api = new tesseract.TessBaseAPI();
                // Initialize tesseract-ocr with English, without specifying tessdata path
                if (api.Init(null, "eng") != 0) {
                    System.err.println("Could not initialize tesseract.");
                    System.exit(1);
                }
                // Open input image with leptonica library
                lept.PIX image = pixRead(sProjectPath.concat(sDevice) + File.separator + "TempScreenShot_" + j + ".png");
                api.SetImage(image);
                // Get OCR result
                outText = api.GetUTF8Text();
                System.out.println("OCR output:\n" + outText.getString());

                // Destroy used object and release memory
                api.End();
                outText.deallocate();
                pixDestroy(image);
                System.out.println("End - Parse OCR");
                //Verify with Input parameter
                if (outText.getString().contains(sVerifyText)){
                    bResult = true;
                    break;
                }
            }
            //Verify result
            if (bResult){
                System.out.println("[VerifyTextInCurrentScreen] Passed - Expected [" + sVerifyText + "] in the current screen, Actual [Found]");
            }else {
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[VerifyTextInCurrentScreen] FAILED: Expected [" + sVerifyText + "] in the current screen, Actual [Not Found in " + timeOut + " seconds]");
            }
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[VerifyTextInCurrentScreen] - FAILED: " + ex.getMessage());
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
     * Verify status of Choose Button
     * @param enabled true mean enabled, false mean disabled
     * @throws Exception
     */
    public void VerifyStatusOfChooseButton(boolean enabled) throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            if (enabled){
                if (getHelper().isElementPresent(CupidElement.btn_ChooseEnabled())){
                    System.out.println("[VerifyStatusOfChooseButton] success: Expected [enabled], Actual [enbaled]");
                }else{
                    Result.Fail(kwName,"Expected [Enable], Actual [Disabled or Not Exist]");
                }
            }else{
                if (getHelper().isElementPresent(CupidElement.btn_ChooseDisabled())){
                    System.out.println("VerifyStatusOfChooseButton success: Expected [Disabled], Actual [Disabled]");
                }else{
                    Result.Fail(kwName,"Expected [Disabled], Actual [Enabled or Not Exist]");
                }
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[VerifyStatusOfChooseButton] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception(ex.getMessage());
        }
    }
}