package com.beecow.mobile.Market.IOS;

import com.beecow.component.BaseTest;
import com.beecow.model.FooterElement;
import com.beecow.screen.*;
import com.beecow.utils.TestLink;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import java.awt.*;
import java.io.File;
import java.nio.charset.StandardCharsets;

import static com.beecow.model.CommonElement.marketPropertiesFile;
import static com.beecow.utils.PropertiesUtils.testlinkBuildName;
import static com.beecow.utils.PropertiesUtils.testlinkProjectName;
import static com.beecow.utils.PropertiesUtils.testlinkTestPlanName;

/**
 * Created by hangpham on 2017-02-07.
 */
public class CategotiesTest extends BaseTest{
    String line = "--------------------------------------------------";

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

    @Test
    public void AND_MAR_TC_7() throws Exception {
        String sMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            marketCategoriesScreen.clickSeeAllBtnAtCate();
            marketCategoriesScreen.checkCategoriesSectionExpand();

        }
        catch (Exception ex){
            FileUtils.write(new File("error-message"), "\n"+line + "\n"+sMethodName + "\n" + ex.getMessage(), StandardCharsets.UTF_8, true);
            throw new Exception("Can not find element - "+ex.getMessage());

        }

    }



}
