package com.beecow.mobile.SN.IOS;

import com.beecow.component.BaseTest;
import com.beecow.screen.ActivityFirstScreen;
import com.beecow.screen.ActivitySecondScreen;
import com.beecow.screen.HomeScreen;
import com.beecow.screen.MarketScreen;
import com.beecow.utils.TestLink;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import java.io.File;

/**
 * Created by hoangnguyen on 3/13/17.
 */
public class IOS_Sprint_01 extends BaseTest {
    String className = this.getClass().getSimpleName();
    private ActivityFirstScreen firstScreen;
    private ActivitySecondScreen secondScreen;
    private HomeScreen homeScreen;
    private MarketScreen marketScreen;
    static String SNPropertiesFileSprint1 = "SN.properties";

    //    // DATA TEST
//    String cats[] = {"Sport", "Computer", "Meal Deals"};
//    String reverse_cats[] = {"Meal Deals", "Computer", "Sport"};
//    String single_cat[] = {"Meal Deals"};
//    String double_cats[] = {"Meal Deals", "Health & Beauty"};
//    String catList[] = {"Men's Fashion", "Women's Fashion", "Mobile & Tablet", "Computer", "Camera & TV", "Home & Living", "Mom & Kids", "Health & Beauty", "Sport", "Meal Deals", "Spa Deals", "Entertainment Deals", "Travel Deals"};
//    String inds[] = {"Consulting", "Design", "Education"};
//    String indList[] = {"Accounting & Auditing Services", "Advertising & Public RelationstAds & PR", "Agriculture/Forestry/Fishing", "Airlines & Aviation", "Architecture", "Automotive", "Banking", "Beauty/Cosmetics", "Biotechnology/Pharmaceuticals", "Broadcasting/Music/Film", "Chemical/Petro-chemical", "Clothing & Textile Manufacturing", "Computer/IT", "Construction", "Consulting", "Design", "Distribution/Logistics", "Education", "Energy/Utilities", "Engineering", "Financial Services", "Food/Beverage Production", "Government", "Healthcare Services", "Hotels/Lodging", "Import/Export/Trade", "Insurance", "Internet Services", "Legal Services", "Manufacturing", "Medical/Hospital", "NGO/INGO/Non-profit", "Performing Arts/Fine Arts", "Personal & Household Services", "Printing/Publishing", "Real Estate/Property", "Recruitment Agencies", "Restaurant/Food Services", "Retail", "Security/Surveillance", "Social Services", "Sports/Physical Recreation", "Telecommunications Services", "Tourism/Travel Services", "Transportation & Storage"};
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp(SNPropertiesFileSprint1);
        firstScreen = new ActivityFirstScreen(driver);
        secondScreen = new ActivitySecondScreen(driver);
        homeScreen = new HomeScreen(driver);
    }

    /*
     IOS_SN_TC_1 Verify adding/removing single category in first launching
     IOS_SN_TC_2 Verify adding/removing multiple categories in first launching
     IOS_SN_TC_3 Verify adding categories feature only appear in first launching
     IOS_SN_TC_4 Verify adding categories feature does not appear in second launching
     IOS_SN_TC_5 Verify navigation between tabs on Main Frame
     IOS_SN_TC_6:Verify in some "scroll-able" lists, user can touch on icon to move on top.
     IOS_SN_TC_7:Verify swipe between tabs on Main Frame
     IOS_SN_TC_8:Verify adding/removing single job in first launching
     IOS_SN_TC_9:Verify adding/removing multiple Jobs in first launching
     IOS_SN_TC_10:Verify adding jobs feature only appears in first launching
     IOS_SN_TC_11:Verify adding jobs feature does not appear in second launching
     */
    @Test
    public void IOS_SN_TC_01() throws Exception, TestLinkAPIException {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            //AddingandRemove_SingleCategory();
            // Assert.assertEquals(,true);
            System.out.println("Begin Select categories for first launching");
            //firstScreen.selectCategories(ActivityFirstScreen.cats);
            System.out.println("Remove some categories");
            //firstScreen.selectCategories(ActivityFirstScreen.reverse_cats);
            System.out.println("Add single category");
            //firstScreen.selectCategories(ActivityFirstScreen.single_cat);
            System.out.println("Click button Next to go second launching");
            firstScreen.clickButtonNext();
            System.out.println("Add double categories");
            //firstScreen.selectCategories(ActivityFirstScreen.single_cat);
            //firstScreen.selectCategories(ActivityFirstScreen.double_cats);
            System.out.println("Click button Next to go second launching");
            //firstScreen.clickButtonNext();
            System.out.println("Add triple categories");
            //firstScreen.selectCategories(ActivityFirstScreen.double_cats);
            //firstScreen.selectCategories(ActivityFirstScreen.reverse_cats);
            System.out.println("Click button Next to go second launching");
            //firstScreen.clickButtonNext();
            //assert second screen appear
            //Assert.assertEquals(true, firstScreen.verifyScreenAppear("Looking for job? Choose an industry"));

            //update if pass to testlink
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-1", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink ");
        } catch (Exception ex) {
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-1", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            throw new Exception("Failed: " + ex.getMessage());
        }
    }
}
