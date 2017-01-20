package com.beecow.mobile.Cupid;

import com.beecow.component.BaseTest;
import com.beecow.screen.HomeScreen;
import com.beecow.screen.CupidScreen;
import com.beecow.utils.CommandPrompt;
import com.beecow.utils.Utils;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.testng.ITestResult;
import org.testng.TestNGUtils;
import org.testng.annotations.*;
import testlink.api.java.client.TestLinkAPIException;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.testng.Reporter;


public class CupidTest extends BaseTest{

    private CupidScreen cupidScreen;
    ITestResult result;
    static String service_url;
    public CupidTest() throws Exception {

    }

    AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
            .usingDriverExecutable(new File(Utils.getPropertyValue("Cupid.properties", "Android_NodeJSPath")))
            .usingPort(4723)
            .withIPAddress("127.0.0.1")
            .withAppiumJS(new File(Utils.getPropertyValue("Cupid.properties","Android_AppiumMainJSPath")))
            .withLogFile(new File(Utils.getPropertyValue("Cupid.properties","Android_LogPath")))
            .withStartUpTimeOut(50, TimeUnit.SECONDS));


    @BeforeClass
    public void RunAppium() throws IOException, InterruptedException {
        System.out.println("Appium is starting");
        service_url = service.getUrl().toString();
        service.start();
        System.out.println("Appium is started");
    }

    @BeforeMethod
    public void Up() throws Exception {
        super.setUp("Cupid.properties");
        cupidScreen = new CupidScreen(driver);
    }


    @Test
    /**
     * DAT_1 - Screen is turn off
     */
    public void DAT_1() throws Exception {
        try{
            System.out.println("Wait 20 seconds");
            Thread.sleep(20000);
            System.out.println("Begin Click on Cupid Tab");
            cupidScreen.clickCupidTab();
            System.out.println("End Click on Cupid Tab");

            Reporter.getCurrentTestResult().setStatus(ITestResult.SUCCESS);
        }catch(Exception ex){
            int result = Reporter.getCurrentTestResult().getStatus();
            if(result  == ITestResult.FAILURE)
            {

                Reporter.log("DAT_1 failed: " + ex.getMessage());

                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                System.out.println("Result:" + Reporter.getCurrentTestResult());
                System.out.println("Result:" + Reporter.getCurrentTestResult());

            }

        }
    }

    @AfterClass
    public void Stop() throws IOException, InterruptedException {
        System.out.println("Stopping Appium");
        service.stop();
        System.out.println("Appium is stopped");
    }
}
