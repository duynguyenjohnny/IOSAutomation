package com.beecow.utils;

import com.beecow.component.Constant;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.ResourceBundle;

import static java.nio.file.StandardCopyOption.*;

/**
 * Created by HangPham on 12/17/2016.
 */
public class Utils {
    private static Utils utils;
    public static String globalPro = "Global.properties";

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

    public static Properties initProperties(String propertyFile) {
        try{

            Properties pro = new Properties();
            InputStream resourceAsStream = Utils.class.getClassLoader()
                    .getResourceAsStream(propertyFile);
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

    /**
     * This function will copy .apk file from share server (config in Global.properties) to local
     * @return
     * @throws Exception
     */
    public static String GetLastAPKFile() throws Exception{
        try{
            Properties initPro = initProperties(globalPro);
            String destFilename = getPropertyValue(initPro, "Android_APKFile");
            FileOutputStream fileOutputStream;
            InputStream fileInputStream;
            byte[] buf;
            int len;
            String sNetworkShare_User = getPropertyValue(initPro,  "NetworkShare_User");
            String sNetworkShare_Pass = getPropertyValue(initPro,  "NetworkShare_Pass");
            String url = "smb:" + getPropertyValue(initPro,  "Android_APKFolder");
            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, sNetworkShare_User, sNetworkShare_Pass);
            SmbFile dir = new SmbFile(url, auth);
            //File folder = new File(dir.getPath());

            //check folder exists or not

            if (dir.exists() && dir.isDirectory()) {
                SmbFile[] files = dir.listFiles();
                long lastMod = Long.MIN_VALUE;
                SmbFile choice = null;
                for (SmbFile file : files) {
                    if(file.getName().toLowerCase().contains("release")){
                        continue;
                    }
                    if (file.lastModified() > lastMod) {
                        choice = file;
                        lastMod = file.lastModified();
                    }
                }
                if(choice != null){
                    fileOutputStream = new FileOutputStream(destFilename);
                    fileInputStream = choice.getInputStream();
                    buf = new byte[16 * 1024 * 1024];
                    while ((len = fileInputStream.read(buf)) > 0) {
                        fileOutputStream.write(buf, 0, len);
                    }
                    fileInputStream.close();
                    fileOutputStream.close();
                    return destFilename;
                }else{
                    return null;
                }
            }else{
                return null;
            }
        } catch (Exception ex) {
            return "Function GetLastAPKFile - Error: " + ex.getMessage();
        }
    }

    public static  void main(String[] args){
        Properties initPro = initProperties(globalPro);
        String test = getPropertyValue(initPro,"Android_AppiumMainJSPath_Win").replaceAll("C:/.*/Appium/", "");
        System.out.println(test);
    }
}
