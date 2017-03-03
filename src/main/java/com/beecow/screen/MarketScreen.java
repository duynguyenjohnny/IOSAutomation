package com.beecow.screen;

import static com.beecow.model.MarketElement.*;

import com.beecow.component.CommonScreenObjects;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.testng.Assert;

/**
 * Created by HangPham on 12/18/2016.
 */

public class MarketScreen extends CommonScreenObjects {
    public MarketScreen(AppiumDriver driver){
        super(driver);
    }

    public void verifyInstructionText() {
        getHelper().waitElementIsDisplayed(getHelper().byLocator(getTextInstruction()));
        System.out.println("Market Instruction Page show with text: Touch, hold and drag a category to reorder your favorite list");
    }

    public void verifyInstructionTextInvisible() throws InterruptedException {
        boolean isVisible = getHelper().isElementPresent(getTextInstruction());
        Assert.assertFalse(isVisible);
    }

    public void verifyButtonGotIt(){
        getHelper().isElementEnabled(getButtonGotIt());
        System.out.println("Verify Button [Got It]");
    }

    public void verifyButtonGotItInvisible() {
        boolean isVisible = getHelper().isElementPresent(getButtonGotIt());
        Assert.assertFalse(isVisible);
    }

    public void clickButtonGotIt(){
        getHelper().findElement(getButtonGotIt()).click();
        System.out.println("Click Button [Got It]");
    }

    public void clickAnywhereOnInstructon(){
        getHelper().findElement(getTextInstruction()).click();
        System.out.println("Click text [Instruction]");
    }

    public void verifyBannerIsVisible() {
        getHelper().waitElementIsDisplayed(getHelper().byLocator(getImageBannerLocator()));
        System.out.println("Market Page show with banner is visible on page");
    }
}
