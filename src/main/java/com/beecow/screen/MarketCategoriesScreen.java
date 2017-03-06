package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;

import static com.beecow.component.BaseTest.beeCow_language;
import static com.beecow.model.CommonElement.failed;
import static com.beecow.model.MarketCategoriesElement.*;

/**
 * Created by hangpham on 2017-03-01.
 */
public class MarketCategoriesScreen extends CommonScreenObjects {

    String line = "--------------------------------------------------";
    String sNameMethod;

    public MarketCategoriesScreen(AppiumDriver driver) {
        super(driver);
    }

    //check fail when not found element
    public void checkFail(String observation){
        result.setResult(failed);
        result.setObservation(observation);
        result.check();
    }

    public void clickSeeAllBtnAtCate() throws Exception {
        sNameMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            getHelper().findElement(getSeeAllOrLessBtnCateLevel0()).click();
//            getHelper().findElement("resourceID::124").click();
        } catch (Exception ex) {
            checkFail(" - " + ex.getMessage());
//            throw new Exception("Can not find element - " + ex.getMessage());
        }
    }
    public void clickSeeLessBtnCate(){
        sNameMethod = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            getHelper().findElement(getSeeAllOrLessBtnCateLevel0()).click();
        }catch (Exception ex){
            checkFail("---"+ex.getMessage());
        }
    }

    public void swipeToSeeLessAtCate() {
        //Swipe to See less to top
        WebElement elementTop = getHelper().findElement(getMainViewWithSearchAndFooter());
        for (int i = 0; i < 3; i++) {
            if(getHelper().isElementPresent(getSeeAllOrLessBtnCateLevel0())) {
                WebElement thisElement = getHelper().findElement(getSeeAllOrLessBtnCateLevel0());
                getSwipe().swipingUpFromElementToOtherElement(thisElement, elementTop);
                break;
            }else {
                getSwipe().swipingUpElementFromBottomToTop(elementTop);
            }
        }
    }

    public void checkCategoriesSectionCollapse() {
        result.setExpectation("Categories expand full item product when user clicks [See all] button at categories section");
        int itemProductLevel0 = getHelper().findElements(getSeeAllBtnCateLevel0_tv()).size();
        System.out.println("total item produc: "+itemProductLevel0);
//        if(itemProductLevel0!=8){
//            result.setResult(failed);
//            result.setObservation("Item product at categories level 0 does not show 8 items");
//            result.setExpectation("Item product at categories level 0 should be shown 8 items at categories");
//        }
        if (!getHelper().findElement(getSeeAllBtnCateLevel0_tv()).getText().equals(beeCow_language.seeAll_text)) {
            result.setResult(failed);
            result.setObservation("[See less] text does not exist!!!");
            result.setExpectation("[See less] should be shown when users click [See all]");
        }
        result.check();
    }


    public void checkCategoriesSectionExpand(){
        result.setExpectation("Categories expand full item product when user clicks [See all] button at categories section");
        int itemProductLevel0 = getHelper().findElements(getSeeAllBtnCateLevel0_tv()).size();
        System.out.println("total item produc: "+itemProductLevel0);
        if(itemProductLevel0!=14){
            result.setResult(failed);
            result.setObservation("Item product at categories level 0 does not show 14 items");
            result.setExpectation("Item product at categories level 0 should be shown 14 items at categories");
        }
        if (!getHelper().findElement(getSeeAllBtnCateLevel0_tv()).getText().equals(beeCow_language.seeLess_text)) {
            result.setResult(failed);
            result.setObservation("[See less] text does not exist!!!");
            result.setExpectation("[See less] should be shown when users click [See all]");
        }
        result.check();
    }

    

    public void swipingToElemntThenStop(WebElement element){
        WebElement element1 = getHelper().findElement(getMainViewWithSearchAndFooter());

        for (int i = 0; i < 10; i++) {
            if (getHelper().isElementPresent("text::See less")) {
                System.out.println("Found element!!!");
                break;
            } else {
                getSwipe().swipingUpElementFromBottomToTop(element1);
            }
        }
    }
    public void swipeToElementAndScrollElementToTopThenStopDemo() {
        WebElement element = getHelper().findElement(getMainViewWithSearchAndFooter());

        for (int i = 0; i < 10; i++) {
            System.out.println("text::"+beeCow_language.spotlights_text);
            if (getHelper().isElementPresent(getSpotlightsText())) {
                System.out.println("Found element!!!");
                WebElement el = getHelper().findElement(getSpotlightsText());
                getSwipe().swipingUpFromElementToOtherElement(el, element);
                break;
            } else {
                getSwipe().swipingUpElementFromBottomToTop(element);
            }
        }

    }
    public void testswipe(){
        getSwipe().swipeLeftToRightElement(getHelper().findElement("resourceID::item_banner_img_banner"));
    }
}


