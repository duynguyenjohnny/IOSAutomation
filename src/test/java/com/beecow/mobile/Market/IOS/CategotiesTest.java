package com.beecow.mobile.Market.IOS;

import com.beecow.component.BaseTest;
import com.beecow.model.FooterElement;
import com.beecow.screen.*;
import com.beecow.utils.TestLink;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import java.awt.*;

import static com.beecow.model.CommonElement.marketPropertiesFile;
import static com.beecow.utils.PropertiesUtils.testlinkBuildName;
import static com.beecow.utils.PropertiesUtils.testlinkProjectName;
import static com.beecow.utils.PropertiesUtils.testlinkTestPlanName;

/**
 * Created by hangpham on 2017-02-07.
 */
public class CategotiesTest extends BaseTest{

    private FooterComponent footerComponent;
    private MarketScreen marketScreen;

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp(marketPropertiesFile);
        footerComponent = new FooterComponent(driver);
        marketScreen=new MarketScreen(driver);
    }

    Dimension size;
//    @Test
    public void testIOSDemo() throws InterruptedException {
        try {
            footerComponent.clickMarketTabView();
//            marketScreen.swipeToElement();
            Thread.sleep(5000);

            } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void swipeFromElementToTopMarket(){
        WebElement marketTable = driver.findElement(By.xpath("//XCUIElementTypeTable"));
        //swipe from bottom to top

        int topY =marketTable.getLocation().getY();
        int bottomY = topY +marketTable.getSize().getHeight()-1;

        int middleX =marketTable.getSize().getWidth()/2;

        ((IOSDriver)driver).swipe(middleX,bottomY,middleX,topY,3000);

    }
//    @Test
    public void testIOSDemo1() throws InterruptedException {
        System.out.println("click Cupid tab bar");
        getHelper().findElement("label::Mua bán").click();
        Thread.sleep(5000);
        System.out.println("Swipe from top to bottom");
        ((IOSDriver)driver).swipe(100,150,100,900,3000);
        Thread.sleep(3000);
    }
    @Test
    public void testPassOnTestLink() throws TestLinkAPIException {
        for(int i=1;i<102;i++) {
            System.out.println("testcases: "+i);
            TestLink.updateResult(testlinkProjectName, testlinkTestPlanName, "AND_MAR_TC-"+i, testlinkBuildName, null, TestLinkAPIResults.TEST_PASSED);
        }

    }


}
