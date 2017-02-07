package com.beecow.mobile.Cupid;

import com.beecow.component.BaseTest;
import com.beecow.screen.*;
import com.beecow.utils.TestLink;
import com.beecow.utils.Utils;
import io.appium.java_client.AppiumDriver;
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


public class CupidTest extends BaseTest{
    public String Testlink_ProjectName;
    public String Testlink_TestPlanName;
    public String Testlink_BuildName;
    String className = this.getClass().getSimpleName();
    Properties globalProperties;
    Properties cupidProperties;
    static String cupidPropertiesFile = "Cupid.properties";
    private ActivityFirstScreen firstScreen;
    private ActivitySecondScreen secondScreen;
    private HomeScreen homeScreen;
    private CupidScreen cupidScreen;
    public String CUPIDPROPERTIESFile = "Cupid.properties";
    public static Properties CUPIDPROPERTIES;


    // DATA TEST
    String cats[] = {"Sport", "Computer", "Meal Deals"};
    String inds[] = {"Consulting", "Design", "Education"};

    public CupidTest() throws Exception {
        CUPIDPROPERTIES = Utils.initProperties(CUPIDPROPERTIESFile);
        Testlink_ProjectName = Utils.getPropertyValue(CUPIDPROPERTIES, "Testlink_ProjectName");
        Testlink_TestPlanName = Utils.getPropertyValue(CUPIDPROPERTIES, "Testlink_TestPlanName");
        Testlink_BuildName = Utils.getPropertyValue(CUPIDPROPERTIES, "Testlink_BuildName");
    }


    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp(cupidPropertiesFile);
        firstScreen = new ActivityFirstScreen(driver);
        secondScreen = new ActivitySecondScreen(driver);
        homeScreen = new HomeScreen(driver);
        cupidScreen = new CupidScreen(driver);
    }


    @Test
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
            TestLink.updateResult(Testlink_ProjectName,Testlink_TestPlanName, "DAT-1", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for DAT_1");
        }
        catch (Exception ex) {
            //Test failed
            getHelper().takeScreenshot("Cupid", className, "Failed", sMethodName);
            System.out.println("Current working dir: " + new File(CupidTest.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "DAT-1", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test
    /**
     * DAT_2 - Screen is turn on
     */
    public void DAT_2() throws Exception {
        try{
            System.out.println("Begin Click on Cupid Tab 2");
            cupidScreen.clickCupidTab();
            System.out.println("End Click on Cupid Tab 2");
            cupidScreen.TurnCupidFeatureOnOff(false);
            TestLink.updateResult(Testlink_ProjectName,Testlink_TestPlanName, "DAT-2", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for DAT_2");
        }catch (Exception ex){
            TestLink.updateResult(Testlink_ProjectName,Testlink_TestPlanName, "DAT-2", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("Failed 2: " + ex.getMessage());
        }

    }
}
