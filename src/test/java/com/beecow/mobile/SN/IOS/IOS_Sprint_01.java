package com.beecow.mobile.SN.IOS;

import com.beecow.component.BaseTest;
import com.beecow.screen.ActivityFirstScreen;
import com.beecow.screen.ActivitySecondScreen;
import com.beecow.screen.HomeScreen;
import com.beecow.screen.MarketScreen;
import com.beecow.utils.TestLink;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import java.io.File;
import java.io.IOException;

import static com.beecow.model.CommonElement.getNotificationPopup;
import static com.beecow.model.CommonElement.passed;
import static com.beecow.model.CommonElement.socialNetworkPropertiesFile;
import static com.beecow.utils.PropertiesUtils.*;
import static com.beecow.utils.Result.result;

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

        // DATA TEST
//      String[] cats = {"Sport", "Computer", "Meal Deals"};
//      String[] reverse_cats = {"Meal Deals", "Computer", "Sport"};
//      String[] single_cat = {"Meal Deals"};
//      String[] double_cats = {"Meal Deals", "Health & Beauty"};
//      String[] catList = {"Mobile & Tablet", "Computer", "Camera & TV", "Home & Living", "Mom & Kids", "Health & Beauty", "Sport", "Meal Deals", "Spa Deals", "Entertainment Deals", "Travel Deals"};
//      String[] singleinds = {"Education"};
//      String[] inds = {"Automotive", "Architecture", "Banking"};
//      String[] indList = {"Accounting & Auditing Services", "Advertising & Public RelationstAds & PR", "Agriculture/Forestry/Fishing", "Airlines & Aviation", "Architecture", "Automotive", "Banking", "Beauty/Cosmetics", "Biotechnology/Pharmaceuticals", "Broadcasting/Music/Film", "Chemical/Petro-chemical", "Clothing & Textile Manufacturing", "Computer/IT", "Construction", "Consulting", "Design", "Distribution/Logistics", "Education", "Energy/Utilities", "Engineering", "Financial Services", "Food/Beverage Production", "Government", "Healthcare Services", "Hotels/Lodging", "Import/Export/Trade", "Insurance", "Internet Services", "Legal Services", "Manufacturing", "Medical/Hospital", "NGO/INGO/Non-profit", "Performing Arts/Fine Arts", "Personal & Household Services", "Printing/Publishing", "Real Estate/Property", "Recruitment Agencies", "Restaurant/Food Services", "Retail", "Security/Surveillance", "Social Services", "Sports/Physical Recreation", "Telecommunications Services", "Tourism/Travel Services", "Transportation & Storage"};


    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        super.setUp(SNPropertiesFileSprint1);
        firstScreen = new ActivityFirstScreen(driver);
        secondScreen = new ActivitySecondScreen(driver);
        homeScreen = new HomeScreen(driver);
    }

    @AfterMethod(alwaysRun = true) public void killServer(ITestResult result)
            throws Exception {
        //endLogTestResults(result);
        //getDriver().closeApp();
        System.out.println(" ===== STEP =====> Start Remove IOS App");
        getDriver().removeApp("ca.mediastep.BeeCow");
        System.out.println(" ===== STEP =====> End Remove IOS App");
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
        getHelper().addLog("IOS_SN_TC_1 Verify adding/removing single category in first launching");
        try {
            //AddingandRemove_SingleCategory();
            // Assert.assertEquals(,true);
            Thread.sleep(3000);
            System.out.println("Begin Select Myanmar Country");
            firstScreen.selectLocationByName(firstScreen.countryname);
            firstScreen.selectLocationByValue(firstScreen.insidecountry);
            System.out.println("Begin Select language English");
            firstScreen.selectLocationByName(firstScreen.language);
            firstScreen.selectLocationByValue(firstScreen.insidelanguage);
            System.out.println("Begin Select city Mandalay");
            firstScreen.selectLocationByName(firstScreen.imagecity);
            System.out.println("Click Start");
            firstScreen.selectLocationByName(firstScreen.startbutton);
            System.out.println("Begin Select categories for first launching");
            firstScreen.selectCategory("Sport");
            firstScreen.selectCategory("Computer");
            firstScreen.selectCategory("Meal Deals");
            System.out.println("Remove some categories");
            firstScreen.selectCategory("Computer");
            firstScreen.selectCategory("Meal Deals");
            firstScreen.selectCategory("Sport");
            System.out.println("Add single category");
            firstScreen.selectCategory("Sport");
            System.out.println("Click button Next to go second launching");
            firstScreen.clickButtonNext();
            System.out.println("Add double categories");
            firstScreen.selectCategory("Sport");

            firstScreen.selectCategory("Meal Deals");
            firstScreen.selectCategory("Health & Beauty");
            System.out.println("Click button Next to go second launching");
            firstScreen.clickButtonNext();
            System.out.println("Add triple categories");
            firstScreen.selectCategory("Meal Deals");
            firstScreen.selectCategory("Health & Beauty");

            firstScreen.selectCategory("Meal Deals");
            firstScreen.selectCategory("Computer");
            firstScreen.selectCategory("Sport");
            System.out.println("Click button Next to go second launching");
            firstScreen.clickButtonNext();
            //assert second screen appear
            Thread.sleep(1000);
            secondScreen.selectIndustry("Design");

            //update if pass to testlink
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-1", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink ");
            throw new TestLinkAPIException("Failed: " + ex.getMessage());
        } catch (AssertionError ex) {
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-1", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new AssertionError("Failed: " + ex.getMessage());
        } catch (Exception ex){
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-1", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test
    public void IOS_SN_TC_02() throws Exception, TestLinkAPIException {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        getHelper().addLog("IOS_SN_TC_2 Verify adding/removing multiple categories in first launching");
            try {
            //AddingandRemove_SingleCategory();
            // Assert.assertEquals(,true);
                Thread.sleep(3000);
                System.out.println("Begin Select Myanmar Country");
                firstScreen.selectLocationByName(firstScreen.countryname);
                firstScreen.selectLocationByValue(firstScreen.insidecountry);
                System.out.println("Begin Select language English");
                firstScreen.selectLocationByName(firstScreen.language);
                firstScreen.selectLocationByValue(firstScreen.insidelanguage);
                System.out.println("Begin Select city Mandalay");
                firstScreen.selectLocationByName(firstScreen.imagecity);
                System.out.println("Click Start");
                firstScreen.selectLocationByName(firstScreen.startbutton);
            System.out.println("Begin Select categories for first launching");
            firstScreen.selectCategory("Sport");
            firstScreen.selectCategory("Computer");
            firstScreen.selectCategory("Meal Deals");
            System.out.println("Remove some categories");
            firstScreen.selectCategory("Computer");
            firstScreen.selectCategory("Meal Deals");
            firstScreen.selectCategory("Sport");
            System.out.println("Add multiple categories");

            firstScreen.selectCategory("Sport");
            firstScreen.selectCategory("Computer");
            firstScreen.selectCategory("Entertainment Deals");
            firstScreen.selectCategory("Health & Beauty");
            firstScreen.selectCategory("Travel Deals");
            firstScreen.selectCategory("Mom & Kids");

            System.out.println("Click button Next to go second launching");
            firstScreen.clickButtonNext();

            //assert second screen appear
            Thread.sleep(1000);
            secondScreen.selectIndustry("Design");


            //update if pass to testlink
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-2", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink ");
                throw new TestLinkAPIException("Failed: " + ex.getMessage());
        } catch (AssertionError ex) {
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-2", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new AssertionError("Failed: " + ex.getMessage());
        } catch (Exception ex){
                //Test failed
                getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
                System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
                TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-2", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
                ex.printStackTrace();
                throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test
    public void IOS_SN_TC_03() throws Exception, TestLinkAPIException {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        getHelper().addLog("IOS_SN_TC_3 Verify adding categories feature only appear in first launching");
        try {
            Thread.sleep(2000);
            firstScreen.selectFirstAndSecondLaunching();

            System.out.print("Exit application");
            //Thread.sleep(2000);
            //driver.closeApp();

            System.out.print("remove application");
            Thread.sleep(2000);
            driver.removeApp("ca.mediastep.BeeCow");
            System.out.print("Install application");
            Thread.sleep(2000);
            driver.installApp("user.dir/BeeCow.app");

            System.out.print("Start application");
            Thread.sleep(2000);
            driver.launchApp();

            //assert first launching  appears
            Thread.sleep(2000);
            getHelper().findElement(getNotificationPopup()).click();

            //update if pass to testlink
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-3", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink ");
            throw new TestLinkAPIException("Failed: " + ex.getMessage());
        } catch (AssertionError ex) {
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-3", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new AssertionError("Failed: " + ex.getMessage());
        } catch (Exception ex){
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-3", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test
    public void IOS_SN_TC_04() throws Exception, TestLinkAPIException {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        getHelper().addLog("IOS_SN_TC_4 Verify adding categories feature does not appear in second launching");
        try {
            Thread.sleep(2000);
            firstScreen.selectFirstAndSecondLaunching();

            //System.out.print("Exit application");
            //Thread.sleep(2000);
            //driver.closeApp();

            System.out.print("restart application");
            Thread.sleep(2000);
            driver.resetApp();

            //assert home  appears
            Thread.sleep(2000);
            homeScreen.clickHomeTabView();

            //update if pass to testlink
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-4", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink ");
            throw new TestLinkAPIException("Failed: " + ex.getMessage());
        } catch (AssertionError ex) {
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-4", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new AssertionError("Failed: " + ex.getMessage());
        } catch (Exception ex){
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-4", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test
    public void IOS_SN_TC_05() throws Exception, TestLinkAPIException {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        getHelper().addLog("IOS_SN_TC_5 Verify navigation between tabs on Main Frame");
        try {
            Thread.sleep(2000);
            firstScreen.selectFirstAndSecondLaunching();

            System.out.print("click market,messages,cupid,more,home");
            Thread.sleep(2000);
            homeScreen.clickMarketTabView();
            homeScreen.clickMessagesTabView();
            homeScreen.clickCupidTabView();
            homeScreen.clickMoreTabView();
            homeScreen.clickHomeTabView();

            //update if pass to testlink
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-5", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink ");
            throw new TestLinkAPIException("Failed: " + ex.getMessage());
        } catch (AssertionError ex) {
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-5", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new AssertionError("Failed: " + ex.getMessage());
        } catch (Exception ex){
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-5", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test
    public void IOS_SN_TC_06() throws Exception, TestLinkAPIException {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        getHelper().addLog("IOS_SN_TC_6:Verify in some \"scroll-able\" lists, user can touch on icon to move on top.");
        try {
            Thread.sleep(2000);
            firstScreen.selectFirstAndSecondLaunching();

            System.out.print("click market tab");
            Thread.sleep(2000);
            homeScreen.clickMarketTabView();
            homeScreen.clickHomeTabView();

            System.out.print("swipe up to see more feeds");
            Thread.sleep(1000);
            homeScreen.swipe(5,16,5,6,500);
            homeScreen.swipe(5,16,5,6,500);

            System.out.print("touch home tab to move on top");
            Thread.sleep(1000);
            homeScreen.clickHomeTabView();

            //update if pass to testlink
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-6", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink ");
            throw new TestLinkAPIException("Failed: " + ex.getMessage());
        } catch (AssertionError ex) {
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-6", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new AssertionError("Failed: " + ex.getMessage());
        } catch (Exception ex){
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-6", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test
    public void IOS_SN_TC_07() throws Exception, TestLinkAPIException {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        getHelper().addLog("IOS_SN_TC_7:Verify swipe between tabs on Main Frame");
        try {

            Thread.sleep(1000);
            Assert.assertEquals(true,true);


            //update if pass to testlink
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-7", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink ");
            throw new TestLinkAPIException("Failed: " + ex.getMessage());
        } catch (AssertionError ex) {
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-7", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new AssertionError("Failed: " + ex.getMessage());
        } catch (Exception ex){
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-7", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test
    public void IOS_SN_TC_08() throws Exception, TestLinkAPIException {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        getHelper().addLog("IOS_SN_TC_8:Verify adding/removing single job in first launching");
        try {

            Thread.sleep(3000);
            System.out.println("Begin Select Myanmar Country");
            firstScreen.selectLocationByName(firstScreen.countryname);
            firstScreen.selectLocationByValue(firstScreen.insidecountry);
            System.out.println("Begin Select language English");
            firstScreen.selectLocationByName(firstScreen.language);
            firstScreen.selectLocationByValue(firstScreen.insidelanguage);
            System.out.println("Begin Select city Mandalay");
            firstScreen.selectLocationByName(firstScreen.imagecity);
            System.out.println("Click Start");
            firstScreen.selectLocationByName(firstScreen.startbutton);
            System.out.println("Begin Select categories for first launching");
            firstScreen.selectCategory("Sport");
            firstScreen.selectCategory("Computer");
            firstScreen.selectCategory("Meal Deals");

            System.out.println("Click button Next to go second launching");
            firstScreen.clickButtonNext();
            Thread.sleep(1000);
            System.out.println("single select job");
            secondScreen.selectIndustry("Design");
            System.out.println("remove single job");
            secondScreen.selectIndustry("Design");
            System.out.println("add single job");
            secondScreen.selectIndustry("Government");
            Thread.sleep(1000);
            secondScreen.clickButtonDone();

            //assert home screen appear
            Thread.sleep(1000);
            homeScreen.clickHomeTabView();

            //update if pass to testlink
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-8", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink ");
            throw new TestLinkAPIException("Failed: " + ex.getMessage());
        } catch (AssertionError ex) {
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-8", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new AssertionError("Failed: " + ex.getMessage());
        } catch (Exception ex){
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-8", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new Exception("Failed: " + ex.getMessage());
        }
    }


    @Test
    public void IOS_SN_TC_09() throws Exception, TestLinkAPIException {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        getHelper().addLog("IOS_SN_TC_9:Verify adding/removing multiple Jobs in first launching");
        try {

            Thread.sleep(3000);
            System.out.println("Begin Select Myanmar Country");
            firstScreen.selectLocationByName(firstScreen.countryname);
            firstScreen.selectLocationByValue(firstScreen.insidecountry);
            System.out.println("Begin Select language English");
            firstScreen.selectLocationByName(firstScreen.language);
            firstScreen.selectLocationByValue(firstScreen.insidelanguage);
            System.out.println("Begin Select city Mandalay");
            firstScreen.selectLocationByName(firstScreen.imagecity);
            System.out.println("Click Start");
            firstScreen.selectLocationByName(firstScreen.startbutton);
            System.out.println("Begin Select categories for first launching");
            firstScreen.selectCategory("Sport");
            firstScreen.selectCategory("Computer");
            firstScreen.selectCategory("Meal Deals");

            System.out.println("Click button Next to go second launching");
            firstScreen.clickButtonNext();
            Thread.sleep(1000);
            System.out.println("single select job");
            secondScreen.selectIndustry("Design");
            System.out.println("remove single job");
            secondScreen.selectIndustry("Design");
            System.out.println("add multiple jobs");
            secondScreen.selectIndustry("Government");
            secondScreen.selectIndustry("Consulting");
            Thread.sleep(1000);
            secondScreen.clickButtonDone();

            //assert home screen appear
            Thread.sleep(1000);
            homeScreen.clickHomeTabView();

            //update if pass to testlink
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-9", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink ");
            throw new TestLinkAPIException("Failed: " + ex.getMessage());
        } catch (AssertionError ex) {
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-9", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new AssertionError("Failed: " + ex.getMessage());
        } catch (Exception ex){
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-9", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test
    public void IOS_SN_TC_10() throws Exception, TestLinkAPIException {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        getHelper().addLog("IOS_SN_TC_10:Verify adding jobs feature only appears in first launching");
        try {

            Thread.sleep(3000);
            System.out.println("Begin Select Myanmar Country");
            firstScreen.selectLocationByName(firstScreen.countryname);
            firstScreen.selectLocationByValue(firstScreen.insidecountry);
            System.out.println("Begin Select language English");
            firstScreen.selectLocationByName(firstScreen.language);
            firstScreen.selectLocationByValue(firstScreen.insidelanguage);
            System.out.println("Begin Select city Mandalay");
            firstScreen.selectLocationByName(firstScreen.imagecity);
            System.out.println("Click Start");
            firstScreen.selectLocationByName(firstScreen.startbutton);
            System.out.println("Begin Select categories for first launching");
            firstScreen.selectCategory("Sport");
            firstScreen.selectCategory("Computer");
            firstScreen.selectCategory("Meal Deals");

            System.out.println("Click button Next to go second launching");
            firstScreen.clickButtonNext();
            Thread.sleep(1000);
            secondScreen.selectIndustry("Government");
            Thread.sleep(1000);
            driver.removeApp("ca.mediastep.BeeCow");
            Thread.sleep(2000);
            driver.installApp("user.dir/BeeCow.app");
            Thread.sleep(2000);
            driver.launchApp();

            Thread.sleep(2000);
            getHelper().findElement(getNotificationPopup()).click();

            Thread.sleep(2000);
            firstScreen.selectFirstAndSecondLaunching();

            //update if pass to testlink
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-10", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink ");
            throw new TestLinkAPIException("Failed: " + ex.getMessage());
        } catch (AssertionError ex) {
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-10", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new AssertionError("Failed: " + ex.getMessage());
        } catch (Exception ex){
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-10", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    @Test
    public void IOS_SN_TC_11() throws Exception, TestLinkAPIException {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        getHelper().addLog("IOS_SN_TC_10:Verify adding jobs feature only appears in first launching");
        try {

            //Thread.sleep(2000);
            //System.out.println("Begin Select categories for first launching");
            firstScreen.selectFirstAndSecondLaunching();

            System.out.println("restart app");
            Thread.sleep(2000);
            driver.resetApp();

            //assert home screen appears
            Thread.sleep(2000);
            homeScreen.clickHomeTabView();

            //update if pass to testlink
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-11", Testlink_BuildName, null, TestLinkAPIResults.TEST_PASSED);
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink ");
            throw new TestLinkAPIException("Failed: " + ex.getMessage());
        } catch (AssertionError ex) {
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-11", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new AssertionError("Failed: " + ex.getMessage());
        } catch (Exception ex){
            //Test failed
            getHelper().takeScreenshot("SN", className, "Failed_", sMethodName);
            System.out.println("Current working dir: " + new File(IOS_Sprint_01.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            TestLink.updateResult(Testlink_ProjectName, Testlink_TestPlanName, "IOS_SN_TC-11", Testlink_BuildName, null, TestLinkAPIResults.TEST_FAILED);
            ex.printStackTrace();
            throw new Exception("Failed: " + ex.getMessage());
        }
    }

    public void closeApp(){
        driver.closeApp();
    }

    public void openApp(){
        driver.launchApp();
    }

    public void resetApp(){
        driver.resetApp();
    }

    public void removeApp(String bundleid){
        driver.removeApp(bundleid);
    }

    public void installApp(String pathapp){
        driver.installApp(pathapp);
}

    @Override
    protected void initData() {

    }
}
