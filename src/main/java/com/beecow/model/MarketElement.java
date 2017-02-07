package com.beecow.model;

import com.beecow.utils.Utils;

/**
 * Created by PhuocHa on 01/10/2017.
 */

public class MarketElement {

    public static String getButtonGotIt(){
        if(Utils.getInstance().isAndroidDevice()){
            return "text::Got it";
        }
        return "ios";
    }

    public static String getBannerLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "resourceID::fragment_market_banner_pager";
        }
        return "ios";
    }

    public static String getCurrentBannerLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath::.//*[@id='fragment_market_banner_pager']/android.support.v4.view.ViewPager/android.widget.ImageView";
        }
        return "ios";
    }

    public static String getCurrentBannerHighlightLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath::.//*[@id='pointLayout']/android.widget.ImageView[1]";
        }
        return "ios";
    }
}

