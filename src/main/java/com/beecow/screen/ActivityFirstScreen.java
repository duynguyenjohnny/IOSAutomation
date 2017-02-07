package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import com.beecow.utils.Helper;
import io.appium.java_client.AppiumDriver;

import static com.beecow.model.ActivityFristElement.*;

/**
 * Created by PhuocHa on 01/10/2017.
 */

public class ActivityFirstScreen extends CommonScreenObjects {

    public ActivityFirstScreen(AppiumDriver driver){
        super(driver);
    }

    public Helper getHelper(){
        return new Helper(driver);
    }

    public void selectCategory(String catName) {
        getHelper().findElement(getCategoryLocatorByText(catName)).click();
    }

    public void selectCategories(String cats[]) {
        for (int i=0; i < cats.length; i++)
            selectCategory(cats[i]);
    }

    public void clickButtonNext(){
        getHelper().findElement(getBtnNext()).click();
    }

    public void verifyFirstScreenShouldContainCategory(String catName){
        getHelper().isElementPresent(getCategoryLocatorByText(catName));
    }

    public void verifyFirstScreenShouldContainCategories(String cats[]) {
        for (int i=0; i < cats.length; i++)
            verifyFirstScreenShouldContainCategory(cats[i]);
    }
}
