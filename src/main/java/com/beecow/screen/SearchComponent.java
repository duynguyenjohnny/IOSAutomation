package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import io.appium.java_client.AppiumDriver;

import static com.beecow.model.SearchElement.*;

/**
 * Created by Phuoc Ha on 01/10/2017.
 */

public class SearchComponent extends CommonScreenObjects {

    public SearchComponent(AppiumDriver driver){
        super(driver);
    }

    // Header

    public void search(String value) {
        clickInputSearch();
        InputTextSearch(value);
    }

    public void waitUntilSearchHeaderIsDisplayed() {
        getHelper().waitElementByID(getInputSearchLocator());
    }

    public void clickInputSearch() {
        getHelper().findElement(getInputSearchLocator()).click();
    }

    public void InputTextSearch(String value) {
        System.out.println("Input text search");

        getHelper().findElement(getInputSearchLocator()).sendKeys(value);
    }

    public void clickIvMenu() {
        getHelper().findElement(getIvMenuLocator()).click();
    }
}
