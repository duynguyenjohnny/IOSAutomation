package com.beecow.mobile.Market;

import com.beecow.screen.CupidScreen;
import com.beecow.screen.HomeScreen;
import com.beecow.utils.Utils;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.beecow.component.BaseTest;
import com.beecow.screen.MarketScreen;

import testlink.api.java.client.TestLinkAPIException;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by HangPham on 12/18/2016.
 */

public class MarketBannerTest extends BaseTest {
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
    public String marketPropertyPath="Market.properties";
    String className = this.getClass().getSimpleName();

    int i=2;
    private HomeScreen homeScreen;
    private MarketScreen marketScreen;


    public MarketBannerTest() throws Exception {
        Appium_IPAddress = Utils.getPropertyValue("Global.properties", "Appium_IPAddress");
        Appium_Port = Utils.getPropertyValue("Global.properties", "Appium_Port");
        Android_NodeJSPath = Utils.getPropertyValue(marketPropertyPath, "Android_NodeJSPath");
        Android_AppiumMainJSPath = Utils.getPropertyValue(marketPropertyPath, "Android_AppiumMainJSPath");
        Android_LogPath = Utils.getPropertyValue(marketPropertyPath, "Android_LogPath");
        Testlink_ProjectName = Utils.getPropertyValue(marketPropertyPath, "Testlink_ProjectName");
        Testlink_TestPlanName = Utils.getPropertyValue(marketPropertyPath, "Testlink_TestPlanName");
        Testlink_BuildName = Utils.getPropertyValue(marketPropertyPath, "Testlink_BuildName");
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File(Android_NodeJSPath))
                .usingPort(Integer.parseInt(Appium_Port))
                .withIPAddress(Appium_IPAddress)
                .withAppiumJS(new File(Android_AppiumMainJSPath))
                .withLogFile(new File(Android_LogPath))
                .withStartUpTimeOut(50, TimeUnit.SECONDS));
    }

    @BeforeSuite
    public void GetLastAPKFile() throws Exception{
        System.out.println("Start Get APK File from share folder");
        Utils.GetLastAPKFile();
        System.out.println("Done Get APK File from share folder");
        System.out.println("Appium is starting");
        service.start();
        System.out.println("Appium is started");
    }


    //@Override
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp("Market.properties");
        homeScreen=new HomeScreen(driver);
        marketScreen =new MarketScreen(driver);
    }
    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws InterruptedException {
        if (driver==null)
            return;
        String str="AND_MAR_TC_";
        String testCasesID=str.concat(Integer.toString(i));
//           marketScreen.checkReportTestLinkLogin(testCasesID);
        i=i+1;
        Thread.sleep(5000);
        driver.quit();
    }


    @Test
    public void AND_MAR_8_TC_1() throws TestLinkAPIException {
        System.out.println("Checking market page ... ");
//        homeScreen.clickMarketTabView();
//        marketScreen.verifyMarketPage();
    }

}
