package com.beecow.screen;

import static com.beecow.model.CommonElement.failed;
import static com.beecow.model.MarketElement.*;

import com.beecow.component.CommonScreenObjects;
import io.appium.java_client.AppiumDriver;

/**
 * Created by HangPham on 12/18/2016.
 */

public class MarketScreen extends CommonScreenObjects {
    String sNameMethod;
    public MarketScreen(AppiumDriver driver){
        super(driver);
    }

    //check fail when not found element
    public void checkFail(String observation){
        result.setResult(failed);
        result.setObservation(observation);
        result.check();
    }

    public void verifyInstructionText() {
        result.setExpectation("The [Instruction text] should be visible");
        sNameMethod = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            getHelper().waitElementIsDisplayed(getHelper().byLocator(getTextInstruction()));
        }catch (Exception ex){
            checkFail("The [Instruction text] not show "+ex.getMessage());
        }
    }

    public void verifyInstructionTextInvisible() throws InterruptedException {
        result.setExpectation("The [Instruction text] should be invisible");
        System.out.println("Is instruction text existed == " + getHelper().isElementPresent(getTextInstruction()));
        if (getHelper().isElementPresent(getTextInstruction()))
        {
            result.setResult(failed);
            result.setObservation("Text instruction still visible");
        }
        result.check();
    }

    public void verifyButtonGotIt(){
        result.setExpectation("The button [Got it] should be visible");
        System.out.println("Is button [Got it] existed == " + getHelper().isElementEnabled(getButtonGotIt()));
        if (!getHelper().isElementEnabled(getButtonGotIt()))
        {
            result.setResult(failed);
            result.setObservation("Text button [Got it] NOT EXIST");
        }
        result.check();
    }

    public void verifyButtonGotItInvisible() throws InterruptedException {
        result.setExpectation("The button [Got it] should be invisible");
        System.out.println("Is button [Got it] existed == " + getHelper().isElementPresent(getButtonGotIt()));
        if (getHelper().isElementPresent(getButtonGotIt()))
        {
            result.setResult(failed);
            result.setObservation("Text button [Got it] still visible");
        }
        result.check();
    }

    public void clickButtonGotIt(){
        result.setExpectation("Click the button [Got it]");
        sNameMethod = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            getHelper().findElement(getButtonGotIt()).click();
        }catch (Exception ex){
            checkFail("Button [Got it] NOT EXIST or NOT clickable "+ ex.getMessage());
        }
    }

    public void clickAnywhereOnInstructon(){
        result.setExpectation("Click on [Instruction text]");
        sNameMethod = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            getHelper().findElement(getTextInstruction()).click();
        }catch (Exception ex){
            checkFail("The [Instruction text] NOT EXIST or NOT clickable "+ ex.getMessage());
        }
    }

    public void verifyBannerIsVisible() {
        result.setExpectation("Check the [Banner] is visible");
        sNameMethod = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            getHelper().waitElementIsDisplayed(getHelper().byLocator(getImageBannerLocator()));
        }catch (Exception ex){
            checkFail("The [Banner IMG] NOT EXIST "+ ex.getMessage());
        }
    }
}
