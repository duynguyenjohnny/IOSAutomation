package com.beecow.screen;

import static com.beecow.model.CommonElement.failed;
import static com.beecow.model.CommonElement.screenShot_login;
import static com.beecow.model.MarketElement.*;

import io.appium.java_client.AppiumDriver;
import static com.beecow.component.Constant.*;
import testlink.api.java.client.TestLinkAPIException;

/**
 * Created by HangPham on 12/18/2016.
 */

public class MarketScreen extends SearchComponent {
    public MarketScreen(AppiumDriver driver){
        super(driver);
    }

    public void swipeBannerLeft() {
        getHelper().swipeRightToLeftElement(getHelper().findElement(getCurrentBannerLocator()));
    }

    public void swipeBannerRight() {
        getHelper().swipeLeftToRightElement(getHelper().findElement(getBannerLocator()));
    }

    public void verifyBannerIsVisible() {
        getHelper().waitElementIsDisplayed(getHelper().byLocator(getBannerLocator()));
        System.out.println("Market Page show with banner is passed");
    }

    public void verifyMarketPage(){
        int a=1;
        int b=2;
        if(a==b){
            result.setResult(failed);
            result.setObservation("a should !=b");
        }
        result.check();
    }


    /**
     * report pass/fail for each screen
     * @param testCaseID
     */
    public void checkReportTestLinkLogin(String testCaseID) {
        String sResult = result.getResult();
        if (sResult.equals("p")) {
            try {
                System.out.println("Result: " + sResult);
                getHelper().takeScreenshot(screenShot_login,"Market_passed\\", testCaseID);
                getHelper().updateTestLinkResult(TEST_PROJECT, TEST_PLAN, testCaseID, TEST_BUILD, null, sResult);
            } catch (TestLinkAPIException e) {
                e.printStackTrace();
            }

        } else {
            try {
                System.out.println("Result: " + sResult);
                getHelper().takeScreenshot(screenShot_login,"Market_failed\\", testCaseID);
                getHelper().updateTestLinkResult(TEST_PROJECT, TEST_PLAN, testCaseID, TEST_BUILD, null, sResult);
            } catch (TestLinkAPIException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
//
//
//
//    public void testdemo() {
//
//        getHelper().findElement(getClickLanguageTwo()).click();
////        getHelper().findElement(getClickLanguageOne()).click();
//        getHelper().findElement(getClickNextButton()).click();
////        driver.findElement(By.id("com.foody.vn.activity.demo:id/vTwo")).click();
////        driver.findElement(By.id("com.foody.vn.activity.demo:id/vOne")).click();
////        driver.findElement(By.id("com.foody.vn.activity.demo:id/btnDone")).click();
//    }
//    public void checkTestLinkTC04() throws TestLinkAPIException {
//        String str1="a";
//        String str2="a";
//        if(str1.equals(str2)){
//            result.setResult(failed);
//            result.setObservation("should be passed");
//        }
////        result.check();
//    }
//    public void checkTestLinkTC05() throws TestLinkAPIException {
//        String str1="a";
//        String str2="b";
//        if(str1.equals(str2)){
//            result.setResult(failed);
//            result.setObservation("should be passed");
//        }
////        result.check();
//    }
//    public void checkTestLinkTC07(){
//        System.out.println("check fail");
//        getHelper().findElement("abc").click();
//    }
//    public void clickIndonesiaLanguage(){
//        System.out.println("click indonesia language");
//        getHelper().findElement(getClickLanguageTwo()).click();
//    }
//    public void clickNextButton(){
//        System.out.println("click next button");
//        getHelper().findElement(getClickNextButton()).click();
//    }

}
