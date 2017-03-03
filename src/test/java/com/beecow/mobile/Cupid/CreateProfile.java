package com.beecow.mobile.Cupid;

import com.beecow.component.BaseTest;
import com.beecow.screen.*;
import com.beecow.utils.Helper;
import com.beecow.utils.SwipeFunctions;
import com.beecow.utils.TestLink;
import com.beecow.utils.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import static com.beecow.model.CommonElement.cupidPropertiesFile;
import static com.beecow.utils.PropertiesUtils.testlinkBuildName;
import static com.beecow.utils.PropertiesUtils.testlinkProjectName;
import static com.beecow.utils.PropertiesUtils.testlinkTestPlanName;


public class CreateProfile extends BaseTest{
  IOSDriver driver1;
    String className = this.getClass().getSimpleName();
    private ActivityFirstScreen firstScreen;
    private ActivitySecondScreen secondScreen;
    private HomeScreen homeScreen;
    private CupidScreen cupidScreen;

    // DATA TEST
    String cats[] = {"Sport", "Computer", "Meal Deals"};
    String inds[] = {"Consulting", "Design", "Education"};


    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp(cupidPropertiesFile);
        firstScreen = new ActivityFirstScreen(driver);
        secondScreen = new ActivitySecondScreen(driver);
        homeScreen = new HomeScreen(driver);
        cupidScreen = new CupidScreen(driver);

    }


    //@Test
    /**
     * DAT_1 - Screen is turn off
     */
    public void ANDROID_DAT_1() throws Exception, TestLinkAPIException {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            System.out.println("Begin Select categories for first launching");
            firstScreen.selectCategories(cats);
            System.out.println("Click button Next to go second launching");
            firstScreen.clickButtonNext();
            secondScreen.selectIndustries(inds);
            System.out.println("Then click button Done");
            secondScreen.clickButtonDone();
            cupidScreen.clickCupidTab();

            //Test passed
            getHelper().takeScreenshot("Cupid", className, "Passed", sMethodName);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-1", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for DAT_1");
        }
        catch (Exception ex) {
            //Test failed
            getHelper().takeScreenshot("Cupid", className, "Failed", sMethodName);
            System.out.println("Current working dir: " + new File(CreateProfile.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "ANDROID_DAT-1", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("[" + sMethodName + "]" + ex.getMessage() + "\n" + "Screenshot path: [" + Helper.sScreenShotPath + "]");
        }
    }

    //@Test
    /**
     * DAT_2 - Turn on, turn off Cupid feature and Verify Cupid Hint
     */
    public void ANDROID_DAT_2() throws Exception {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{

            cupidScreen.clickCupidTab();
            cupidScreen.VerifyCupidHint(true);
            cupidScreen.TurnCupidFeatureOnOff(false);
            cupidScreen.TurnCupidFeatureOnOff(true);
            cupidScreen.VerifyCupidHint(false);
            cupidScreen.TurnCupidFeatureOnOff(true);
            cupidScreen.InputMyAlias("thai hoang");
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-2", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for DAT_2");
        }catch (Exception ex){
            getHelper().takeScreenshot("Cupid", className, "Failed", sMethodName);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-2", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("[" + sMethodName + "]" + ex.getMessage() + "\n" + "Screenshot path: [" + Helper.sScreenShotPath + "]");
        }

    }

    @Test
    /**
     * DAT_3 - Verify save button is disabled while there is no input for Alias Name
     */
    public void ANDROID_DAT_3() throws Exception {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            cupidScreen.clickCupidTab();
            cupidScreen.TurnCupidFeatureOnOff(true);
            System.out.println("Swipe to button Save");
            SwipeFunctions.Swipe(2, 9, 2, 2, 2000);
            cupidScreen.VerifyStatusOfSaveButton(false);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-3", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for DAT_3");
        }catch (Exception ex){
            getHelper().takeScreenshot("Cupid", className, "Failed", sMethodName);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-3", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("[" + sMethodName + "]" + ex.getMessage() + "\n" + "Screenshot path: [" + Helper.sScreenShotPath + "]");
        }
    }

    @Test
    /**
     * DAT_4 - Verify save button is disable while there is no input for Birthday
     */
    public void ANDROID_DAT_4() throws Exception {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            cupidScreen.clickCupidTab();
            cupidScreen.TurnCupidFeatureOnOff(true);
            cupidScreen.InputMyAlias("thai hoang");
            System.out.println("Swipe to button Save");
            cupidScreen.Swipe(2, 9, 2, 2, 2000);
            cupidScreen.VerifyStatusOfSaveButton(false);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-4", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for ANDROID_DAT_3");
        }catch (Exception ex){
            getHelper().takeScreenshot("Cupid", className, "Failed", sMethodName);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-4", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("[" + sMethodName + "]" + ex.getMessage() + "\n" + "Screenshot path: [" + Helper.sScreenShotPath + "]");
        }
    }

    @Test
    /**
     * DAT_5 - Verify save button is disable while there is no photo is upload
     */
    public void ANDROID_DAT_5() throws Exception {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            cupidScreen.clickCupidTab();
            cupidScreen.TurnCupidFeatureOnOff(true);
            cupidScreen.InputMyAlias("test");
            System.out.println("Swipe to button Save");
            cupidScreen.ClickOnBirthDay();
            cupidScreen.ClickOnButtonCalendarOK();
            cupidScreen.Swipe(2, 9, 2, 2, 2000);
            cupidScreen.VerifyStatusOfSaveButton(false);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-5", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for ANDROID_DAT_3");
        }catch (Exception ex){
            getHelper().takeScreenshot("Cupid", className, "Failed", sMethodName);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-5", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("[" + sMethodName + "]" + ex.getMessage() + "\n" + "Screenshot path: [" + Helper.sScreenShotPath + "]");
        }
    }

    @Test
    /**
     * DAT_6 - Create new profile
     * Choose 6 photos, then deselect that photo, verify status button save is disabled
     */
    public void ANDROID_DAT_6() throws Exception {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            cupidScreen.clickCupidTab();
            cupidScreen.TurnCupidFeatureOnOff(true);
            cupidScreen.SelectGender("Woman");
            cupidScreen.InputMyAlias("test nguyen thai hoang");
            cupidScreen.SelectLookingFor("Man");
            cupidScreen.ClickOnBirthDay();
            cupidScreen.ClickOnButtonCalendarOK();
            cupidScreen.ClickOnBigPhotoUpload();
            cupidScreen.ChooseImageForUpload(6);
            cupidScreen.ClickOnChooseButton();
            cupidScreen.DeselectImageForUpload(6);
            SwipeFunctions.(2, 9, 2, 2, 2000);
            cupidScreen.VerifyStatusOfSaveButton(false);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-6", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for ANDROID_DAT_6");
        }catch (Exception ex){
            getHelper().takeScreenshot("Cupid", className, "Failed", sMethodName);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-6", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("[" + sMethodName + "]" + ex.getMessage() + "\n" + "Screenshot path: [" + Helper.sScreenShotPath + "]");
        }
    }

    @Test
    /**
     * DAT_7 - Create new profile
     * Alias name lower than 6 characters
     */
    public void ANDROID_DAT_7() throws Exception {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            cupidScreen.clickCupidTab();
            cupidScreen.TurnCupidFeatureOnOff(true);
            cupidScreen.SelectGender("Woman");
            cupidScreen.InputMyAlias("lower");
            cupidScreen.SelectLookingFor("Man");
            cupidScreen.ClickOnBirthDay();
            cupidScreen.ClickOnButtonCalendarOK();
            cupidScreen.ClickOnBigPhotoUpload();
            cupidScreen.ChooseImageForUpload(6);
            cupidScreen.ClickOnChooseButton();
            cupidScreen.Swipe(2, 9, 2, 2, 2000);
            cupidScreen.VerifyStatusOfSaveButton(true);
            cupidScreen.ClickOnSaveButton();
            cupidScreen.VerifyTextInCurrentScreen("Alias must be between 6", "20 characters", "failed nè" ,5);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-7", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for ANDROID_DAT_7");
        }catch (Exception ex){
            getHelper().takeScreenshot("Cupid", className, "Failed", sMethodName);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-7", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("[" + sMethodName + "]" + ex.getMessage() + "\n" + "Screenshot path: [" + Helper.sScreenShotPath + "]");
        }
    }

    @Test
    /**
     * DAT_8 - Create new profile
     * Choose 1 photo for upload
     */
    public void ANDROID_DAT_8() throws Exception {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            cupidScreen.clickCupidTab();
            cupidScreen.TurnCupidFeatureOnOff(true);
            cupidScreen.SelectGender("Woman");
            cupidScreen.InputMyAlias("test hoang");
            cupidScreen.SelectLookingFor("Man");
            cupidScreen.ClickOnBirthDay();
            cupidScreen.ClickOnButtonCalendarOK();
            cupidScreen.ClickOnBigPhotoUpload();
            cupidScreen.VerifyStatusOfChooseButton(false);
            cupidScreen.ChooseImageForUpload(1);
            cupidScreen.VerifyStatusOfChooseButton(true);
            cupidScreen.ClickOnChooseButton();
            System.out.println("Swipe");
            cupidScreen.Swipe(2, 9, 2, 2, 2000);
            cupidScreen.ClickOnSaveButton();
            cupidScreen.VerifyTextInCurrentScreen("Registration succeeded", 5);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-8", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for ANDROID_DAT_8");
        }catch (Exception ex){
            getHelper().takeScreenshot("Cupid", className, "Failed", sMethodName);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-8", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("[" + sMethodName + "]" + ex.getMessage() + "\n" + "Screenshot path: [" + Helper.sScreenShotPath + "]");
        }
    }



    @Test
    /**
     * DAT_9 - Create new profile
     * Choose 6 photos
     */
    public void ANDROID_DAT_9() throws Exception {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            cupidScreen.clickCupidTab();
            cupidScreen.TurnCupidFeatureOnOff(true);
            cupidScreen.SelectGender("Woman");
            cupidScreen.InputMyAlias("test nguyen thai hoang");
            cupidScreen.SelectLookingFor("Man");
            cupidScreen.ClickOnBirthDay();
            cupidScreen.ClickOnButtonCalendarOK();
            cupidScreen.ClickOnBigPhotoUpload();
            cupidScreen.ChooseImageForUpload(6);
            cupidScreen.VerifyStatusOfChooseButton(true);
            cupidScreen.ClickOnChooseButton();
            System.out.println("Swipe");
            cupidScreen.Swipe(2, 9, 2, 2, 2000);
            cupidScreen.ClickOnSaveButton();
            cupidScreen.VerifyTextInCurrentScreen("Registration succeeded", 5);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-9", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for ANDROID_DAT_9");
        }catch (Exception ex){
            getHelper().takeScreenshot("Cupid", className, "Failed", sMethodName);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-9", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("[" + sMethodName + "]" + ex.getMessage() + "\n" + "Screenshot path: [" + Helper.sScreenShotPath + "]");
        }
    }

    @Test
    /**
     * DAT_10 - Create new profile
     * Choose 6 photos
     */
    public void ANDROID_DAT_10() throws Exception {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            cupidScreen.clickCupidTab();
            cupidScreen.closeApp();

            cupidScreen.openApp();
            System.out.print("AAAAAAAAAAAAAAAA");
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for ANDROID_DAT_9");
        }catch (Exception ex){
            System.out.println("Error" + ex.getMessage());
//            getHelper().takeScreenshot("Cupid", className, "Failed", sMethodName);
//            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-9", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
//            throw new Exception("[" + sMethodName + "]" + ex.getMessage() + "\n" + "Screenshot path: [" + Helper.sScreenShotPath + "]");
        }
    }
}
