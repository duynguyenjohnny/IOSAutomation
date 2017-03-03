package com.beecow.mobile.Market.AND;

import com.beecow.component.BaseTest;
import com.beecow.screen.ActivityFirstScreen;
import com.beecow.screen.ActivitySecondScreen;
import com.beecow.screen.HomeScreen;
import com.beecow.screen.MarketScreen;
import com.beecow.utils.TestLink;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import java.io.File;

import static com.beecow.model.CommonElement.marketPropertiesFile;
import static com.beecow.utils.PropertiesUtils.testlinkBuildName;
import static com.beecow.utils.PropertiesUtils.testlinkProjectName;
import static com.beecow.utils.PropertiesUtils.testlinkTestPlanName;

/**
 * Created by HangPham on 12/18/2016.
 */

public class MarketInstructionTest extends BaseTest {


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

    @Test
    /**
     * AND_MAR_TC_10 - Verify quick instruction is shown when user launches app for first time
     */
    public void AND_MAR_TC_10() throws Exception, TestLinkAPIException {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            selectFirstSecondLaunchingAndGoToMarketPage();
            System.out.println("Verify market [Instruction]");
            marketScreen.verifyInstructionText();
            marketScreen.verifyButtonGotIt();

            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "AND_MAR_TC-10", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for AND_MAR_TC_10");
        }
        catch (Exception ex) {
            //Test failed
            getHelper().takeScreenshot("Market", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(MarketInstructionTest.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "AND_MAR_TC-10", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test
    /**
     * AND_MAR_TC_11 - Verify instruction is closed when user taps [Got it] button
     */
    public void AND_MAR_TC_11() throws Exception, TestLinkAPIException {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
//            homeScreen.clickMarketTabView();
            selectFirstSecondLaunchingAndGoToMarketPage();
            System.out.println("Click button [Got it]");
            marketScreen.clickButtonGotIt();
            System.out.println("Verify the [Instruction] screen is hidden");
            marketScreen.verifyInstructionTextInvisible();
            marketScreen.verifyButtonGotItInvisible();

            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "AND_MAR_TC-11", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for AND_MAR_TC_11");
        }
        catch (Exception ex) {
            //Test failed
            getHelper().takeScreenshot("Market", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(MarketInstructionTest.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "AND_MAR_TC-11", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("Failed: " + ex.getMessage());
        }
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
