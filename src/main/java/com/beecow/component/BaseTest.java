package com.beecow.component;

import com.beecow.textLanguage.BeeCow_Language;
import com.beecow.utils.*;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.*;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static com.beecow.model.CommonElement.IOS_APKFile_Element;
import static com.beecow.model.CommonElement.IOS_APKFolder_Element;
import static com.beecow.model.CommonElement.getNotificationPopup;
import static com.beecow.model.MarketCategoriesElement.getTitleFirstLaunch_tv;
import static com.beecow.utils.PropertiesUtils.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by HangPham on 12/18/2016.
 */

public class BaseTest {

    // GENERAL
    public static AppiumDriver driver;
    public String GLOBALPROPERTIESFile = "Global.properties";
    public static Properties GLOBALPROPERTIES;
    AppiumDriverLocalService service;
    private int TIMEOUT200 = 200;
    private int TIMEOUT10 = 10;
    public static boolean isAndroid = Utils.getInstance().isAndroidDevice();

    public static Properties PROJECTPROPERTIES;
    public String Testlink_ProjectName;
    public String Testlink_TestPlanName;
    public String Testlink_BuildName;


    public static final String ROOT_PATH = System.getProperty("user.dir");
    public static final String LOG_PATH_FOLDER = ROOT_PATH + "/log";
    public static final String LOG_FILE_PATH = LOG_PATH_FOLDER + "/androidLog.txt";

    public Helper getHelper() {
        return new Helper(getDriver());
    }

    public SwipeFunctions getSwipe() {
        return new SwipeFunctions(getDriver());
    }

    public static BeeCow_Language beeCow_language;

    public String getLanguageKey() {
        String keyLanguage = "en";
        if (getHelper().isElementPresent(getTitleFirstLaunch_tv())) {
            keyLanguage = getHelper().getLanguageDevice(getHelper().findElement(getTitleFirstLaunch_tv()).getText());
        }
        return keyLanguage;
    }

    @BeforeSuite
    public void GetLastAPKFile() throws Exception {
        PropertiesUtils.getPropertiesGlobal();
        //if (Utils.getInstance().isAndroidDevice()) {
            //System.out.println("Start Get APK File from share folder");
            //PropertiesUtils.GetLastAPKFile();
//            if(driver.isAppInstalled("ca.mediastep.BeeCow")== true) {
//                driver.resetApp();
//            } if (driver.isAppInstalled("ca.mediastep.BeeCow")== false){
//                driver.installApp("");
//        }

            //System.out.println("Done Get APK File from share folder");
      //  }
        //GLOBALPROPERTIES = Utils.initProperties (GLOBALPROPERTIESFile);
        System.out.println("Appium is starting");
        setAppium();
        service.start();
        System.out.println("Appium is started");

    }

    public void setUp(String propertyFile) throws Exception {
        try {
//            //remove app if have
//            if(getDriver().isAppInstalled("ca.mediastep.BeeCow")==true){
//                getDriver().removeApp("ca.mediastep.BeeCow");
//            } else if(Objects.equals(getDriver().isAppInstalled("ca.mediastep.BeeCow"), null)){
                System.out.println("Before Method: Setup");
                initDriver(propertyFile);
                //closeNotificationPopup();

                //get language
                beeCow_language = getHelper().getTextByLanguage(getLanguageKey());
            //}
        } catch (Exception ex) {
            System.out.println("Error Before Method: Setup:" + ex.getMessage());
        }

    }

    private void closeNotificationPopup(){
        if(Utils.getInstance().isIosDevice()){
            //if(!driver.findElements(By.xpath("xpath:://XCUIElementTypeButton[@name='Allow']")).isEmpty()){
            if(getHelper().isElementPresent(getNotificationPopup())){
    getHelper().findElement(getNotificationPopup()).click();
            }
        }
    }

//    @AfterMethod
//    public void teardown() {
//        if (getDriver() != null) {
//            getDriver().closeApp();
//            getDriver().quit();
//        }
//    }

//    @AfterMethod(alwaysRun = true) public void killServer(ITestResult result)
//            throws InterruptedException, IOException {
//        //endLogTestResults(result);
//        getDriver().closeApp();
//        System.out.println(" ===== STEP =====> Start Remove IOS App");
//        getDriver().removeApp(iOS_BundleID);
//        System.out.println(" ===== STEP =====> End Remove IOS App");
//        System.out.println(" ===== STEP =====> Stopping Appium");
//        service.stop();
//        System.out.println(" ===== STEP =====> Appium is stopped");
//        //getDriver().quit();
//        //deleteAppIOS("com.tesco.sample");
//    }

    public AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    @AfterSuite
    public void Stop() throws IOException, InterruptedException, Exception {
        if (getDriver() != null) {
//            if (Utils.getInstance().isAndroidDevice()) {
//                System.out.println(" ===== STEP =====> Start Remove Android App");
//                getDriver().removeApp(androidAppPackage);
//                System.out.println(" ===== STEP =====> End Remove Android App");
//            }else if (Utils.getInstance().isIosDevice()){
//                System.out.println(" ===== STEP =====> Start Remove IOS App");
//                getDriver().removeApp(iOS_BundleID);
//                System.out.println(" ===== STEP =====> End Remove IOS App");
//            }
//            getDriver().quit();
            //System.out.println(" ===== STEP =====> Start Remove IOS App");
            //getDriver().removeApp(iOS_BundleID);
            //System.out.println(" ===== STEP =====> End Remove IOS App");
            System.out.println(" ===== STEP =====> Stopping Appium");
            service.stop();
            System.out.println(" ===== STEP =====> Appium is stopped");
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
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("File log path is created!");
        } else {
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
        try {
            DesiredCapabilities capabilities = getPlatform_capabilities(propertyFile);
            URL url = new URL(serverTest);
            driver = buildDriver(url, capabilities);
            driver.manage().timeouts().implicitlyWait(TIMEOUT10, TimeUnit.SECONDS);
            closeNotificationPopup();
        } catch (Exception ex) {
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
        if (Utils.getInstance().isWebAndroidDevice()) {
            return new AndroidDriver(url, capabilities);
        }
        if (Utils.getInstance().isWebIOSDevice()) {
            return new IOSDriver(url, capabilities);
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
        if (Utils.getInstance().isWebAndroidDevice()) {
            return getWebAndroid_capability();
        }
        if (Utils.getInstance().isWebIOSDevice()) {
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

//    private DesiredCapabilities getiOS_capability(String projectPropertiesFile) {
//        PropertiesUtils.getPropertiesOther(projectPropertiesFile);
//        String iOSAPKFilePath = new File(iOSAPKFile).getAbsolutePath();
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
//                AutomationName.IOS_XCUI_TEST);
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, iOS_DeviceName);
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, iOS_PlatformVersion);
//        capabilities.setCapability(MobileCapabilityType.APP, iOSAPKFilePath);
//        capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, iOS_BundleID);
//        capabilities.setCapability(MobileCapabilityType.UDID, iOS_UDID);
//        //capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
//        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
//        capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, "true");
//        //capabilities.setCapability("waitForAppScript", "if (target.frontMostApp().alert().name()=='\"BeeCow\" Would Like to Send You Notifications') {$.acceptAlert(); true;}");
//        //capabilities.setCapability("autoDismissAlerts", true);
//        return capabilities;
//    }

    private DesiredCapabilities getiOS_capability(String projectPropertiesFile) {
        PROJECTPROPERTIES = Utils.initProperties(projectPropertiesFile);
        Testlink_ProjectName = Utils.getPropertyValue(PROJECTPROPERTIES, "Testlink_ProjectName");
        Testlink_TestPlanName = Utils.getPropertyValue(PROJECTPROPERTIES, "Testlink_TestPlanName");
        Testlink_BuildName = Utils.getPropertyValue(PROJECTPROPERTIES, "Testlink_BuildName");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
                AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Utils.getPropertyValue(PROJECTPROPERTIES,"IOS_DeviceName"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Utils.getPropertyValue(PROJECTPROPERTIES,"IOS_PlatformVersion"));

        capabilities.setCapability(MobileCapabilityType.APP, Utils.getPropertyValue(PROJECTPROPERTIES,"IOS_FILE"));
        capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, Utils.getPropertyValue(PROJECTPROPERTIES,"IOS_BundleID"));
        capabilities.setCapability(MobileCapabilityType.UDID, Utils.getPropertyValue(PROJECTPROPERTIES,"IOS_UDID"));

        //capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
        return capabilities;
    }

    private DesiredCapabilities getWebAndroid_capability() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("Chrome");
        return capabilities;
    }

    private DesiredCapabilities getWebIOS_capability() {
        DesiredCapabilities capabilities = DesiredCapabilities.safari();
        capabilities.setBrowserName("Safari");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "");
        capabilities.setCapability(MobileCapabilityType.UDID, "");
        return capabilities;
    }
}
