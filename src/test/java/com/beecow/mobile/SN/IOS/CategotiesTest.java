package com.beecow.mobile.SN.IOS;

import com.beecow.component.BaseTest;
import com.beecow.screen.FooterComponent;
import com.beecow.screen.MarketCategoriesScreen;
import com.beecow.screen.MarketScreen;
import com.beecow.utils.TestLink;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import static com.beecow.model.CommonElement.marketPropertiesFile;
import static com.beecow.model.CommonElement.passed;
import static com.beecow.utils.PropertiesUtils.*;
import static com.beecow.utils.Result.result;

/**
 * Created by hangpham on 2017-02-07.
 */
public class CategotiesTest extends BaseTest {
    String line = "--------------------------------------------------";
    String sNameTestCaseMethod;
    String className = this.getClass().getSimpleName();
    String sMarket = "Market";

    private FooterComponent footerComponent;
    private MarketScreen marketScreen;
    private MarketCategoriesScreen marketCategoriesScreen;

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp(marketPropertiesFile);
        footerComponent = new FooterComponent(driver);
        marketScreen = new MarketScreen(driver);
        marketCategoriesScreen = new MarketCategoriesScreen(driver);
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
    public void AND_MAR_TC_7() throws Exception {
        Thread.sleep(5000);
        sNameTestCaseMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();

        System.out.println("Run testcases: " + sNameTestCaseMethod);
        marketCategoriesScreen.swipeToElementAndScrollElementToTopThenStopDemo();
        footerComponent.clickMarketTabView();
        marketCategoriesScreen.swipeToSeeLessAtCate();
        marketCategoriesScreen.clickSeeLessBtnCate();
        marketCategoriesScreen.checkCategoriesSectionCollapse();
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
