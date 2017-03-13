package com.beecow.model;

import com.beecow.utils.Utils;

/**
 * Created by PhuocHa on 02/15/2017.
 */

public class HomeElement {

    public static String getActivityMainLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "resourceID::activity_main_pager";
        }
        return "ios";
    }
}

