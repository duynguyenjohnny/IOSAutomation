package com.beecow.model;

import com.beecow.utils.Utils;

/**
 * Created by hangpham on 2017-02-07.
 */
public class MarketBannerElement {
    public static String getIndicatorBanner(){
        if(Utils.getInstance().isAndroidDevice()){
            return "resourceID::banner_view_indicator";
        }
        return "value::page 1 of 4";
    }
//"xpath:://XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeOther[1]/XCUIElementTypePageIndicator[1]"
    public static String getImageBannerID(){
        if(Utils.getInstance().isAndroidDevice()){
            return "resourceID::item_banner_img_banner";
        }
        return "ios";
    }

    public static String getImageBannerXpath(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.ImageView[@id='item_banner_img_banner'] ";
        }
        return "ios";
    }
}
