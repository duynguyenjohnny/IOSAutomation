package com.beecow.mobile.Market.AND;


import com.beecow.screen.MarketScreen;
import com.beecow.screen.HomeScreen;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.beecow.screen.*;
import com.beecow.utils.TestLink;

import com.beecow.component.BaseTest;

import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import java.io.File;

import static com.beecow.model.CommonElement.marketPropertiesFile;
import static com.beecow.model.CommonElement.passed;
import static com.beecow.utils.PropertiesUtils.testlinkBuildName;
import static com.beecow.utils.PropertiesUtils.testlinkProjectName;
import static com.beecow.utils.PropertiesUtils.testlinkTestPlanName;
import static com.beecow.utils.Result.result;

/**
 * Created by PhuocHa on 02/10/2017.
 */

public class MarketBannerTest extends BaseTest {
    String sNameTestCaseMethod;
    String sMarket = "Market";
    String className = this.getClass().getSimpleName();
    private ActivityFirstScreen firstScreen;
    private ActivitySecondScreen secondScreen;
    private HomeScreen homeScreen;
    private MarketScreen marketScreen;
    private MarketBannerScreen marketBannerScreen;



    // DATA TEST
    String cats[] = {"Sport", "Computer", "Meal Deals"};
    String catList[] = {"Men's Fashion", "Women's Fashion", "Mobile & Tablet", "Computer", "Camera & TV", "Home & Living", "Mom & Kids", "Health & Beauty", "Sport", "Meal Deals", "Spa Deals", "Entertainment Deals", "Travel Deals"};
    String inds[] = {"Consulting", "Design", "Education"};
    String indList[] = {"Accounting & Auditing Services", "Advertising & Public RelationstAds & PR", "Agriculture/Forestry/Fishing", "Airlines & Aviation", "Architecture", "Automotive", "Banking", "Beauty/Cosmetics", "Biotechnology/Pharmaceuticals", "Broadcasting/Music/Film", "Chemical/Petro-chemical", "Clothing & Textile Manufacturing", "Computer/IT", "Construction", "Consulting", "Design", "Distribution/Logistics", "Education", "Energy/Utilities", "Engineering", "Financial Services", "Food/Beverage Production", "Government", "Healthcare Services", "Hotels/Lodging", "Import/Export/Trade", "Insurance", "Internet Services", "Legal Services", "Manufacturing", "Medical/Hospital", "NGO/INGO/Non-profit", "Performing Arts/Fine Arts", "Personal & Household Services", "Printing/Publishing", "Real Estate/Property", "Recruitment Agencies", "Restaurant/Food Services", "Retail", "Security/Surveillance", "Social Services", "Sports/Physical Recreation", "Telecommunications Services", "Tourism/Travel Services", "Transportation & Storage"};

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp(marketPropertiesFile);
        firstScreen = new ActivityFirstScreen(driver);
        secondScreen = new ActivitySecondScreen(driver);
        homeScreen = new HomeScreen(driver);
        marketScreen = new MarketScreen(driver);
        marketBannerScreen = new MarketBannerScreen(driver);
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

    @Test
    /**
     * AND_MAR_TC_1 - Banner section should be displayed
     */
    public void AND_MAR_TC_1() throws Exception {
        sNameTestCaseMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Run test cases: " + sNameTestCaseMethod);

        selectFirstSecondLaunchingAndGoToMarketPage();
        System.out.println("Verify Banner image should be enabled");
        marketBannerScreen.verifyBannerDisplayed();
    }

    @Test
    /**
     * AND_MAR_TC_2 - Banner image can be swipe left
     */
    public void AND_MAR_TC_2() throws Exception, TestLinkAPIException {
        sNameTestCaseMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Run test cases: " + sNameTestCaseMethod);
        homeScreen.clickMarketTabView();
        System.out.println("Verify Banner image can be swipe left");
        marketBannerScreen.swipeBannerLeft();
        marketBannerScreen.swipeBannerLeft();
        marketBannerScreen.swipeBannerLeft();
        marketBannerScreen.swipeBannerLeft();
        marketBannerScreen.swipeBannerLeft();
    }

    @Test
    /**
     * AND_MAR_TC_3 - Banner image can be swipe right
     */
    public void AND_MAR_TC_3() throws Exception, TestLinkAPIException {
        sNameTestCaseMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Run test cases: " + sNameTestCaseMethod);
        homeScreen.clickMarketTabView();
        System.out.println("Verify Banner image can be swipe right");
        marketBannerScreen.swipeBannerRight();
        marketBannerScreen.swipeBannerRight();
        marketBannerScreen.swipeBannerRight();
        marketBannerScreen.swipeBannerRight();
        marketBannerScreen.swipeBannerRight();
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
        System.out.println("Click Button [Got it]");
        marketScreen.clickButtonGotIt();
    }

    @Override
    protected void initData() {

    }
}
