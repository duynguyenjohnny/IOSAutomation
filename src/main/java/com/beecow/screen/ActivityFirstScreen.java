package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import com.beecow.utils.Helper;
import com.beecow.utils.Utils;
import io.appium.java_client.AppiumDriver;

import static com.beecow.model.ActivityFirstElement.getBtnNext;
import static com.beecow.model.ActivityFirstElement.getCategoryByText;
import static com.beecow.model.ActivityFirstElement.getCategoryLocatorByText;
import static com.beecow.model.ActivitySecondElement.getBtnDone;
import static com.beecow.model.ActivitySecondElement.getIndustryByText;

/**
 * Created by PhuocHa on 01/10/2017.
 */

public class ActivityFirstScreen extends CommonScreenObjects {

    //DATA TEST
//      public static String[] cats = {"Sport", "Computer", "Meal Deals"};
//    public static String[] reverse_cats = {"Meal Deals", "Computer", "Sport"};
//    public static String[] single_cat = {"Meal Deals"};
//    public static String[] double_cats = {"Meal Deals", "Health & Beauty"};
//    public static String[] catList = {"Mobile & Tablet", "Computer", "Camera & TV", "Home & Living", "Mom & Kids", "Health & Beauty", "Sport", "Meal Deals", "Spa Deals", "Entertainment Deals", "Travel Deals"};
//    public static String[] singleinds = {"Education"};
//    public static String[] inds = {"Automotive", "Architecture", "Banking"};
//    public static String[] indList = {"Accounting & Auditing Services", "Advertising & Public RelationstAds & PR", "Agriculture/Forestry/Fishing", "Airlines & Aviation", "Architecture", "Automotive", "Banking", "Beauty/Cosmetics", "Biotechnology/Pharmaceuticals", "Broadcasting/Music/Film", "Chemical/Petro-chemical", "Clothing & Textile Manufacturing", "Computer/IT", "Construction", "Consulting", "Design", "Distribution/Logistics", "Education", "Energy/Utilities", "Engineering", "Financial Services", "Food/Beverage Production", "Government", "Healthcare Services", "Hotels/Lodging", "Import/Export/Trade", "Insurance", "Internet Services", "Legal Services", "Manufacturing", "Medical/Hospital", "NGO/INGO/Non-profit", "Performing Arts/Fine Arts", "Personal & Household Services", "Printing/Publishing", "Real Estate/Property", "Recruitment Agencies", "Restaurant/Food Services", "Retail", "Security/Surveillance", "Social Services", "Sports/Physical Recreation", "Telecommunications Services", "Tourism/Travel Services", "Transportation & Storage"};

    public ActivityFirstScreen(AppiumDriver driver){
        super(driver);
    }

    public Helper getHelper(){
        return new Helper(driver);
    }

    public void selectCategory(String catName) {
        getHelper().findElement(getCategoryByText(catName)).click();
    }
    public void selectIndustry(String indName) {
        getHelper().findElement(getIndustryByText(indName)).click();
    }

    public void selectCategories(String cats[]) {
        for (int i=0; i < cats.length; i++)
            selectCategory(cats[i]);
    }

    public String getCategoryByText(String value) {
        if (Utils.getInstance().isAndroidDevice()) return "text::" + value + "";
        //return "label::"+value;
        return "xpath:://XCUIElementTypeButton[@name='" + value + "']";
    }

    public void selectIndustries(String indus[]) {
        for (int i=0; i < indus.length; i++)
            selectIndustry(indus[i]);
    }

    public void clickButtonNext(){
        getHelper().findElement(getBtnNext()).click();
    }

    public void clickButtonDone(){
        getHelper().findElement(getBtnDone()).click();
    }

    public void verifyFirstScreenShouldContainCategory(String catName){
        getHelper().isElementPresent(getCategoryByText(catName));
    }

    public void verifyFirstScreenShouldContainCategories(String cats[]) {
        for (int i=0; i < cats.length; i++)
            verifyFirstScreenShouldContainCategory(cats[i]);
    }

    public boolean verifyScreenAppear(String indName){
        getHelper().isElementPresent(getCategoryByText(indName));
        return true;
    }
    public void selectFirstAndSecondLaunching() {
        System.out.println("Begin Select categories for first launching");
        //selectCategories(cats);
        System.out.println("Click button Next to go second launching");
        clickButtonNext();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Next select industries");
        //selectIndustries(inds);
        System.out.println("Then click button Done");
        clickButtonDone();
        //System.out.println("Click Market Tab view to go Market Overview page");
        //homeScreen.clickMarketTabView();
    }

}
