package com.beecow.mobile.Market;

import com.beecow.screen.*;
import com.beecow.utils.TestLink;
import org.testng.annotations.*;

import com.beecow.component.BaseTest;

import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import java.io.File;

/**
 * Created by HangPham on 12/18/2016.
 */

public class MarketBannerTest extends BaseTest {

    String className = this.getClass().getSimpleName();
    private ActivityFirstScreen firstScreen;
    private ActivitySecondScreen secondScreen;
    private HomeScreen homeScreen;
    private MarketScreen marketScreen;
    
    static String marketPropertiesFile = "Market.properties";

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
    }

    @Test
    public void AND_MAR_TC_13() throws Exception, TestLinkAPIException {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            selectFirstSecondLaunchingAndGoToMarketPage();
            System.out.println("Verify button Got It should be enabled");
            marketScreen.verifyButtonGotItShouldBeEnabled();
            System.out.println("Then click button Got It");
            marketScreen.clickButtonGotIt();

            TestLink.updateResult(Testlink_ProjectName,Testlink_TestPlanName, "AND_MAR_TC-13", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for AND_MAR_TC_13");
        }
        catch (Exception ex) {
            //Test failed
            getHelper().takeScreenshot("Market", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(MarketBannerTest.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "AND_MAR_TC-13", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("Failed: " + ex.getMessage());
        }
    }
    @Test
    public void AND_MAR_TC_14() throws Exception, TestLinkAPIException {
        System.out.println("run demo tc14");
        getHelper().findElement("abc").click();
    }

    public void selectFirstSecondLaunchingAndGoToMarketPage() {
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
