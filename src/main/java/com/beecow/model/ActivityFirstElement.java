package com.beecow.model;

import com.beecow.utils.Utils;

/**
 * Created by PhuocHa on 01/10/2017.
 */

public class ActivityFirstElement {

    public static String getActivityFirstPageLocator(){
        if(Utils.getInstance().isAndroidDevice()) return "resourceID::activity_first_launching_pager";
        return "ios";
    }

    public static String getCategoryLocatorByText(String value){
        if(Utils.getInstance().isAndroidDevice()) return "text::" + value + "";
        return "label::"+value;
    }

    public static String getCategoryByText(String value){
        if(Utils.getInstance().isAndroidDevice()) return "text::" + value + "";
        //return "label::"+value;
        return "xpath:://XCUIElementTypeButton[@name='" + value + "']";
    }

    public static String getBtnNext(){
        if(Utils.getInstance().isAndroidDevice()) return "resourceID::bottom_bar_tvPositive";
        return "xpath:://XCUIElementTypeButton[@name='Next']";
    }
}

