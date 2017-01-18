package com.beecow.mobile.Market;

import com.beecow.screen.HomeScreen;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.beecow.component.BaseTest;
import com.beecow.screen.MarketScreen;

import testlink.api.java.client.TestLinkAPIException;

/**
 * Created by HangPham on 12/18/2016.
 */

public class MarketBannerTest extends BaseTest {
    int i=2;
    private HomeScreen homeScreen;
    private MarketScreen marketScreen;

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
           marketScreen.checkReportTestLinkLogin(testCasesID);
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
