package com.beecow.mobile.Market;

import com.beecow.component.BaseTest;
import com.beecow.screen.ActivityFirstScreen;
import com.beecow.screen.ActivitySecondScreen;
import com.beecow.screen.HomeScreen;
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

public class FirstSecondLaunchingTest extends BaseTest {


    String className = this.getClass().getSimpleName();

    private ActivityFirstScreen firstScreen;
    private ActivitySecondScreen secondScreen;
    private HomeScreen homeScreen;
    

    String cats[] = {"Sport", "Computer", "Meal Deals"};
    String catList[] = {"Men's Fashion", "Women's Fashion", "Mobile & Tablet", "Computer", "Camera & TV", "Home & Living", "Mom & Kids", "Health & Beauty", "Sport", "Meal Deals", "Spa Deals", "Entertainment Deals", "Travel Deals"};
    String inds[] = {"Consulting", "Design", "Education"};
    String indList[] = {"Accounting & Auditing Services", "Advertising & Public RelationstAds & PR", "Agriculture/Forestry/Fishing", "Airlines & Aviation", "Architecture", "Automotive", "Banking", "Beauty/Cosmetics", "Biotechnology/Pharmaceuticals", "Broadcasting/Music/Film", "Chemical/Petro-chemical", "Clothing & Textile Manufacturing", "Computer/IT", "Construction", "Consulting", "Design", "Distribution/Logistics", "Education", "Energy/Utilities", "Engineering", "Financial Services", "Food/Beverage Production", "Government", "Healthcare Services", "Hotels/Lodging", "Import/Export/Trade", "Insurance", "Internet Services", "Legal Services", "Manufacturing", "Medical/Hospital", "NGO/INGO/Non-profit", "Performing Arts/Fine Arts", "Personal & Household Services", "Printing/Publishing", "Real Estate/Property", "Recruitment Agencies", "Restaurant/Food Services", "Retail", "Security/Surveillance", "Social Services", "Sports/Physical Recreation", "Telecommunications Services", "Tourism/Travel Services", "Transportation & Storage"};


    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp(marketPropertiesFile);

        firstScreen = new ActivityFirstScreen(driver);
        secondScreen = new ActivitySecondScreen(driver);

    }

    @Test
    public void AND_MAR_TC_51() throws Exception, TestLinkAPIException {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            System.out.println("Verify first screen contain list categories");
//            firstScreen.verifyFirstScreenShouldContainCategories(catList);
            System.out.println("Begin Select categories for first launching");
            firstScreen.selectCategories(cats);
            System.out.println("Click button Next to go second launching");
            firstScreen.clickButtonNext();
            System.out.println("Verify first screen contain list industries");
//            secondScreen.verifySecondScreenShouldContainIndustries(indList);
            System.out.println("Next select industries");
            secondScreen.selectIndustries(inds);
            System.out.println("Then click button Done");
            secondScreen.clickButtonDone();
            TestLink.updateResult(testlinkProjectName,testlinkTestPlanName, "AND_MAR_TC-51", testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }catch (TestLinkAPIException ex){
            System.out.print("Can't update result to Testlink for AND_MAR_TC_51");
        }
        catch (Exception ex) {
            //Test failed
            getHelper().takeScreenshot("Market", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(FirstSecondLaunchingTest.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "AND_MAR_TC-51", testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("Failed: " + ex.getMessage());
        }
    }
}
