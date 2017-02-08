package com.beecow.model;

import com.beecow.utils.Utils;

/**
 * Created by PhuocHa on 01/10/2017.
 */

public class ActivitySecondElement {

    public static String getIndustryLocatorByText(String value){
        if(Utils.getInstance().isAndroidDevice()) return "text::" + value + "";
        return "ios";
    }

    public static String getBtnDone(){
        if(Utils.getInstance().isAndroidDevice()) return "resourceID::bottom_bar_tvPositive";
        return "ios";
    }
}

