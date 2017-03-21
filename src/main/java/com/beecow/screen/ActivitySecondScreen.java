package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import io.appium.java_client.AppiumDriver;

import static com.beecow.model.ActivityFirstElement.getCategoryLocatorByText;
import static com.beecow.model.ActivitySecondElement.*;

/**
 * Created by PhuocHa on 01/10/2017.
 */

public class ActivitySecondScreen extends CommonScreenObjects {
    public ActivitySecondScreen(AppiumDriver driver){
        super(driver);
    }

    public void selectIndustry(String indName) {
        getHelper().findElement(getIndustryByText(indName)).click();
    }

    public void selectIndustries(String indus[]) {
        for (int i=0; i < indus.length; i++)
            selectIndustry(indus[i]);
    }

    public void clickButtonLater(){
        getHelper().findElement(getBtnLater()).click();
    }

    public void clickButtonDone(){
        getHelper().findElement(getBtnDone()).click();
    }

    public void verifySecondScreenShouldContainIndustry(String indName){
        getHelper().isElementPresent(getCategoryLocatorByText(indName));
    }

    public void verifySecondScreenShouldContainIndustries(String indus[]) {
        for (int i=0; i < indus.length; i++)
            verifySecondScreenShouldContainIndustry(indus[i]);
    }
}
