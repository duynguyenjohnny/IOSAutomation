package com.beecow.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Created by HangPham on 12/17/2016.
 */
public class SetupTest implements Runnable{
    public CommandPrompt cp=new CommandPrompt();
    public AppiumManager appiumManager=new AppiumManager();
    public AppiumDriver driver;
    public int devicesCount;

    protected String deviceId;
    protected String deviceName;
    protected String osVersion;
    protected String port;
    protected Thread t;

    static Map<String, String> devices = new HashMap<String, String>();
    static DeviceConfiguration deviceConf = new DeviceConfiguration();

    public SetupTest() {
        try {
            devices = deviceConf.getDevices();
            devicesCount = devices.size() / 3;
            System.out.println("devices: " + devices);
            System.out.println("devices count: " + devicesCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //tao thang nay de truyen 1 mang devices vao
    public SetupTest(int i){
        int deviceNumber = (i+1);
        this.deviceId = devices.get("deviceID"+deviceNumber);
        this.deviceName = devices.get("deviceName"+deviceNumber);
        this.osVersion = devices.get("osVersion"+deviceNumber);
    }
//    @BeforeMethod
    public void setUp() throws Exception {
//        cp.runCommand("\"C:\\Program Files (x86)\\Appium\\node.exe\" \"C:\\Program Files (x86)\\Appium\\node_modules\\appium\\lib\\server\\main.js\" --address 127.0.0.1 --port 4723 --app E:\\Company\\automationTest\\project_Appium\\BeeCowProjectMobile\\FoodyVN.apk --pre-launch --platform-name Android --platform-version 19 --automation-name Appium --log-no-color");
//        Thread.sleep(5000);
        initDriver();
        Thread.sleep(5000);
    }
//    @AfterMethod
    public void tearDown(){
        driver.quit();
        System.out.println("teaDown: quit driver");
        try {
            deviceConf.stopADB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void start(){
        if (t == null){
            t = new Thread(this);
            t.start ();
        }
    }
//    @Override
    public void run() {
        if (t == null){
            t = new Thread(this);
            t.start ();
        }
    }

//    @Test
    public void testMultiDevices(){
        int totalDevices=devicesCount;
        System.out.println("total devices: "+totalDevices);
        SetupTest[] threads=new SetupTest[totalDevices];
        for (int i=0;i<totalDevices;i++){
            threads[i]=new SetupTest(i);

        }
        // Start running execution on each device
        for(int i=0;i<totalDevices;i++)
            threads[i].start();
        test();
    }
//    @Test
    public void test() {
        System.out.println("just test");
        driver.findElement(By.id("com.foody.vn.activity.demo:id/vTwoOverlay")).click();
    }

    public void initDriver() throws Exception {
        port = appiumManager.startAppium(); 			// Start appium server
        DesiredCapabilities capabilities = getCapabilities();
        URL url = new URL("http://127.0.0.1:"+port+"/wd/hub");
        driver = setBuildDriver(url, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public DesiredCapabilities getCapabilities() throws Exception {
        if (Utils.getInstance().isAndroidDevice()) {
            return getAndroid_Capability();
        }
        if (Utils.getInstance().isIosDevice()) {
            return getiOS_Capability();
        }
        throw new Exception("does not find mobile platform");

    }

    public AppiumDriver setBuildDriver(URL url, DesiredCapabilities capabilities) throws Exception {
        if (Utils.getInstance().isAndroidDevice()) {
            return new AndroidDriver(url, capabilities);
        }
        if (Utils.getInstance().isIosDevice()) {
            return new IOSDriver(url, capabilities);
        }
        throw new Exception("mobile platform does not support");
    }

    public DesiredCapabilities getAndroid_Capability() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, osVersion);
        capabilities.setCapability(MobileCapabilityType.UDID,deviceId);
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,     "PLATFORM_NAME");
//        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "APP_PACKAGE_LIVE");
        capabilities.setCapability(MobileCapabilityType.APP,"E:\\Company\\automationTest\\project_Appium\\BeeCowProjectMobile\\FoodyVN.apk");
        return capabilities;
    }

    public DesiredCapabilities getiOS_Capability() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Constant.PLATFORM_NAME_IOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "Constant.PLATFORM_VERSION_IOS"); //Replace this with your iOS version
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Constant.DEVICE_NAME_IOS"); //Replace this with your simulator/device version
        capabilities.setCapability(MobileCapabilityType.UDID, "Constant.UDID");
        capabilities.setCapability(MobileCapabilityType.APP, "Constant.APP_PACKAGE_LIVE_IOS");
        return capabilities;
    }


    public void testtest(){

    }

//    public static void main(String[] args) throws Exception {
//        SetupTest setupTest = new SetupTest();
//        setupTest.testtest();
//    }


}
