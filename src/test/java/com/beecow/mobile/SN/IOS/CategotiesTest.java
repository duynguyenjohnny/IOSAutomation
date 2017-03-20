package com.beecow.mobile.SN.IOS;
import com.beecow.component.BaseTest;
import com.beecow.model.FooterElement;
import com.beecow.screen.*;
import com.beecow.textLanguage.BeeCow_Language;
import com.beecow.utils.TestLink;
import com.beecow.utils.Utils;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;
import java.util.List;
import java.util.Random;
import static com.beecow.model.CommonElement.marketPropertiesFile;
import static com.beecow.model.CommonElement.passed;
import static com.beecow.model.MarketCategoriesElement.getItemCategories_tv;
import static com.beecow.model.MarketCategoriesElement.getProductItemAtCateLevel0;
import static com.beecow.utils.PropertiesUtils.testlinkBuildName;
import static com.beecow.utils.PropertiesUtils.testlinkProjectName;
import static com.beecow.utils.PropertiesUtils.testlinkTestPlanName;
import static com.beecow.utils.Result.result;
/**
 * Created by hangpham on 2017-02-07.
 */
public class CategotiesTest extends BaseTest {
    String line = "--------------------------------------------------";
    String sNameTestCaseMethod;
    String className = this.getClass().getSimpleName();
    String sMarket = "Market";
    String testCases = " ===== STEP =====> Run test case: ";
    private HomeScreen homeScreen;
    private ActivityFirstScreen firstScreen;
    private MarketCategoriesScreen marketCategoriesScreen;
    private ActivitySecondScreen secondScreen;
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp(marketPropertiesFile);
        homeScreen = new HomeScreen(driver);
        firstScreen = new ActivityFirstScreen(driver);
        secondScreen = new ActivitySecondScreen(driver);
        marketCategoriesScreen = new MarketCategoriesScreen(driver);
    }
    @AfterMethod
    public void checkTakeScreenShotAndPassFailTestLink() throws TestLinkAPIException {
        try {
            String b = sNameTestCaseMethod.split("_")[3];
            sNameTestCaseMethod = sNameTestCaseMethod.substring(0, sNameTestCaseMethod.lastIndexOf("_")) + "-".concat(b);
            System.out.println(sNameTestCaseMethod);
            if (result.equals(passed)) {
                getHelper().takeScreenshot(sMarket, className, "Passed_", sNameTestCaseMethod);
                TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, sNameTestCaseMethod, testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
            } else {
                getHelper().takeScreenshot(sMarket, className, "Failed_", sNameTestCaseMethod);
                TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, sNameTestCaseMethod, testlinkBuildName, null, TestLinkAPIResults.TEST_FAILED);
            }
        } catch (TestLinkAPIException ex) {
            System.out.print("Can't update result to Testlink for: " + sNameTestCaseMethod);
        } catch (Exception ex) {
            marketCategoriesScreen.checkFail("Error: " + ex.getMessage());
        }
    }
    @Test
    public void AND_MAR_TC_5() throws Exception {
        sNameTestCaseMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(sNameTestCaseMethod + ": Verify collapse categories list");
        if (Utils.getInstance().isAndroidDevice()) {
            marketCategoriesScreen.clearAndStartAppAndroid();
        }
        selectFirstSecondLaunchingAndGoToMarketPage();
        homeScreen.clickMarketTabView();
        marketCategoriesScreen.checkCategoriesSectionExpand();
    }
    //    @Test
    public void AND_MAR_TC_6() throws Exception {
        sNameTestCaseMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(testCases + sNameTestCaseMethod + ": Verify collapse categories list");
        homeScreen.clickMarketTabView();
        marketCategoriesScreen.swipeToSeeLessAtCate();
        marketCategoriesScreen.clickSeeLessBtnCate();
        marketCategoriesScreen.checkCategoriesSectionCollapse();
    }
    //    @Test
    public void AND_MAR_TC_7() throws Exception {
        sNameTestCaseMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(testCases + sNameTestCaseMethod + ": Verify expand categories list");
        homeScreen.clickMarketTabView();
        Thread.sleep(3000);
        marketCategoriesScreen.clickSeeAllBtnAtCate();
        marketCategoriesScreen.swipeToSeeLessAtCate();
        marketCategoriesScreen.clickSeeLessBtnCate();
        marketCategoriesScreen.checkCategoriesSectionCollapse();
    }
    //    @Test
    public void AND_MAR_TC_53() throws Exception {
        sNameTestCaseMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(testCases + sNameTestCaseMethod + ": Verify the detail categories will be shown when users click any product item on Categories");
        homeScreen.clickMarketTabView();
        Thread.sleep(2000);
        marketCategoriesScreen.clickRandomItemCategoriesToGoDetail();
    }
    //    @Test
    public void AND_MAR_TC_55() throws Exception {
        sNameTestCaseMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(testCases + sNameTestCaseMethod + ": Verify user can scroll down at detail categories page");
        homeScreen.clickMarketTabView();
        Thread.sleep(2000);
        marketCategoriesScreen.swipeDownAtMarket();
    }
    //    @Test
    public void AND_MAR_TC_56() throws Exception {
        sNameTestCaseMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(testCases + sNameTestCaseMethod + ": Verify user can scroll up at detail categories page");
        homeScreen.clickMarketTabView();
        Thread.sleep(2000);
        marketCategoriesScreen.swipeDownAtMarket();
        Thread.sleep(2000);
        marketCategoriesScreen.swipeUpAtMarket();
    }
    //    @Test
    public void changeLanguage() throws Exception {
        sNameTestCaseMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(testCases + sNameTestCaseMethod + "");
        homeScreen.clickMoreTabView();
        List<WebElement> list = getHelper().findElements("xpath:://XCUIElementTypeStaticText");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                list.get(i).click();
            }
            System.out.println(list.get(i).getText());
        }
        list.get(1).click();
        homeScreen.clickMarketTabView();
        Thread.sleep(3000);
    }
    //    @Test
    public void testC() throws Exception {
        homeScreen.clickMarketTabView();
        getHelper().findElement("xpath:://*[@name='Hot Deals']/parent::XCUIElementTypeCell/preceding::*/*[@name='See all']").click();
        String xpath = "xpath:://*[@name='Categories']/parent::XCUIElementTypeCell/following::XCUIElementTypeCell[1]/XCUIElementTypeStaticText";
//        String xpath = "resourceID::item_category_border";
        List<WebElement> list = getHelper().findElements(xpath);
        System.out.println(list.get(0).getText());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getText());
            if (list.get(0).getText().equals("Women's Fashion")) {
                System.out.println("Right!!!");
            }
            list.get(0).click();
        }
    }
    //    @Test
    public void dragDrop() throws Exception {
        //Tap on Basic usage Playground.
        System.out.println("abc");
        marketCategoriesScreen.clickSeeAllBtnAtCate();
        //Perform drag and drop operation using TouchAction class.
        int i = getHelper().getRandomIndexWithRange(0, 13);
        int j = getHelper().getRandomIndexWithRange(0, 13);
        WebElement ele1 = getHelper().findElements(getProductItemAtCateLevel0()).get(i);
        WebElement ele2 = getHelper().findElements(getItemCategories_tv()).get(j);
        //Created object of TouchAction class.
        TouchAction action = new TouchAction(driver);
        System.out.println("It Is dragging element.");
        //It will hold tap on 3rd element and move to 6th position and then release tap.
        action.longPress(ele1).moveTo(ele2).release().perform();
        System.out.println("Element has been droped at destination successfully.");
    }
    String cats[] = {"Sport", "Computer", "Meal Deals"};
    public void selectFirstSecondLaunchingAndGoToMarketPage() throws Exception {
        System.out.println(" ===== STEP =====> Begin Select categories for first launching");
        firstScreen.selectCategories(cats);
        System.out.println(" ===== STEP =====> Click button [Next] to go second launching");
        firstScreen.clickButtonNext();
        System.out.println(" ===== STEP =====> Then click button [Later] to skip select");
        secondScreen.clickButtonLater();
        System.out.println(" ===== STEP =====> Click Tab view [Market] to go Market Overview page");
        homeScreen.clickMarketTabView();
    }
//    public static void main(String[] args) {
//        String a = "AND_MAR_TC_7";
////        StringBuilder b = new StringBuilder(a);
////        b.replace(a.lastIndexOf("_"), a.lastIndexOf("_") + 1, "-");
////        a = b.toString();
//
//        String b = a.split("_")[3];
//        a = a.substring(0, a.lastIndexOf("_")) + "-".concat(b);
////get error
//        //            FileUtils.write(new File("error-message"), "\n" + line + "\n" + sNameMethod + "\n" + ex.getMessage(), StandardCharsets.UTF_8, true);
//
//
//        System.out.println(a);
//    }
}