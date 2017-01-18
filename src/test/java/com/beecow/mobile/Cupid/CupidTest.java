package com.beecow.mobile.Cupid;

import com.beecow.component.BaseTest;
import com.beecow.screen.HomeScreen;
import com.beecow.screen.CupidScreen;
import com.beecow.utils.CommandPrompt;
import com.beecow.utils.Utils;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.testng.annotations.*;
import testlink.api.java.client.TestLinkAPIException;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class CupidTest extends BaseTest {
    int i=1;
    private CupidScreen cupidScreen;

    AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
            .usingDriverExecutable(new File("c:\\Program Files\\nodejs\\node.exe"))
            .withAppiumJS(new File("C:\\Users\\Hoang Nguyen\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
            .withLogFile(new File("D:\\Appium.log"))
            .withStartUpTimeOut(20, TimeUnit.SECONDS));

    @BeforeClass
    public void RunAppium() throws IOException, InterruptedException {
        System.out.println("Appium is starting");
        service.start();
        System.out.println("Appium is started");
    }
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        cupidScreen = new CupidScreen(driver);
    }

    @Test
    public void ClickOnCupidTab() throws Exception {
        System.out.println("Wait 20 seconds");
        Thread.sleep(20000);
        System.out.println("Begin Click on Cupid Tab");
        cupidScreen.clickCupidTab();
        System.out.println("End Click on Cupid Tab");
    }

    @AfterClass
    public void Stop() throws IOException, InterruptedException {
        System.out.println("Stopping Appium");
        service.stop();
        System.out.println("Appium is stopped");
    }
}
