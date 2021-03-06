package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import com.beecow.model.CupidElement;
import com.beecow.utils.Result;
import com.beecow.utils.Utils;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;


public class CupidScreen extends CommonScreenObjects{

    public CupidScreen(AppiumDriver driver) {
        super(driver);

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
     * Swipe Profile to left or right, left means: disklie, right means: like
     * @param SwipeDirection: 1 means swipe left, 2 mean swipe right
     * @param numberSwipe: how many time Swipe
     * @throws Exception
     */
    public void SwipeProfile(int SwipeDirection, int numberSwipe) throws Exception{
        try{
            switch(SwipeDirection){
                case 1: {
                    for(int i=1; i<=numberSwipe; i++){
                        getSwipe().Swipe(6,4, 1, 4, 500);
                        Thread.sleep(1000);
                    }
                    break;
                }
                case 2:{
                    for(int j=1; j<=numberSwipe; j++){
                        getSwipe().Swipe(6,4, 9, 4, 500);
                        Thread.sleep(1000);
                    }
                    break;
                }
                default:{
                    Result.Fail("SwipeProfile", "Input Parameter Failed: SwipeDirection must be 1 or 2, your input is [" + SwipeDirection + "]");
                    break;
                }
            }
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[SwipeProfile] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Deselect number of photo in the screen choose photo (Cupid_Function12.png)
     * @param number
     * @throws Exception
     */
    public void DeselectImageForUpload(int number) throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            //Verify input parameter
            if (number <= 0){
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[DeselectImageForUpload] Input parameter failed, number must be > 0, your input is [" + number + "]");
            }
            //Get number of photo able to deselect in the system
            int photo_count = driver.findElements(By.xpath("//android.widget.ImageView[contains(@resource-id,'item_cupid_picked_photo_iv_delete')]")).size();
            System.out.println("[DeselectImageForUpload] Number of selected photos [" + photo_count + "]");
            if (photo_count == 0){
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[DeselectImageForUpload] There is no Photo for deselect");
            }else{
                System.out.print("Number of photo able to deselect is [" + photo_count + "]");
            }

            if (number > photo_count){
                Result.Fail(kwName,"[DeselectImageForUpload] Input parameter failed: Number of photo need to DeSelect is greater than Selected photo");
            }

            //deselect photo
            List<WebElement> lstDeletePhoto = driver.findElements(By.xpath("//android.widget.ImageView[contains(@resource-id,'item_cupid_picked_photo_iv_delete')]"));
            for (int i = 0; i < number; i++) {
                lstDeletePhoto.get(0).click();
                Thread.sleep(1000);
            }
        }catch (NoSuchElementException noElement){
            Result.Fail(kwName,"[DeselectImageForUpload] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Result.Fail(kwName,"[DeselectImageForUpload] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Click on Choose button in the screen Choose image, verify it enable first before select
     * @throws Exception
     */
    public void ClickOnChooseButton() throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            //select choose button
            if (getHelper().isElementPresent(CupidElement.btn_ChooseEnabled())){
                WebElement WEbtn_ChooseEnabled = getHelper().findElement(CupidElement.btn_ChooseEnabled());
                WEbtn_ChooseEnabled.click();
            }else{
                Result.Fail(kwName,"[ClickOnChooseButton] Button choose is not enable or not found");
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
     * Verify button Save is enabled or not, then click on it
     * @throws Exception
     */
    public void ClickOnSaveButton() throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            //select choose button
            if (getHelper().isElementPresent(CupidElement.btn_CupidSaveEnable())){
                WebElement WEbtn_CupidSaveEnable = getHelper().findElement(CupidElement.btn_CupidSaveEnable());
                WEbtn_CupidSaveEnable.click();
            }else{
                Result.Fail(kwName,"[ClickOnSaveButton] Button SAVE is not enable or not found");
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
     * Verify button Matched Tab on the Swipe Screen, then click on it - Cupid_Function1.png
     * @throws Exception
     */
    public void ClickMatchedTab() throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            //Click on Matched Tab
            if (getHelper().isElementPresent(CupidElement.btn_MatchedTab())){
                WebElement WEbtn_MatchedTab = getHelper().findElement(CupidElement.btn_MatchedTab());
                WEbtn_MatchedTab.click();
            }else{
                Result.Fail(kwName,"[ClickMatchedTab] Matched Tab not found");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickMatchedTab] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickMatchedTab] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Verify button Saved Tab on the Swipe Screen, then click on it - Cupid_Function2.png
     * @throws Exception
     */
    public void ClickSavedTab() throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            //Click on Saved Tab
            if (getHelper().isElementPresent(CupidElement.btn_SavedTab())){
                WebElement WEbtn_SavedTab = getHelper().findElement(CupidElement.btn_SavedTab());
                WEbtn_SavedTab.click();
            }else{
                Result.Fail(kwName,"[ClickSavedTab] Saved Tab not found");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickSavedTab] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickSavedTab] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Verify button Gift Tab on the Swipe Screen, then click on it - Cupid_Function3.png
     * @throws Exception
     */
    public void ClickGiftTab() throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            //Click on Gift Tab
            if (getHelper().isElementPresent(CupidElement.btn_GiftTab())){
                WebElement WEbtn_GiftTab = getHelper().findElement(CupidElement.btn_GiftTab());
                WEbtn_GiftTab.click();
            }else{
                Result.Fail(kwName,"[ClickGiftTab] Gift Tab not found");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickGiftTab] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickGiftTab] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Verify button Save Profile on the Swipe Screen, then click on it - Cupid_Function4.png
     * @throws Exception
     */
    public void ClickSaveProfile() throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            //Click on Save Profile
            if (getHelper().isElementPresent(CupidElement.btn_SaveProfile())){
                WebElement WEbtn_SaveProfile = getHelper().findElement(CupidElement.btn_SaveProfile());
                WEbtn_SaveProfile.click();
            }else{
                Result.Fail(kwName,"[ClickSaveProfile] Gift Tab not found");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickSaveProfile] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickSaveProfile] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Verify button Dislike on the Swipe Screen, then click on it - Cupid_Function5.png
     * @throws Exception
     */
    public void ClickDislikeButton() throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            //Click on Dislike button
            if (getHelper().isElementPresent(CupidElement.btn_DislikeEnabled())){
                WebElement WEbtn_DislikeEnabled = getHelper().findElement(CupidElement.btn_DislikeEnabled());
                WEbtn_DislikeEnabled.click();
            }else{
                Result.Fail(kwName,"[ClickDislikeButton] Dislike Button is not enable or not found");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickDislikeButton] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickDislikeButton] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Verify button Chat on the Swipe Screen, then click on it - Cupid_Function6.png
     * @throws Exception
     */
    public void ClickChatButton() throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            //Click on Chat button
            if (getHelper().isElementPresent(CupidElement.btn_ChatEnabled())){
                WebElement WEbtn_ChatEnabled = getHelper().findElement(CupidElement.btn_ChatEnabled());
                WEbtn_ChatEnabled.click();
            }else{
                Result.Fail(kwName,"[ClickChatButton] Chat Button is not enable or not found");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickChatButton] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickChatButton] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Verify button Gift on the Swipe Screen, then click on it - Cupid_Function7.png
     * @throws Exception
     */
    public void ClickGiftButton() throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            //Click on Gift button
            if (getHelper().isElementPresent(CupidElement.btn_GiftEnabled())){
                WebElement WEbtn_GiftEnabled = getHelper().findElement(CupidElement.btn_GiftEnabled());
                WEbtn_GiftEnabled.click();
            }else{
                Result.Fail(kwName,"[ClickGiftButton] Gift Button is not enable or not found");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickGiftButton] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickGiftButton] FAILED: " + ex.getMessage());
        }
    }

    /**
     * Verify button Like on the Swipe Screen, then click on it - Cupid_Function8.png
     * @throws Exception
     */
    public void ClickLikeButton() throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            //Click on Like button
            if (getHelper().isElementPresent(CupidElement.btn_LikeDisabled())){
                WebElement WEbtn_LikeDisabled = getHelper().findElement(CupidElement.btn_LikeDisabled());
                WEbtn_LikeDisabled.click();
            }else{
                Result.Fail(kwName,"[ClickLikeButton] Like Button is not enable or not found");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickLikeButton] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[ClickLikeButton] FAILED: " + ex.getMessage());
        }
    }

    /**
     * This function will take screenshot of current screen, then use OCR to parse into Text, then verify with input text
     * @param sVerifyText Text need to verify in current screen, case sensitive
     * @param timeOut how many second need to verify (second)
     */
    public void VerifyTextInCurrentScreen(String sVerifyText, int timeOut) throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
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
                Result.Fail(kwName,"[VerifyTextInCurrentScreen] FAILED: Expected [" + sVerifyText + "] in the current screen, Actual [Not Found in " + timeOut + " seconds]");
            }
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[VerifyTextInCurrentScreen] - FAILED: " + ex.getMessage());
        }
    }

    /**
     * This function will take screenshot of current screen, then use OCR to parse into Text, then verify with input text
     * @param sVerifyText1 Text need to verify in current screen, case sensitive, must input
     * @param sVerifyText2 Text need to verify in current screen, case sensitive, null: for by pass verify
     * @param sVerifyText3 Text need to verify in current screen, case sensitive, null: for by pass verify
     * @param timeOut how many second need to verify (second)
     */
    public void VerifyTextInCurrentScreen(String sVerifyText1, String sVerifyText2, String sVerifyText3, int timeOut) throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        String sProjectPath = new File("src/report").getAbsolutePath().concat(File.separator).concat("Cupid").concat(File.separator);
        String fileScrShot = "";
        String sDevice = "Android";
        Boolean bResult = false;
        try{
            String sMessage = "";
            //Check input parameter
            if(sVerifyText1.isEmpty() || sVerifyText1 == null){
                Result.Fail(kwName,"[VerifyTextInCurrentScreen] Input parameter FAILED: sVerifyText1 can't be null or empty");
            }

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
            }

            //Parse OCR and Verify with input parameter
            for (int j = 1; j <= timeOut; j++) {
                sMessage = "";
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
                //Verify with Input parameter 1
                if (outText.getString().contains(sVerifyText1)){
                    bResult = true;
                    sMessage += "Verify text [" + sVerifyText1 +"] PASSED";
                }else{
                    sMessage += "Verify text [" + sVerifyText1 +"] FAILED";
                    bResult = false;
                }
                //Verify with Input parameter 2
                if (sVerifyText2 != null){
                    if (outText.getString().contains(sVerifyText2)){
                        bResult = true;
                        sMessage += " - Verify text [" + sVerifyText2 +"] PASSED";
                    }else{
                        sMessage += " - Verify text [" + sVerifyText2 +"] FAILED";
                        bResult = false;
                    }
                }else{
                    System.out.print("[VerifyTextInCurrentScreen] By pass verify sVerifyText2");
                }

                //Verify with Input parameter 3
                if (sVerifyText3 != null){
                    if (outText.getString().contains(sVerifyText3)){
                        bResult = true;
                        sMessage += " - Verify text [" + sVerifyText3 +"] PASSED";
                    }else{
                        sMessage += " - Verify text [" + sVerifyText3 +"] FAILED";
                        bResult = false;
                    }
                }else{
                    System.out.print("[VerifyTextInCurrentScreen] By pass verify sVerifyText3");
                }

                //Show message
                System.out.print("[VerifyTextInCurrentScreen] Message for [" + j + "] verify: " + sMessage);

                //if pass --> exit the loop, failed continue loop
                if(bResult){
                    break;
                }
            }
            //Verify result
            if (bResult){
                System.out.println("[VerifyTextInCurrentScreen] PASSED " + sMessage);
            }else {
                Result.Fail(kwName,"[VerifyTextInCurrentScreen] FAILED " + sMessage);
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
     * Verify status Enable or Disable of Choose Button
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

    /**
     * Verify status Enable or Disable of Save Button
     * @param enabled true mean enabled, false mean disabled
     * @throws Exception
     */
    public void VerifyStatusOfSaveButton(boolean enabled) throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            if (enabled){
                if (getHelper().isElementPresent(CupidElement.btn_CupidSaveEnable())){
                    System.out.println("[VerifyStatusOfSaveButton] success: Expected [enabled], Actual [enbaled]");
                }else{
                    Result.Fail(kwName,"Expected [Enable], Actual [Disabled or Not Exist]");
                }
            }else{
                if (getHelper().isElementPresent(CupidElement.btn_CupidSaveDisable())){
                    System.out.println("VerifyStatusOfSaveButton success: Expected [Disabled], Actual [Disabled]");
                }else{
                    Result.Fail(kwName,"Expected [Disabled], Actual [Enabled or Not Exist]");
                }
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[VerifyStatusOfSaveButton] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Verify Cupid Alias name on Swipe screen - Cupid_Function9.png
     * @param sAliasName Alias Name need to verify
     * @throws Exception
     */
    public void VerifyAliasName(String sAliasName) throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            //Get actual Alias Name
            String actualAliasName = getHelper().findElement(CupidElement.txt_UserNameOnCard()).getText();
            //Compare with Inputted Alias name
            if (sAliasName.equals(actualAliasName)){
                System.out.println("[VerifyAliasName] success: Expected [" + sAliasName + "], Actual [" + actualAliasName + "]");
            }else{
                Result.Fail(kwName,"[VerifyAliasName] Failed: Expected [" + sAliasName + "], Actual [" + actualAliasName + "]");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[VerifyAliasName] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Verify Cupid Year Old, Age on Swipe screen - Cupid_Function10.png
     * @param sAge Alias Name need to verify
     * @throws Exception
     */
    public void VerifyCupidYearOld(int sAge) throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            //Get actual Age
            String actualAge = getHelper().findElement(CupidElement.txt_YearOldDistanceOnCard()).getText().trim().split(" ")[0];
            //Compare with Inputted Alias name
            if(sAge == Integer.parseInt(actualAge)){
                System.out.println("[VerifyCupidYearOld] success: Expected [" + sAge + " years old], Actual [" + actualAge + " years old]");
            }
        }catch (NumberFormatException nfe) {
            Result.Fail(kwName, "Error on Parsing Actual Age to Integer");
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[VerifyCupidYearOld] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Verify Cupid Distance on Swipe screen - Cupid_Function11.png
     * @param sDistance Distance need to verify, example: 3km
     * @throws Exception
     */
    public void VerifyCupidDistance(String sDistance) throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            //Get actual Distance
            String temp = getHelper().findElement(CupidElement.txt_YearOldDistanceOnCard()).getText().trim();
            String actualDistance = temp.substring(temp.lastIndexOf(" ") + 1);
            //Compare with Inputted Alias name
            if(sDistance.toLowerCase().equals(actualDistance.toLowerCase())){
                System.out.println("[VerifyCupidDistance] success: Expected [" + sDistance + "], Actual [" + actualDistance + "]");
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[VerifyCupidDistance] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Verify screen upgrade to premium is displayed or not - Cupid_Function13.png
     * @param bDisplay true means is displayed, false means is not displayed
     * @throws Exception
     */
    public void VerifyScreenUpgradeToPremium(boolean bDisplay) throws Exception{
        String kwName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            if (bDisplay){
                if (getHelper().isElementPresent(CupidElement.txt_LimitReached())){
                    System.out.println("[VerifyScreenUpgradeToPremium] success: Expected [display], Actual [display]");
                }else{
                    Result.Fail(kwName,"Expected [display], Actual [Not display]");
                }
            }else{
                if (getHelper().isElementPresent(CupidElement.txt_LimitReached())){
                    Result.Fail(kwName,"[VerifyScreenUpgradeToPremium] failed : Expected [not display], Actual [display]");
                }else{
                    System.out.println("[VerifyScreenUpgradeToPremium] success: Expected [not display], Actual [not display]");
                }
            }
        }catch (NoSuchElementException noElement){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[VerifyScreenUpgradeToPremium] Can't find Element: " + noElement.getMessage());
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * close APP
     */
    public void closeApp() {
        try {
            driver.closeApp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * open APP
     */
    public void openApp() {
        try {
            driver.launchApp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}