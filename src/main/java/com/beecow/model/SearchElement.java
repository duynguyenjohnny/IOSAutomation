package com.beecow.model;

import com.beecow.utils.Utils;

/**
 * Created by PhuocHa on 01/10/2017.
 */

public class SearchElement {

    // Header
    public static String getInputSearchLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "resourceID::search_box_etSearch";
        }
        return "ios";
    }
    public static String getIvMenuLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "resourceID::search_box_ivMenu";
        }
        return "ios";
    }
}

