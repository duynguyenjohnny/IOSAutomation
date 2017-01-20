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
import static java.nio.file.StandardCopyOption.*;

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

    public static String getPropertyValue(String propertyFile, String propertyName) throws Exception{
        try{
            String value = "";
            Properties pro = new Properties();
            String workingdir = Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\main\\resources\\";

            File f = new File(workingdir + propertyFile);
            if(f.exists() && !f.isDirectory()) {
                FileInputStream Master = new FileInputStream(workingdir + propertyFile);
                pro.load(Master);
                Master.close();
                return pro.getProperty(propertyName);
            }else{
                System.out.println("Get Property Value");
                return null;
            }
        }catch (Exception ex){
            System.out.println("Exception Error while Get Property Value: " + ex.getMessage());
            return null;
        }
    };

    /**
     * @Function: Get Last APK File in a Folder
     * @Description: Get Last APK File in a Folder and return a String with full file path
     * @Author: thaihoang.nguyen
     * @CreateDate: Jan-19-2017
     * @throws Exception
     */
    public static String GetLastAPKFileInFolder(String sPath) throws Exception{
        try{
            String destFilename = Paths.get(".").toAbsolutePath().normalize().toString() + "\\1.apk";
            FileOutputStream fileOutputStream;
            InputStream fileInputStream;
            byte[] buf;
            int len;
            String sNetworkShare_User = utils.getPropertyValue("Global.properties", "NetworkShare_User");
            String sNetworkShare_Pass = utils.getPropertyValue("Global.properties", "NetworkShare_Pass");
            String url = "smb:" + utils.getPropertyValue("Global.properties", "Android_APKFolder");
            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, sNetworkShare_User, sNetworkShare_Pass);
            SmbFile dir = new SmbFile(url, auth);
            //File folder = new File(dir.getPath());

            //check folder exists or not

            if (dir.exists() && dir.isDirectory()) {
                SmbFile[] files = dir.listFiles();
                long lastMod = Long.MIN_VALUE;
                SmbFile choice = null;
                for (SmbFile file : files) {
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
            return "Function GetLastFileInFolder - Error: " + ex.getMessage();
        }
    }
}
