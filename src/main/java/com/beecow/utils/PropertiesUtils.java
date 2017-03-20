package com.beecow.utils;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static com.beecow.model.CommonElement.*;
/**
 * Created by hangpham on 2017-02-08.
 */
public class PropertiesUtils {
    public static Properties globalProperties;
    public static Properties otherProperties;
    public static String serverTest;
    public static String androidAPKFile;
    public static String androidAppPackage;
    public static String androidAppActivities;
    public static String androidNodeJSPath;
    public static String androidAppiumMainJSPath;
    public static String networkShare_User;
    public static String networkShare_Pass;
    public static String appiumIPAddress;
    public static String appiumPort;
    public static String androidAPKFolder;
    public static String iOSAPKFile;
    public static String iOSNodeJSPath;
    public static String iOSAppiumMainJSPath;
    public static String iOSAPKFolder;
    public static String iOS_DeviceName;
    public static String iOS_PlatformVersion;
    public static String iOS_BundleID;
    public static String iOS_UDID;
    public static String testlinkProjectName;
    public static String testlinkTestPlanName;
    public static String testlinkBuildName;
    public static String testlinkDevKey;
    public static String androidLogPath;
    public static String androidDeviceName;
    public static String androidPlatformVersion;
    public static String androidVersion;
    public static String androidPlatform;
    public static Properties initProperties(String propertyFile) {
        try {
            Properties pro = new Properties();
            InputStream resourceAsStream = Utils.class.getClassLoader()
                    .getResourceAsStream(propertyFile);
            pro.load(resourceAsStream);
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
    /**
     * This function will copy .apk file from share server (config in Global.properties) to local
     *
     * @return
     * @throws Exception
     */
    public static String GetLastAPKFile() throws Exception {
        try {
            String destFilename="";
            Properties initPro = initProperties(GLOBALPROPERTIESFile);
            if(Utils.getInstance().isAndroidDevice()) {
                destFilename = getPropertyValue(initPro, Android_APKFile_Element);
            }else if(Utils.getInstance().isIosDevice()){
                destFilename = getPropertyValue(initPro, IOS_APKFile_Element);
            }
            FileOutputStream fileOutputStream;
            InputStream fileInputStream;
            byte[] buf;
            int len;
            String sNetworkShare_User = getPropertyValue(initPro, NetworkShare_User_Element);
            String sNetworkShare_Pass = getPropertyValue(initPro, NetworkShare_Pass_Element);
            String url = "";
            if(Utils.getInstance().isAndroidDevice()) {
                url = "smb:" + getPropertyValue(initPro, Android_APKFolder_Element);
            }else if(Utils.getInstance().isIosDevice()){
                url = "smb:" + getPropertyValue(initPro, IOS_APKFolder_Element);
            }
            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, sNetworkShare_User, sNetworkShare_Pass);
            SmbFile dir = new SmbFile(url, auth);
            //File folder = new File(dir.getPath());
            //check folder exists or not
            if (dir.exists() && dir.isDirectory()) {
                SmbFile[] files = dir.listFiles();
                long lastMod = Long.MIN_VALUE;
                SmbFile choice = null;
                for (SmbFile file : files) {
                    if (file.getName().toLowerCase().contains("release") || file.getName().toLowerCase().contains("develop")) {
                        continue;
                    }
                    if (file.lastModified() > lastMod) {
                        choice = file;
                        lastMod = file.lastModified();
                    }
                }
                if (choice != null) {
                    System.out.println(" ===== STEP =====> Last APK file is [" + choice.getName() + "]");
                    fileOutputStream = new FileOutputStream(destFilename);
                    fileInputStream = choice.getInputStream();
                    buf = new byte[16 * 1024 * 1024];
                    while ((len = fileInputStream.read(buf)) > 0) {
                        fileOutputStream.write(buf, 0, len);
                    }
                    fileInputStream.close();
                    fileOutputStream.close();
                    return destFilename;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception ex) {
            return "Function GetLastAPKFile - Error: " + ex.getMessage();
        }
    }
    public static void getPropertiesGlobal() {
        globalProperties = initProperties(GLOBALPROPERTIESFile);
        serverTest = getPropertyValue(globalProperties, Server_Test_Element);
        androidAPKFile = getPropertyValue(globalProperties, Android_APKFile_Element);
        androidAppPackage = getPropertyValue(globalProperties, Android_AppPackage_Element);
        androidAppActivities = getPropertyValue(globalProperties, Android_AppActivity_Element);
        androidNodeJSPath = getPropertyValue(globalProperties, Android_NodeJSPath_Element);
        androidAppiumMainJSPath = getPropertyValue(globalProperties, Android_AppiumMainJSPath_Element);
        networkShare_User = getPropertyValue(globalProperties, NetworkShare_User_Element);
        networkShare_Pass = getPropertyValue(globalProperties, NetworkShare_Pass_Element);
        appiumIPAddress = getPropertyValue(globalProperties, Appium_IPAddress_Element);
        appiumPort = getPropertyValue(globalProperties, Appium_Port_Element);
        androidAPKFolder = getPropertyValue(globalProperties, Android_APKFolder_Element);
        iOSAPKFile = getPropertyValue(globalProperties, IOS_APKFile_Element);
        iOSNodeJSPath = getPropertyValue(globalProperties, IOS_NodeJSPath_Element);
        iOSAppiumMainJSPath = getPropertyValue(globalProperties, IOS_AppiumMainJSPath_Element);
        iOSAPKFolder = getPropertyValue(globalProperties, IOS_APKFolder_Element);
    }
    public static void getPropertiesOther(String propertiesFile) {
        otherProperties = initProperties(propertiesFile);
        if(Utils.getInstance().isAndroidDevice()) {
            testlinkProjectName = getPropertyValue(otherProperties, testlink_ProjectName_Element);
        } else if (Utils.getInstance().isIosDevice())
        {
            testlinkProjectName = getPropertyValue(otherProperties, testlink_ProjectName_Element_IOS);
        }
        testlinkDevKey = getPropertyValue(otherProperties, testlink_DevKey_Element);
        testlinkTestPlanName = getPropertyValue(otherProperties, testlink_TestPlanName_Element);
        testlinkBuildName = getPropertyValue(otherProperties, testlink_BuildName_Element);
        androidNodeJSPath = getPropertyValue(otherProperties, Android_NodeJSPath_Element);
        androidAppiumMainJSPath = getPropertyValue(otherProperties, Android_AppiumMainJSPath_Element);
        androidLogPath = getPropertyValue(otherProperties, Android_LogPath_Element);
        androidDeviceName = getPropertyValue(otherProperties, Android_DeviceName_Element);
        androidPlatformVersion = getPropertyValue(otherProperties, Android_PlatformVersion_Element);
        androidVersion = getPropertyValue(otherProperties, Android_Version_Element);
        androidPlatform = getPropertyValue(otherProperties, Android_Platform_Element);
        iOS_DeviceName = getPropertyValue(otherProperties, IOS_DeviceName_Element);
        iOS_PlatformVersion = getPropertyValue(otherProperties, IOS_PlatformVersion_Element);
        iOS_BundleID = getPropertyValue(otherProperties, IOS_BundleID_Element);
        iOS_UDID = getPropertyValue(otherProperties, IOS_UDID_Element);
    }
}