package com.beecow.component;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import com.beecow.utils.CommandPrompt;
import com.beecow.utils.Helper;
import com.beecow.utils.Result;
import com.beecow.utils.Utils;

import static com.beecow.component.Constant.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by HangPham on 12/18/2016.
 */
public class BaseTest {
    // GENERAL
    protected static AppiumDriver driver;
    private CommandPrompt cp;
//    public String localApp = APP_PATH;
    public String globalPropertiesFile = "Global.properties";
    public static Properties GLOBALPROPERTIES;
    AppiumDriverLocalService service;

    public String Appium_IPAddress;
    public String Appium_Port;
    public String Android_NodeJSPath;
    public String Android_AppiumMainJSPath;
    public String Android_LogPath;

    // FOR EACH PROJECT
    public static Properties PROJECTPROPERTIES;
    public String Testlink_ProjectName;
    public String Testlink_TestPlanName;
    public String Testlink_BuildName;

    public BaseTest() {
        GLOBALPROPERTIES = Utils.initProperties(globalPropertiesFile);
    }

    public Helper getHelper(){
        return new Helper(driver);
    }
    public Result getResult(){
        return new Result();
    }

    @BeforeSuite
    public void GetLastAPKFile() throws Exception{
        System.out.println("Start Get APK File from share folder");
//        Utils.GetLastAPKFile();
        System.out.println("Done Get APK File from share folder");
        System.out.println("Appium is starting");
        setAppium();
        service.start();
        System.out.println("Appium is started");

    }

//    @Parameters({ "config_file"})
//    @BeforeMethod
    public void setUp(String propertyFile) throws Exception {
        try{
            System.out.println("Before Method: Setup");
            initDriver(propertyFile);
        }catch (Exception ex){
            System.out.println("Error Before Method: Setup:" + ex.getMessage());
        }

    }

    @AfterSuite
    public void Stop() throws IOException, InterruptedException, Exception {
        System.out.println("Start Remove App");
        driver.removeApp(Utils.getPropertyValue(GLOBALPROPERTIES,"Android_AppPackage"));
        System.out.println("End Remove App");
        System.out.println("Stopping Appium");
        service.stop();
        System.out.println("Appium is stopped");
    }

    public void setAppium() {
        System.out.println("Start APPIUM");
        Appium_IPAddress = Utils.getPropertyValue(GLOBALPROPERTIES, "Appium_IPAddress");
        Appium_Port = Utils.getPropertyValue(GLOBALPROPERTIES, "Appium_Port");
        Android_NodeJSPath = Utils.getPropertyValue(GLOBALPROPERTIES, "Android_NodeJSPath");
        Android_AppiumMainJSPath = Utils.getPropertyValue(GLOBALPROPERTIES, "Android_AppiumMainJSPath");
        Android_LogPath = Utils.getPropertyValue(GLOBALPROPERTIES, "Android_LogPath");
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File(Android_NodeJSPath))
                .usingPort(Integer.parseInt(Appium_Port))
                .withIPAddress(Appium_IPAddress)
                .withAppiumJS(new File(Android_AppiumMainJSPath))
                .withLogFile(new File(Android_LogPath))
                .withStartUpTimeOut(50, TimeUnit.SECONDS));
    }



//      @BeforeSuite
//    public void runCommandToLaunchAppium() throws IOException, InterruptedException {
//        cp=new CommandPrompt();
//        if(Utils.getInstance().isAndroidDevice()||Utils.getInstance().isWebAndroidDevice()) {
//            cp.runCommand("appium --address 127.0.0.1 --port 4723 --app C:\\jenkins\\workspace\\BeeCow-QAAndroid\\BeeCow.apk --session-override --log C:\\jenkins\\workspace\\BeeCow-QAAndroid\\appium.log -bp 4724");
//            //cp.runCommand("\"C:\\Program Files (x86)\\Appium\\node.exe\" \"C:\\Program Files (x86)\\Appium\\node_modules\\appium\\lib\\server\\main.js\" --address 127.0.0.1 --port 4723 --app C:\\jenkins\\workspace\\android-beecow\\app\\build\\outputs\\apk\\BeeCow_1.0_RELEASE_12Jan2017_Build_1.apk --pre-launch --platform-name Android --platform-version 19 --automation-name Appium --log-no-color");
//        }
//        if(Utils.getInstance().isIosDevice()){
//            cp.runCommand("");
//        }
//        if(Utils.getInstance().isWebIOSDevice()){
//            //open new terminal
//            Runtime.getRuntime().exec("/usr/bin/open -a Terminal /path/to/the/executable");
//        }
//        System.out.println("Appium is started");
//        //Thread.sleep(20000);
//    }
//
//
//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//    }

    private void initDriver(String propertyFile) throws Exception {
        try{
            DesiredCapabilities capabilities = getPlatform_capabilities(propertyFile);
            URL url = new URL(Utils.getPropertyValue(GLOBALPROPERTIES,"Server_Test"));
            driver = buildDriver(url, capabilities);
            driver.manage().timeouts().implicitlyWait(TIME_OUT, TimeUnit.SECONDS);
        }catch (Exception ex){
            System.out.println("[Error] : " + ex.getMessage());
        }

    }

    private AppiumDriver buildDriver(URL url, DesiredCapabilities capabilities) throws Exception {
        if (Utils.getInstance().isAndroidDevice()) {
            return new AndroidDriver(url, capabilities);
        }
        if (Utils.getInstance().isIosDevice()) {
            return new IOSDriver(url, capabilities);
        }
        if (Utils.getInstance().isWebAndroidDevice()){
            return new AndroidDriver(url,capabilities);
        }
        if (Utils.getInstance().isWebIOSDevice()){
            return new IOSDriver(url,capabilities);
        }
        throw new Exception("the driver does not find");
    }

    private DesiredCapabilities getPlatform_capabilities(String propertyFile) throws Exception {
        if (Utils.getInstance().isAndroidDevice()) {
            return getAndroid_capability(propertyFile);
        }
        if (Utils.getInstance().isIosDevice()) {
            return getiOS_capability();
        }
        if (Utils.getInstance().isWebAndroidDevice()){
            return getWebAndroid_capability();
        }
        if (Utils.getInstance().isWebIOSDevice()){
            return getWebIOS_capability();
        }
        throw new Exception("does not find the platform correspon");
    }

    private DesiredCapabilities getAndroid_capability(String projectPropertiesFile) throws Exception {
        PROJECTPROPERTIES = Utils.initProperties(projectPropertiesFile);
        Testlink_ProjectName = Utils.getPropertyValue(PROJECTPROPERTIES, "Testlink_ProjectName");
        Testlink_TestPlanName = Utils.getPropertyValue(PROJECTPROPERTIES, "Testlink_TestPlanName");
        Testlink_BuildName = Utils.getPropertyValue(PROJECTPROPERTIES, "Testlink_BuildName");
        String Android_APKFile = Paths.get(".").toAbsolutePath().normalize().toString() + "\\" + Utils.getPropertyValue(GLOBALPROPERTIES, "Android_APKFile");
        DesiredCapabilities capabilities = new DesiredCapabilities("appWaitActivity", null, null);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.APP, Android_APKFile);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, Utils.getPropertyValue(GLOBALPROPERTIES, "Android_AppPackage"));
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, Utils.getPropertyValue(GLOBALPROPERTIES, "Android_AppActivity"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Utils.getPropertyValue(PROJECTPROPERTIES, "Android_DeviceName"));//ASUS_T00N
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Utils.getPropertyValue(PROJECTPROPERTIES,"Android_PlatformVersion"));

        capabilities.setCapability(MobileCapabilityType.VERSION, Utils.getPropertyValue(PROJECTPROPERTIES,"Android_Version"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM, Utils.getPropertyValue(PROJECTPROPERTIES,"Android_Platform"));


        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("noReset", true);
        return capabilities;
    }

    private DesiredCapabilities getiOS_capability() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //real device
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iphone");
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0.2");
        //simulator
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6s");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.2");

        capabilities.setCapability(MobileCapabilityType.APP, "apppath");
        capabilities.setCapability("bundleId","");
        capabilities.setCapability(MobileCapabilityType.UDID, "");

        capabilities.setCapability("noReset",true);
        capabilities.setCapability("fullReset",false);
        return capabilities;
    }
    private DesiredCapabilities getWebAndroid_capability(){
        DesiredCapabilities capabilities=DesiredCapabilities.chrome();
        capabilities.setBrowserName("Chrome");
        return capabilities;
    }
    private DesiredCapabilities getWebIOS_capability(){
        DesiredCapabilities capabilities=DesiredCapabilities.safari();
        capabilities.setBrowserName("Safari");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"");
        capabilities.setCapability(MobileCapabilityType.UDID,"");
        return capabilities;
    }
}
