package com.beecow.mobile.Cupid;

import com.beecow.component.BaseTest;
import com.beecow.screen.CupidScreen;
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
    AppiumDriverLocalService service;
    private CupidScreen cupidScreen;
    public String Appium_IPAddress;
    public String Appium_Port;

    public String Android_NodeJSPath;
    public String Android_AppiumMainJSPath;
    public String Android_LogPath;
    public String Testlink_ProjectName;
    public String Testlink_TestPlanName;
    public String Testlink_BuildName;
    String className = this.getClass().getSimpleName();
    Properties globalProperties;
    Properties cupidProperties;




    @BeforeSuite
    public void GetLastAPKFile() throws Exception{
        System.out.println("Start Get APK File from share folder");
        System.out.println("Done Get APK File from share folder");
        System.out.println("Appium is starting");
        service.start();
        System.out.println("Appium is started");
    }


    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp("Cupid.properties");
        cupidScreen = new CupidScreen(driver);
//        driver.launchApp();
    }


    @Test
    /**
     * DAT_1 - Screen is turn off
     */
    public void DAT_1() throws Exception, TestLinkAPIException {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            System.out.println("Begin Click on Cupid Tab");
//            cupidScreen.clickCupidTab();
            System.out.println("End Click on Cupid Tab");
            //Test passed
//            getHelper().takeScreenshot("Cupid", className, "Passed", sMethodName);
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

//    @Test
//    /**
//     * DAT_2 - Screen is turn off
//     */
//    public void DAT_2() throws Exception {
//        try{
//            System.out.println("Begin Click on Cupid Tab 2");
//            cupidScreen.clickCupidTab();
//            System.out.println("End Click on Cupid Tab 2");
//            TestLink.updateResult(Testlink_ProjectName,Testlink_TestPlanName, "DAT-2", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
//        }catch (TestLinkAPIException ex){
//            System.out.print("Can't update result to Testlink for DAT_2");
//        }catch (Exception ex){
//            TestLink.updateResult(Testlink_ProjectName,Testlink_TestPlanName, "DAT-2", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
//            throw new Exception("Failed 2: " + ex.getMessage());
//        }
//
//    }

    @AfterMethod
    public void closeApp() throws Exception{
        System.out.println("Closing app");
        if ((driver!=null)){
            driver.quit();
        }
//        driver.quit();
        System.out.println("Closed app");
    }

    
}
