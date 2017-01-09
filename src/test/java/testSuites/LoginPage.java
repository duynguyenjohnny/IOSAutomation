package testSuites;

import config.BaseTest;
import org.testng.annotations.*;
import screenObjects.LoginScreen;
import testlink.api.java.client.TestLinkAPIException;

/**
 * Created by HangPham on 12/18/2016.
 */
public class LoginPage extends BaseTest {
    int i=4;
    private LoginScreen loginScreen;
    @Override
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        loginScreen=new LoginScreen(driver);
    }
    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws InterruptedException {
        if (driver==null)
            return;
            String str="TC-";
            String testCasesID=str.concat(Integer.toString(i));
            loginScreen.checkReportTestLinkLogin(testCasesID);
        i=i+1;
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void testAndroid1() throws TestLinkAPIException {
        System.out.println("check TC-4 is passed");
        loginScreen.clickIndonesiaLanguage();
//        loginScreen.checkTestLinkTC05();
    }
    @Test
    public void testAndroid2() throws TestLinkAPIException {
        System.out.println("check TC-5 is failed");

        loginScreen.checkTestLinkTC04();
    }
    @Test
    public void testAndroid3() throws TestLinkAPIException {
        System.out.println("check TC-6 is passed");
        loginScreen.clickNextButton();
        loginScreen.checkTestLinkTC05();
    }
//    @Test
//    public void testIOS1() throws TestLinkAPIException {
////        System.out.println("check TC-7 is passed");
//        loginScreen.checkTestLinkTC05();
//    }
//    @Test
//    public void testIOS2() throws TestLinkAPIException {
////        System.out.println("check TC-5 is failed");
//
//        loginScreen.checkTestLinkTC04();
//    }
//    @Test
//    public void testIOS3() throws TestLinkAPIException {
////        System.out.println("check TC-6 is passed");
//        loginScreen.checkTestLinkTC05();
//    }

}
