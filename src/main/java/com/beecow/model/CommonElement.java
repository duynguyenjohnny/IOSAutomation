package com.beecow.model;

import com.beecow.utils.Utils;
import testlink.api.java.client.TestLinkAPIResults;

/**
 * Created by HangPham on 12/18/2016.
 */
public class CommonElement {
    public static String passed = TestLinkAPIResults.TEST_PASSED;
    public static String failed = TestLinkAPIResults.TEST_FAILED;

    public static String getNotificationPopup(){
        if(Utils.getInstance().isAndroidDevice()){
            return "";
        } return "xpath:://XCUIElementTypeButton[@name='Allow']";
    }

    public static String GLOBALPROPERTIESFile = "Global.properties";
    public static String marketPropertiesFile = "Market.properties";

    public static String cupidPropertiesFile = "Cupid.properties";
    public static String socialNetworkPropertiesFile = "SN.properties";


    public static String Server_Test_Element = "Server_Test";
    public static String Android_APKFile_Element = "Android_APKFile";
    public static String Android_AppPackage_Element = "Android_AppPackage";
    public static String Android_AppActivity_Element = "Android_AppActivity";
    public static String Android_NodeJSPath_Element = "Android_NodeJSPath";
    public static String Android_AppiumMainJSPath_Element = "Android_AppiumMainJSPath";
    public static String NetworkShare_User_Element = "NetworkShare_User";
    public static String NetworkShare_Pass_Element = "NetworkShare_Pass";
    public static String Appium_IPAddress_Element = "Appium_IPAddress";
    public static String Appium_Port_Element = "Appium_Port";
    public static String Android_APKFolder_Element = "Android_APKFolder";
    public static String IOS_APKFile_Element = "IOS_APKFile";

    public static String IOS_NodeJSPath_Element = "IOS_NodeJSPath";
    public static String IOS_AppiumMainJSPath_Element = "IOS_AppiumMainJSPath";

    public static String IOS_APKFolder_Element = "IOS_APKFolder";

    public static String IOS_DeviceName_Element = "IOS_DeviceName";
    public static String IOS_PlatformVersion_Element = "IOS_PlatformVersion";
    public static String IOS_BundleID_Element = "IOS_BundleID";
    public static String IOS_UDID_Element = "IOS_UDID";

    //element for each properties
    public static String testlink_ProjectName_Element = "Testlink_ProjectName";
    public static String testlink_DevKey_Element = "TestLink_DevKey";
    public static String testlink_TestPlanName_Element = "Testlink_TestPlanName";
    public static String testlink_BuildName_Element = "Testlink_BuildName";

    public static String testlink_ProjectName_Element_IOS = "Testlink_ProjectName_IOS";
    public static String testlink_DevKey_Element_Hang = "TestLink_DevKey_Hang";

    public static String Android_LogPath_Element = "Android_LogPath";
    public static String Android_DeviceName_Element = "Android_DeviceName";
    public static String Android_PlatformVersion_Element = "Android_PlatformVersion";
    public static String Android_Version_Element = "Android_Version";
    public static String Android_Platform_Element = "Android_Platform";


    public static String screenShot_login = "src\\report\\screenshot\\market\\";


}
