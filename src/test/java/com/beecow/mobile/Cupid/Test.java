package com.beecow.mobile.Cupid;

import com.beecow.component.BaseTest;
import com.beecow.screen.ActivityFirstScreen;
import com.beecow.screen.ActivitySecondScreen;
import com.beecow.screen.CupidScreen;
import com.beecow.screen.HomeScreen;
import com.beecow.utils.Helper;
import com.beecow.utils.TestLink;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.BeforeMethod;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import java.io.File;

import static com.beecow.model.CommonElement.cupidPropertiesFile;
import static com.beecow.utils.PropertiesUtils.*;


public class Test extends BaseTest {
    IOSDriver driver1;
    String className = this.getClass().getSimpleName();
    private ActivityFirstScreen firstScreen;
    private ActivitySecondScreen secondScreen;
    private HomeScreen homeScreen;
    private CupidScreen cupidScreen;

    // DATA TEST
    String cats[] = {"Sport", "Computer", "Meal Deals"};
    String inds[] = {"Consulting", "Design", "Education"};


    //@BeforeMethod
    public void setUp() throws Exception {
        super.setUp(cupidPropertiesFile);
        firstScreen = new ActivityFirstScreen(driver);
        secondScreen = new ActivitySecondScreen(driver);
        homeScreen = new HomeScreen(driver);
        cupidScreen = new CupidScreen(driver);

    }

    @org.testng.annotations.Test
    public void AND_MAR_TC_10()throws Exception{
        System.out.print("1");
    }
    @org.testng.annotations.Test
    public void AND_MAR_TC_11()throws Exception{
        System.out.print("2");
    }
    @org.testng.annotations.Test
    public void AND_MAR_TC_12()throws Exception{
        System.out.print("3");
    }
    @org.testng.annotations.Test
    public void AND_MAR_TC_13()throws Exception{
        System.out.print("4");
    }
    @org.testng.annotations.Test
    public void AND_MAR_TC_14()throws Exception{
        System.out.print("5");
    }
    @org.testng.annotations.Test
    public void AAD_MAR_TC_AA()throws Exception{
        System.out.print("5");
    }
    @org.testng.annotations.Test
    public void AAD_MAR_TC_BB()throws Exception{
        System.out.print("5");
    }

}
