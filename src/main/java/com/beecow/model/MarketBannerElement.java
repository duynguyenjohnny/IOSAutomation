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
            return "resourceID::banner_view_viewpager";
        }
        return "ios";
    }

    public static String getBannerXpath(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.ImageView[contains(@content-desc,'Banner 1')] ";
        }
        return "ios";
    }
}
