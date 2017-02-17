package com.beecow.component;

import com.beecow.utils.PropertiesUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
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

import static com.beecow.utils.PropertiesUtils.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by HangPham on 12/18/2016.
 */

public class BaseTest {

    // GENERAL
    protected static AppiumDriver driver;
    AppiumDriverLocalService service;
    private int TIMEOUT200 = 200;
    private int TIMEOUT10 = 10;

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
        PropertiesUtils.getPropertiesGlobal();
        if(Utils.getInstance().isAndroidDevice()){
            System.out.println("Start Get APK File from share folder");
        PropertiesUtils.GetLastAPKFile();
            System.out.println("Done Get APK File from share folder");
        }
        System.out.println("Appium is starting");
        setAppium();
        service.start();
        System.out.println("Appium is started");
    }

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
            if(Utils.getInstance().isAndroidDevice()) {
                System.out.println("Start Remove App");
                driver.removeApp(androidAppPackage);
                System.out.println("End Remove App");
            }
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
        if (osName.contains(MobilePlatform.WINDOWS)) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File(androidNodeJSPath))
                    .usingPort(Integer.parseInt(appiumPort))
                    .withIPAddress(appiumIPAddress)
                    .withAppiumJS(new File(androidAppiumMainJSPath))
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withLogFile(file)
                    .withStartUpTimeOut(TIMEOUT200, TimeUnit.SECONDS));
        } else if (osName.contains("Mac")) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File(iOSNodeJSPath))
                    .usingPort(Integer.parseInt(appiumPort))
                    .withIPAddress(appiumIPAddress)
                    .withAppiumJS(new File(iOSAppiumMainJSPath))
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withLogFile(file)
                    .withStartUpTimeOut(TIMEOUT200, TimeUnit.SECONDS));

        } else {
            // you can add for other OS, just to track added a fail message
            Assert.fail("Starting appium is not supporting the current OS.");
        }
    }

    private void initDriver(String propertyFile) throws Exception {
        try{
            DesiredCapabilities capabilities = getPlatform_capabilities(propertyFile);
            URL url = new URL(serverTest);
            driver = buildDriver(url, capabilities);
            driver.manage().timeouts().implicitlyWait(TIMEOUT10, TimeUnit.SECONDS);
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
            return getiOS_capability(propertyFile);
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
        PropertiesUtils.getPropertiesOther(projectPropertiesFile);
        String androidAPKFilePath = new File(androidAPKFile).getAbsolutePath();
        DesiredCapabilities capabilities = new DesiredCapabilities(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, null, null);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.APP, androidAPKFilePath);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, androidAppPackage);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, androidAppActivities);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, androidDeviceName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidPlatformVersion);

        capabilities.setCapability(MobileCapabilityType.VERSION, androidVersion);
        capabilities.setCapability(MobileCapabilityType.PLATFORM, androidPlatform);


        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, TIMEOUT200);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        return capabilities;
    }

    private DesiredCapabilities getiOS_capability(String projectPropertiesFile) {
        PropertiesUtils.getPropertiesOther(projectPropertiesFile);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, iOS_DeviceName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, iOS_PlatformVersion);

        capabilities.setCapability(MobileCapabilityType.APP, iOSAPKFolder);
        capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID,iOS_BundleID);
        capabilities.setCapability(MobileCapabilityType.UDID, iOS_UDID);

        capabilities.setCapability(MobileCapabilityType.FULL_RESET,false);
        capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
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
