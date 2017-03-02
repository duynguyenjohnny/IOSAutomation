package com.beecow.model;

import com.beecow.utils.Utils;

/**
 * Created by hangpham on 2017-03-01.
 */
public class MarketCategoriesElement {

    //categories title section
    public static String getCategoriesSection(){
        if (Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.TextView[@text='Categories']";
        }
        return "";
    }

    public static String getItemCategoriesLevel0ID(){
        if (Utils.getInstance().isAndroidDevice()){
            return "resourceID::item_category_root";
        }
        return "";
    }

    //get overview market except footer and search header
    public static String getMainViewWithSearchAndFooter(){
        if(Utils.getInstance().isAndroidDevice()){
            return "resourceID::fragment_market_content_main_view";
        }
        return "";
    }

}
