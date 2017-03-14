package com.beecow.component;

/**
 * Created by HangPham on 12/17/2016.
 */
public class Constant {

//"C:\Program Files (x86)\Appium\node.exe" "C:\Program Files (x86)\Appium\node_modules\appium\lib\server\main.js" --address 127.0.0.1 --port 4723 --avd Nexus4
    public static final MOBILE_PLATFORM TYPE_PLATFORM = MOBILE_PLATFORM.IOS;
    public enum MOBILE_PLATFORM{
        ANDROID,
        IOS,
        CHROME_AND,
        SAFARI_IOS
    }

    // TEST LINK
    public static final String DEVKEY ="bed1de6a318ab11678ee0b96272eefba";
    public static final String URL ="http://192.168.1.124/testlink/lib/api/xmlrpc/v1/xmlrpc.php";

    //Constant
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
}
