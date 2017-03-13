package com.beecow.model;

import com.beecow.utils.Utils;

/**
 * Created by PhuocHa on 01/10/2017.
 */

public class MarketElement {

    public static String getButtonGotIt(){
        if(Utils.getInstance().isAndroidDevice()){
            return "resourceID::fragment_market_btn_tutorial";
        }
        return "ios";
    }

    public static String getTextInstruction(){
        if(Utils.getInstance().isAndroidDevice()){
            return "text::Touch, hold and drag a category to reorder your favorite list";
        }
        return "ios";
    }

    public static String getImageBannerLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "resourceID::item_banner_img_banner";
        }
        return "ios";
    }
}

