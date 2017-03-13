package com.beecow.mobile.Market.AND;

import com.beecow.component.BaseTest;
import com.beecow.screen.ActivityFirstScreen;
import com.beecow.screen.ActivitySecondScreen;
import com.beecow.screen.HomeScreen;
import com.beecow.screen.MarketScreen;
import com.beecow.utils.TestLink;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import java.io.File;

import static com.beecow.model.CommonElement.marketPropertiesFile;
import static com.beecow.model.CommonElement.passed;
import static com.beecow.utils.PropertiesUtils.*;
import static com.beecow.utils.Result.result;

/**
 * Created by HangPham on 12/18/2016.
 */

public class MarketInstructionTest extends BaseTest {

    String sNameTestCaseMethod;
    String sMarket = "Market";
    String className = this.getClass().getSimpleName();

    private ActivityFirstScreen firstScreen;
    private ActivitySecondScreen secondScreen;
    private HomeScreen homeScreen;
    private MarketScreen marketScreen;
    

    String cats[] = {"Sport", "Computer", "Meal Deals"};

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp(marketPropertiesFile);
        firstScreen = new ActivityFirstScreen(driver);
        secondScreen = new ActivitySecondScreen(driver);
        homeScreen = new HomeScreen(driver);
        marketScreen = new MarketScreen(driver);
    }

    @AfterMethod
    public void checkTakeScreenShotAndPassFailTestLink() throws TestLinkAPIException {
        String b = sNameTestCaseMethod.split("_")[3];
        sNameTestCaseMethod = sNameTestCaseMethod.substring(0, sNameTestCaseMethod.lastIndexOf("_")) + "-".concat(b);
        if (result.equals(passed)) {
            getHelper().takeScreenshot(sMarket, className, "Passed_", sNameTestCaseMethod);
            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, sNameTestCaseMethod, testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        } else {
            getHelper().takeScreenshot(sMarket, className, "Failed_", sNameTestCaseMethod);
            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, sNameTestCaseMethod, testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
        }
    }

//    @Test(priority = 0)
    /**
     * AND_MAR_TC_10 - Verify quick instruction is shown when user launches app for first time
     */
    public void AND_MAR_TC_10() throws Exception, TestLinkAPIException {
        sNameTestCaseMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Run testcases: " + sNameTestCaseMethod);
        selectFirstSecondLaunchingAndGoToMarketPage();
        System.out.println("Verify market [Instruction]");
        marketScreen.verifyInstructionText();
        marketScreen.verifyButtonGotIt();
    }

    @Test(priority = 1)
    /**
     * AND_MAR_TC_11 - Verify instruction is closed when user taps [Got it] button
     */
    public void AND_MAR_TC_11() throws Exception, TestLinkAPIException {
        sNameTestCaseMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Run testcases: " + sNameTestCaseMethod);
//        homeScreen.clickMarketTabView();
      selectFirstSecondLaunchingAndGoToMarketPage();
        System.out.println("Click button [Got it]");
        marketScreen.clickButtonGotIt();
        System.out.println("Verify the [Instruction] screen is hidden");
        getSwipe().Swipe(4, 4, 4, 8, 2000);
        marketScreen.verifyInstructionTextInvisible();
        marketScreen.verifyButtonGotItInvisible();
        marketScreen.verifyBannerIsVisible();
        // POST CONDITION: remove app to check tc AND_MAR_TC_12 
        driver.closeApp();
        driver.removeApp(androidAppPackage);
    }

    @Test(dependsOnMethods = { "AND_MAR_TC_11" })
    /**
     * AND_MAR_TC_12 - Verify instruction is closed when user taps anywhere at instruction
     */
    public void AND_MAR_TC_12() throws Exception, TestLinkAPIException {
        sNameTestCaseMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Run testcases: " + sNameTestCaseMethod);
//      homeScreen.clickMarketTabView();
        selectFirstSecondLaunchingAndGoToMarketPage();
        System.out.println("Click anywhere in the instruction");
        marketScreen.clickAnywhereOnInstructon();
        System.out.println("Verify the [Instruction] screen is hidden");
        getSwipe().Swipe(4, 4, 4, 8, 2000);
        marketScreen.verifyInstructionTextInvisible();
        marketScreen.verifyButtonGotItInvisible();
        marketScreen.verifyBannerIsVisible();
    }

    public void selectFirstSecondLaunchingAndGoToMarketPage() throws Exception {
        System.out.println("Begin Select categories for first launching");
        firstScreen.selectCategories(cats);
        System.out.println("Click button [Next] to go second launching");
        firstScreen.clickButtonNext();
        System.out.println("Then click button [Later] to skip select");
        secondScreen.clickButtonLater();
        System.out.println("Click Tab view [Market] to go Market Overview page");
        homeScreen.clickMarketTabView();
    }
}
