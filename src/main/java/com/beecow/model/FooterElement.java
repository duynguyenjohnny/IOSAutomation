package com.beecow.model;

import com.beecow.utils.Utils;
import io.appium.java_client.AppiumDriver;

import static com.beecow.component.BaseTest.beeCow_language;
import static com.beecow.component.BaseTest.isAndroid;

/**
 * Created by PhuocHa on 01/10/2017.
 */

public class FooterElement {
    // Footer
    public static String getTabHomeLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "resourceID::bottom_navigation_tab_home";
        }
        return "xpath:://XCUIElementTypeButton[@label='Home']";
    }
    public static String getTabMarketLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "resourceID::bottom_navigation_tab_market";
        }
        return "xpath:://XCUIElementTypeButton[@label='Market']";
    }
    public static String getTabMarketLocatorIsSelect(){
        if (isAndroid){
            return "";
        }
        return "xpath:://XCUIElementTypeButton[@label='Cupid' and @value='true']";
    }
    public static String getTabMessagesLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "resourceID::bottom_navigation_tab_message";
        }
        return "xpath:://XCUIElementTypeButton[@label='Messages']";
    }
    public static String getTabCupidLocator() {
        if (Utils.getInstance().isAndroidDevice()) {
            return "resourceID::bottom_navigation_tab_cupid";
        }
        return "xpath:://XCUIElementTypeButton[@label='Cupid']";
    }
    public static String getTabMoreLocator(){
        if(Utils.getInstance().isAndroidDevice()){
            return "resourceID::bottom_navigation_tab_more";
        }
        return "xpath:://XCUIElementTypeButton[@label='More']";
    }
}

