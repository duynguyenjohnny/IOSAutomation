package com.beecow.utils;

import com.beecow.component.Constant;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

import java.io.*;
import java.util.Properties;

import static com.beecow.model.CommonElement.*;

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

//    public static String getPropertyValue(String propertyFile, String propertyName) throws Exception{
//        try{
//            String value = "";
//            Properties pro = new Properties();
//            String workingdir = Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\main\\resources\\";
//
//            File f = new File(workingdir + propertyFile);
//            if(f.exists() && !f.isDirectory()) {
//                FileInputStream Master = new FileInputStream(workingdir + propertyFile);
//                pro.load(Master);
//                Master.close();
//                return pro.getProperty(propertyName);
//            }else{
//                System.out.println("Get Property Value");
//                return null;
//            }
//        }catch (Exception ex){
//            System.out.println("Exception Error while Get Property Value: " + ex.getMessage());
//            return null;
//        }
//    };
}
