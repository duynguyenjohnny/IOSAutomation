package com.beecow.mobile.Cupid;

import com.beecow.component.BaseTest;
import com.beecow.screen.*;
import com.beecow.utils.Helper;
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
    public void DAT_1() throws Exception, TestLinkAPIException {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            System.out.println("Begin Select categories for first launching");
            firstScreen.selectCategories(cats);
            System.out.println("Click button Next to go second launching");
            firstScreen.clickButtonNext();
            secondScreen.selectIndustries(inds);
            System.out.println("Then click button Done");
            secondScreen.clickButtonDone();

            System.out.println("Begin Click on Cupid Tab");
            cupidScreen.clickCupidTab();
            System.out.println("End Click on Cupid Tab");

            //Test passed
            getHelper().takeScreenshot("Cupid", className, "Passed", sMethodName);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "DAT-1", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for DAT_1");
        }
        catch (Exception ex) {
            //Test failed
            getHelper().takeScreenshot("Cupid", className, "Failed", sMethodName);
            System.out.println("Current working dir: " + new File(CreateProfile.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "DAT-1", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test
    /**
     * DAT_2 - Turn on, turn off Cupid feature and Verify Cupid Hint
     */
    public void DAT_2() throws Exception {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{

            cupidScreen.clickCupidTab();
            cupidScreen.VerifyCupidHint(true);
            cupidScreen.TurnCupidFeatureOnOff(false);
            cupidScreen.TurnCupidFeatureOnOff(true);
            cupidScreen.VerifyCupidHint(false);
            cupidScreen.TurnCupidFeatureOnOff(true);
            cupidScreen.InputMyAlias("thai hoang");
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "DAT-2", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for DAT_2");
        }catch (Exception ex){
            getHelper().takeScreenshot("Cupid", className, "Failed", sMethodName);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "DAT-2", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("[DAT_2] Failed : " + ex.getMessage());
        }

    }

    @Test
    /**
     * DAT_3 - Create new profile
     */
    public void ANDROID_DAT_03() throws Exception {
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
            cupidScreen.VerifyStatusOfChooseButton(true);
            cupidScreen.ChooseImageForUpload(2);
            cupidScreen.VerifyStatusOfChooseButton(true);
            cupidScreen.ClickOnChooseButton();
            System.out.println("Swipe");
            cupidScreen.Swipe(2, 9, 2, 2, 2000);
            cupidScreen.ClickOnSaveButton();
            cupidScreen.VerifyTextInCurrentScreen("Registration succeeded", 5);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-3", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for DAT_3");
        }catch (Exception ex){
            getHelper().takeScreenshot("Cupid", className, "Failed", sMethodName);
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "ANDROID_DAT-3", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("[" + sMethodName + "]" + ex.getMessage() + "\n" + "Screenshot path: [" + Helper.sScreenShotPath + "]");
        }
    }
}
