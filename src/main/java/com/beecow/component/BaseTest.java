package com.beecow.component;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

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
//    public String localApp = APP_PATH;
    public String GLOBALPROPERTIESFile = "Global.properties";
    public static Properties GLOBALPROPERTIES;
    AppiumDriverLocalService service;

    // FOR EACH PROJECT
    public static Properties PROJECTPROPERTIES;
    public String Testlink_ProjectName;
    public String Testlink_TestPlanName;
    public String Testlink_BuildName;

    public static final String ROOT_PATH = System.getProperty("user.dir");
    public static final String LOG_PATH_FOLDER = ROOT_PATH + "/log";
    public static final String LOG_FILE_PATH = LOG_PATH_FOLDER + "/androidLog.txt";

    public Helper getHelper(){
        return new Helper(driver);
    }
    public Result getResult(){
        return new Result();
    }

    @BeforeSuite
    public void GetLastAPKFile() throws Exception{
        GLOBALPROPERTIES = Utils.initProperties(GLOBALPROPERTIESFile);
        System.out.println("Start Get APK File from share folder");
        Utils.GetLastAPKFile();
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
    @AfterMethod
    public void teardown() {
        if(driver!=null){
            driver.closeApp();
        }
    }

    @AfterSuite
    public void Stop() throws IOException, InterruptedException, Exception {
        if(driver!=null) {
            System.out.println("Start Remove App");
            driver.removeApp(Utils.getPropertyValue(GLOBALPROPERTIES, "Android_AppPackage"));
            System.out.println("End Remove App");
            System.out.println("Stopping Appium");
            service.stop();
            System.out.println("Appium is stopped");
        }
    }

    public void setAppium() {
        File classPathRoot = new File(ROOT_PATH);
        String osName = System.getProperty("os.name");

        File dir = new File(LOG_PATH_FOLDER);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(LOG_FILE_PATH);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("File log path is created!");
        }else{
            System.out.println("File log path already exists.");
        }
        if (osName.contains("Windows")) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File(GLOBALPROPERTIES.getProperty("Android_NodeJSPath_win")))
                    .usingPort(Integer.parseInt(GLOBALPROPERTIES.getProperty("Appium_Port")))
                    .withIPAddress(GLOBALPROPERTIES.getProperty("Appium_IPAddress"))
                    .withAppiumJS(new File(GLOBALPROPERTIES.getProperty("Android_AppiumMainJSPath_Win")))
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withLogFile(file)
                    .withStartUpTimeOut(50, TimeUnit.SECONDS));
        } else if (osName.contains("Mac")) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File("/Applications/Appium.app/Contents/Resources/node/bin/node"))
                    .usingPort(Integer.parseInt("4723"))
                    .withIPAddress("127.0.0.1")
                    .withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/node_modules/appium/build/lib/main.js"))
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withLogFile(new File(new File(classPathRoot, File.separator + "log"), "androidLog.txt"))
                    .withStartUpTimeOut(50, TimeUnit.SECONDS));

        } else {
            // you can add for other OS, just to track added a fail message
            Assert.fail("Starting appium is not supporting the current OS.");
        }
    }

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
        String Android_APKFile = Paths.get(".").toAbsolutePath().normalize().toString() + File.separator + Utils.getPropertyValue(GLOBALPROPERTIES, "Android_APKFile");
        String androidAPKFile = new File(Utils.getPropertyValue(GLOBALPROPERTIES, "Android_APKFile")).getAbsolutePath();
        DesiredCapabilities capabilities = new DesiredCapabilities("appWaitActivity", null, null);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.APP, androidAPKFile);
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
