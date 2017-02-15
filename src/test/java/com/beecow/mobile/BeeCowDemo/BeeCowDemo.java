package com.beecow.mobile.BeeCowDemo;


import com.beecow.component.BaseTest;
import com.beecow.screen.*;
import com.beecow.utils.TestLink;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import java.io.File;

import static com.beecow.model.CommonElement.marketPropertiesFile;
import static com.beecow.utils.PropertiesUtils.*;

/**
 * Created by PhuocHa on 01/10/2017.
 */

public class BeeCowDemo extends BaseTest {

    String className = this.getClass().getSimpleName();
    private ActivityFirstScreen firstScreen;
    private ActivitySecondScreen secondScreen;
    private HomeScreen homeScreen;
    private MarketScreen marketScreen;
    private CupidScreen cupidScreen;

    // DATA TEST
    String cats[] = {"Computer", "Sport", "Spa Deals", "Travel Deals", "Meal Deals", "Entertainment Deals"};
    String inds[] = {"Consulting", "Design", "Education"};


    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp(marketPropertiesFile);
        firstScreen = new ActivityFirstScreen(driver);
        secondScreen = new ActivitySecondScreen(driver);
        homeScreen = new HomeScreen(driver);
        marketScreen = new MarketScreen(driver);
        cupidScreen = new CupidScreen(driver);
    }

    @Test
    public void testMarketPageInstruction() throws Exception {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            selectFirstSecondLaunchingAndGoToMarketPage();
            System.out.println("Verify Market Instruction show with description & button [Got It]");
            marketScreen.verifyInstructionText();
            marketScreen.verifyButtonGotIt();

            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "TC-1", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink for TC-1");
        } catch (Exception ex) {
            //Test failed
            getHelper().takeScreenshot("Market", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(BeeCowDemo.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "TC-1", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test(dependsOnMethods = { "testMarketPageInstruction" })
    public void testMarketOverviewPage() throws Exception {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            System.out.println("Click Market Tab view to go Market Overview page");
            homeScreen.clickMarketTabView();
            System.out.println("Click button Got It");
            marketScreen.clickButtonGotIt();
            System.out.println("Verify market banner is visibled");
            marketScreen.verifyBannerIsVisible();

            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "TC-2", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink for TC-2");
        } catch (Exception ex) {
            //Test failed
            getHelper().takeScreenshot("Market", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(BeeCowDemo.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "TC-2", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test(dependsOnMethods = { "testMarketOverviewPage" })
    public void testCupidOverviewPage() throws Exception {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            System.out.println("Click Cupid Tab view to go Cuspid Overview page");
            homeScreen.clickCupidTabView();
            System.out.println("Turn on Cupid feature");
            cupidScreen.TurnCupidFeatureOnOff(true);

            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "TC-3", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink for TC-3");
        } catch (Exception ex) {
            //Test failed
            getHelper().takeScreenshot("Market", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(BeeCowDemo.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "TC-3", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test(dependsOnMethods = { "testCupidOverviewPage" })
    public void testHomePageWithNewFeed() throws Exception {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            System.out.println("Click Cupid Tab");
            homeScreen.clickCupidTabView();
            System.out.println("Click Market Tab");
            homeScreen.clickMarketTabView();
            System.out.println("Click Home Tab");
            homeScreen.clickHomeTabView();

            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "TC-4", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink for TC-4");
        } catch (Exception ex) {
            //Test failed
            getHelper().takeScreenshot("Market", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(BeeCowDemo.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "TC-4", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test(dependsOnMethods = { "testHomePageWithNewFeed" })
    public void testMessagesPageWithButtonGotIt() throws Exception {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            System.out.println("Click Messages Tab");
            homeScreen.clickMessagesTabView();
//            System.out.println("Verify button [Got It]");
//            marketScreen.verifyButtonGotIt();

            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "TC-8", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink for TC-8");
        } catch (Exception ex) {
            //Test failed
            getHelper().takeScreenshot("Market", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(BeeCowDemo.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "TC-8", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    private void selectFirstSecondLaunchingAndGoToMarketPage() {
        System.out.println("Begin Select categories for first launching");
        firstScreen.selectCategories(cats);
        System.out.println("Click button Next to go second launching");
        firstScreen.clickButtonNext();
        System.out.println("Next select industries");
        secondScreen.selectIndustries(inds);
        System.out.println("Then click button Done");
        secondScreen.clickButtonDone();
        System.out.println("Click Market Tab view to go Market Overview page");
        homeScreen.clickMarketTabView();
    }
}
