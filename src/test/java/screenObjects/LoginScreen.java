package screenObjects;

import config.CommonScreenObjects;
import io.appium.java_client.AppiumDriver;
import libs.Utils;
import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import testlink.api.java.client.TestLinkAPIException;


import java.util.HashMap;
import java.util.Map;

import static model.CommonElement.failed;
import static model.CommonElement.passed;
import static model.CommonElement.screenShot_login;
import static model.LoginElement.*;

/**
 * Created by HangPham on 12/18/2016.
 */
public class LoginScreen extends CommonScreenObjects{
    public LoginScreen(AppiumDriver driver){
        super(driver);
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
                getHelper().takeScreenshot(screenShot_login,"Login_passed\\", testCaseID);
                getHelper().updateTestLinkResult(getTestProjectLogin(), getTestPlanLogin(), testCaseID, getBuildLogin(), null, sResult);
            } catch (TestLinkAPIException e) {
                e.printStackTrace();
            }

        } else {
            try {
                System.out.println("Result: " + sResult);
                getHelper().takeScreenshot(screenShot_login,"Login_failed\\", testCaseID);
                getHelper().updateTestLinkResult(getTestProjectLogin(), getTestPlanLogin(), testCaseID, getBuildLogin(), null, sResult);
            } catch (TestLinkAPIException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }



    public void testdemo() {

        getHelper().findElement(getClickLanguageTwo()).click();
//        getHelper().findElement(getClickLanguageOne()).click();
        getHelper().findElement(getClickNextButton()).click();
//        driver.findElement(By.id("com.foody.vn.activity.demo:id/vTwo")).click();
//        driver.findElement(By.id("com.foody.vn.activity.demo:id/vOne")).click();
//        driver.findElement(By.id("com.foody.vn.activity.demo:id/btnDone")).click();
    }
    public void checkTestLinkTC04() throws TestLinkAPIException {
        String str1="a";
        String str2="a";
        if(str1.equals(str2)){
            result.setResult(failed);
            result.setObservation("should be passed");
        }
//        result.check();
    }
    public void checkTestLinkTC05() throws TestLinkAPIException {
        String str1="a";
        String str2="b";
        if(str1.equals(str2)){
            result.setResult(failed);
            result.setObservation("should be passed");
        }
//        result.check();
    }
    public void checkTestLinkTC07(){
        System.out.println("check fail");
        getHelper().findElement("abc").click();
    }
    public void clickIndonesiaLanguage(){
        System.out.println("click indonesia language");
        getHelper().findElement(getClickLanguageTwo()).click();
    }
    public void clickNextButton(){
        System.out.println("click next button");
        getHelper().findElement(getClickNextButton()).click();
    }

}
