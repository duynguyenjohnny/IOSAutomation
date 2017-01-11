package com.beecow.model;

import com.beecow.utils.Utils;

/**
 * Created by PhuocHa on 01/10/2017.
 */

public class BeeCowLayoutElement {

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

    // Footer

    public static String getTabHomeLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "text::Home";
        }
        return "ios";
    }
    public static String getTabMarketLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "text::Market";
        }
        return "ios";
    }

    public static String getTabMessagesLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "text::Messages";
        }
        return "ios";
    }
    public static String getTabCupidLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "text::Cupid";
        }
        return "ios";
    }
    public static String getTabProfileLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "text::Profile";
        }
        return "ios";
    }
}

