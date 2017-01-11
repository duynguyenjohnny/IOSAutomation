package com.beecow.utils;

import com.beecow.component.Constant;

/**
 * Created by HangPham on 12/17/2016.
 */
public class Utils {
    private static Utils utils;

    public static synchronized Utils getInstance(){
        if(utils==null){
            utils = new Utils();
        }
        return utils;
    }

    public static boolean isTypeDevice(Constant.MOBILE_PLATFORM type){
        if(type== Constant.MOBILE_PLATFORM.ANDROID){
            return true;
        }
        if(type==Constant.MOBILE_PLATFORM.IOS){
            return true;
        }
        return false;
    }

    public boolean isAndroidDevice() {
        if(Constant.TYPE_PLATFORM.equals(Constant.MOBILE_PLATFORM.ANDROID)){
            return true;
        }
        return false;
    }

    public boolean isIosDevice() {
        if (Constant.TYPE_PLATFORM.equals(Constant.MOBILE_PLATFORM.IOS)) {
            return true;
        }
        return false;
    }

    public boolean isWebAndroidDevice(){
        if(Constant.TYPE_PLATFORM.equals(Constant.MOBILE_PLATFORM.CHROME_AND)){
            return true;
        }
        return false;
    }

    public boolean isWebIOSDevice(){
        if(Constant.TYPE_PLATFORM.equals(Constant.MOBILE_PLATFORM.SAFARI_IOS)){
            return true;
        }
        return false;
    }
//    public static void main(String[] args){
//        Utils utils=new Utils();
//        utils.isAndroidDevice();
//        utils.isIosDevice();
//    }
}
