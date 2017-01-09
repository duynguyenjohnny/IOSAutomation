package model;

import libs.Utils;

/**
 * Created by HangPham on 12/18/2016.
 */
public class LoginElement {

    public static String getTestProjectLogin(){
        if(Utils.getInstance().isAndroidDevice()){
            return "Account & Settings (AS)";
        }
        return "Account & Settings (AS)";
    }
    public static String getTestPlanLogin(){
        if(Utils.getInstance().isAndroidDevice()){
            return "Testplan1";
        }
        return "Testplan1";
    }
    public static String getBuildLogin(){
        if(Utils.getInstance().isAndroidDevice()){
            return "Build 1";
        }
        return "Build 1";
    }

    /////////////////////////////////////////////////////////////
    public static String getClickLanguageTwo(){
        if (Utils.getInstance().isAndroidDevice()){
            return "resourceID::vTwo";
        }
        return "iOS";
    }
    public static String getClickLanguageOne(){
        if (Utils.getInstance().isAndroidDevice()){
            return "resourceID::vOne";
        }
        return "iOS";
    }
    public static String getClickNextButton(){
        if (Utils.getInstance().isAndroidDevice()){
            return "resourceID::btnDone";
        }
        return "iOS";
    }
}
