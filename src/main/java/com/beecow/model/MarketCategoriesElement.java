package com.beecow.model;

import com.beecow.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by hangpham on 2017-03-01.
 */
public class MarketCategoriesElement {

    //categories title section
    public static String getCateSection(){
        if (Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.TextView[@text='Categories']";
        }
        return "label::Categories";
    }

    public static String getItemCateLevel0ID(){
        if (Utils.getInstance().isAndroidDevice()){
            return "resourceID::item_category_root";
        }
        return "label::Men's Fashion";
    }

    //get overview market except footer and search header
    public static String getMainViewWithSearchAndFooter(){
        if(Utils.getInstance().isAndroidDevice()){
            return "resourceID::fragment_market_content_main_view";
        }
        return "xpath:://XCUIElementTypeTable";
    }
    //example: computer, t-shirt
    public static String getProductItemAtCateLevel0(){
        if(Utils.getInstance().isAndroidDevice()){
            return "resourceID::item_category_border";
        }
        return "";
    }
        public static String getSeeAllBtnCateLevel0(){
        if (Utils.getInstance().isAndroidDevice()) {
            return "resourceID::fragment_market_rl_see_more";
        }
        return "label::See all";
    }
    // get text: see all & down icon --> id
    //fragment_market_tv_see_more, fragment_market_img_see_more
    // categories section: fragment_market_ln_category
//    WebElement WEa = driver.find(By.xpath("//XCUIElementTypeTable//XCUIElementTypeCell[4]"));
//    WebElement WEb = WEa.findElement(".//XCUIElementTypeStaticText[@label='Camera & TV']/ancestor::XCUIElementTypeTable");
//    WebElement a= WEa.findElement(By.xpath("."))


}
