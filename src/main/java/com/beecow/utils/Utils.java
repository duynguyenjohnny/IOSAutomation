package com.beecow.utils;

import com.beecow.component.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by HangPham on 12/17/2016.
 */
public class Utils {
    private static Utils utils;
    public static String globalPro = "Global.properties";

    public static synchronized Utils getInstance() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    public static boolean isTypeDevice(Constant.MOBILE_PLATFORM type) {
        if (type == Constant.MOBILE_PLATFORM.ANDROID) {
            return true;
        }
        if (type == Constant.MOBILE_PLATFORM.IOS) {
            return true;
        }
        return false;
    }

    public boolean isAndroidDevice() {
        if (Constant.TYPE_PLATFORM.equals(Constant.MOBILE_PLATFORM.ANDROID)) {
            return true;
        }
        return false;
    }


    public static Properties initProperties(String propertyFile) {
        try{

            Properties pro = new Properties();
            InputStream resourceAsStream = Utils.class.getClassLoader()
                    .getResourceAsStream(propertyFile);
            //InputStream resourceAsStream = Utils.getInstance().getClass().getResourceAsStream(propertyFile);
            pro.load(resourceAsStream);

//            String workingdir = ResourceBundle.getBundle("Global");

//            File f = new File(workingdir + propertyFile);
//            if(f.exists() && !f.isDirectory()) {
//                FileInputStream Master = new FileInputStream(workingdir + propertyFile);
//                pro.load(Master);
//                Master.close();
//                return pro;
//            } catch (FileNotFoundException e) {
//            e.printStackTrace();
            return pro;
        } catch (IOException e) {
            e.printStackTrace();
        }
//    }catch (Exception ex){
//            ex.printStackTrace();
//        }
        return null;
    }

    public static String getPropertyValue(Properties pro, String propertyName) {
        if (pro == null || propertyName == null) {
            return null;//new Exception("properties==null || propertyName==null");
        }
        return pro.getProperty(propertyName);
    }

    private static void initProperties(Properties pro) {
    }

    public boolean isIosDevice() {
        if (Constant.TYPE_PLATFORM.equals(Constant.MOBILE_PLATFORM.IOS)) {
            return true;
        }
        return false;
    }

    public boolean isWebAndroidDevice() {
        if (Constant.TYPE_PLATFORM.equals(Constant.MOBILE_PLATFORM.CHROME_AND)) {
            return true;
        }
        return false;
    }

    public boolean isWebIOSDevice() {
        if (Constant.TYPE_PLATFORM.equals(Constant.MOBILE_PLATFORM.SAFARI_IOS)) {
            return true;
        }
        return false;
    }
}
