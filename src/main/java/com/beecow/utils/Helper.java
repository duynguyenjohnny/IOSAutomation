package com.beecow.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSDriver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.NoSuchElementException;

import static com.beecow.component.Constant.APP_PACKAGE_LIVE;
import static com.beecow.component.Constant.DEVKEY;
import static com.beecow.component.Constant.URL;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * Created by HangPham on 12/18/2016.
 */
public class Helper {
    private AppiumDriver driver;
    private Dimension size;

    //Enter your project API key here.
    public static String devKey=DEVKEY;

    //Enter your Test Link URL here
//    public static String URL= "http://192.168.1.83:9091/testlink/index.php";//
    public static String url= URL;//testlink/lib/api/xmlrpc/v1/xmlrpc.php


    public Helper(AppiumDriver driver) {
        this.driver = driver;
    }

    public By byLocator(String selectorTypeStr, String value) {
        By e = null;
        UISelectorType selector = UISelectorType.fromString(selectorTypeStr);
        if (driver instanceof AndroidDriver) {
            String appPackage = APP_PACKAGE_LIVE;
//            String androidPackage = ANDROID_APP_PACKAGE;
            switch (selector) {
//                case ANDROID_PACKAGE:
//                    e = By.id(androidPackage + ":id/" + value);
//                    break;
                case RESOURCE_ID:
                    e = By.id(appPackage + ":id/" + value);
                    break;
                case TEXT_CONTAINS:
                    e = By.xpath("//*[contains(@text, '" + value + "']");
                    break;
                case TEXT:
                    e = By.xpath("//*[@text = '" + value + "']");
                    break;
                case TEXT_START_WITH:
                    e = By.xpath("//*[starts-with(@text, '" + value + "']");
                    break;
                case CLASS_NAME:
                    e = By.className(value);
                    break;
                case XPATH:
                    e = By.xpath(value);
                    break;
            }
//            return e;
        } else if (driver instanceof IOSDriver) {
            switch (selector) {
                case XPATH:
                    e = By.xpath(value);
                    break;
                case CLASS_NAME:
                    e = By.className(value);
                    break;
                case VALUE:
                    e = By.xpath("//*[@value = '" + value + "']");
                    break;
            }
//            return e;
        }
        return e;
    }

    public By byLocator(String locator){
        By e = byLocator(split(locator)[0], split(locator)[1]);
        return e;
    }
    private String[] split(String str){
        String[] parts = str.split("::");
        return parts;
    }
    public WebElement findElement(String locator){
        WebElement e = driver.findElement(byLocator(locator));
        return e;
    }

    private List<WebElement> findElements(String locator){
        List<WebElement> e = driver.findElements(byLocator(locator));
        return e;
    }
    public void backButton(){

        driver.navigate().back();
    }
    public void refreshPage(){
        driver.navigate().refresh();
    }
    public Boolean isElementPresent(String locator) {
        Boolean isPresent = Boolean.FALSE;
        try {
            isPresent = findElements(locator).size() > 0;
            return isPresent;
        } catch (NoSuchElementException ex) {
            return isPresent;
        }
    }

    public Boolean isElementPresent(By locator) {
        Boolean isPresent = Boolean.FALSE;
        try {
            isPresent = driver.findElements(locator).size() > 0;
            return isPresent;
        } catch (NoSuchElementException ex) {
            return isPresent;
        }
    }
    public Boolean isElementPresent(WebElement locator) {
        Boolean isPresent = Boolean.FALSE;
        try {
            isPresent = locator.isDisplayed();
            return isPresent;
        } catch (NoSuchElementException ex) {
            return isPresent;
        }
    }

    /**
     *
     * @param Project Project Name: Cupid, Social Network, Market, ...
     * @param ClassNames Current Class Name, contains test cases
     * @param Result Passed or Failed
     * @param TCsID Current Test Case ID
     * @return A screenshot locate in path with given param above
     */
    public File takeScreenshot(String Project, String ClassNames, String Result, String TCsID) {
        String sProjectPath = Paths.get(".").toAbsolutePath().normalize().toString() + "/src/report/" + Project + File.separator;
        DateFormat dateFormat = new SimpleDateFormat("_yyyy_MM_dd_HH_mm_ss");
        //get current date time with Date()
        Date date = new Date();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileScrShot = "";
        if(Utils.getInstance().isAndroidDevice()) {
            fileScrShot = sProjectPath.concat("Android") + File.separator + ClassNames + File.separator + TCsID + File.separator + Result + "_" + dateFormat.format(date).toString() + ".png";
        }else if(Utils.getInstance().isIosDevice()){
            fileScrShot = sProjectPath.concat("IOS") + File.separator + ClassNames + File.separator + TCsID + File.separator + Result + "_" + dateFormat.format(date).toString() + ".png";
        }
        try {
            FileUtils.copyFile(scrFile, new File(fileScrShot));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println(e);
        }
        addLog("Captured a screenshot to: " + fileScrShot);
        return scrFile;

    }


    // Return a random number in a range
    public int getRandomIndexWithRange(int Min, int Max){
        int random = 0;
        try{
            random = Min + new Random().nextInt(((Max - Min) + 1));
        }catch (Exception e){
            e.printStackTrace();
        }
        return random;
    }

    // Return a random number in size
    public int getRandomIndex(int size){
        int random = 0;
        try{
            random = new Random().nextInt(size);
        }catch (Exception e){
            e.printStackTrace();
        }
        return random;
    }

    public static void addLog(String text){
        Reporter.log(text+ "</br>", true);
    }

    /**
     * swipe right, left, up, down
     * @param dMiddleY
     * @param dRightX
     * @param dLeftX
     * @throws InterruptedException
     */

    public void swipingHorizontal(double dLeftX, double dRightX, double dMiddleY){
        //Get the size of screen.
        size = driver.manage().window().getSize();
//        System.out.println(size);
        //        Find swipe start and end point from screen's with and height.
//        Find startx point which is at right side of screen.
        int leftX = (int)(size.width * dLeftX);
        //Find endx point which is at left side of screen.
        int rightX = (int)(size.width * dRightX);
        // Find vertical point where you wants to swipe. It is in middle of screen height.
        int centerY = (int)(size.height * dMiddleY);
        System.out.println("Swipe horizontal with coordinates is: \nleftX = " + leftX + " , rightX = " + rightX + " , centerY = " + centerY);

        driver.swipe(leftX, centerY, rightX, centerY, 3000);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void swipingVertical(double dMiddleX, double dUpperY, double dLowerY){
        //Get the size of screen.
        size = driver.manage().window().getSize();
        System.out.println(size);

        int middleX = (int) (size.width * dMiddleX);
        //Find endY point which is at left side of screen.
        int bottomY = (int) (size.height * dLowerY);
        // Find vertical point where you wants to swipe. It is in middle of screen height.
        int topY = (int) (size.height * dUpperY);
        System.out.println("Swipe vertical with coordinates is: \nmiddleX = " + middleX + " , topY = " + topY + " , bottomY = " + bottomY);

        driver.swipe(middleX, topY, middleX, bottomY, 3000);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Swipe left, right
     * @throws InterruptedException
     */

    public void swipeRightToLeft() throws InterruptedException {
        //Get the size of screen.
        size = driver.manage().window().getSize();
        System.out.println(size);
        //        Find swipe start and end point from screen's with and height.
//        Find startx point which is at right side of screen.
        int leftX = (int) (size.width * 0.1);
        //Find endx point which is at left side of screen.
        int rightX = (int) (size.width * 0.9);
        // Find vertical point where you wants to swipe. It is in middle of screen height.
        int middleY = (int) (size.height * 0.66);
        driver.swipe(rightX, middleY, leftX, middleY, 3000);
        Thread.sleep(2000);
    }
    public void swipeLeftToRight() throws InterruptedException {
        size = driver.manage().window().getSize();
        System.out.println(size);
        int leftX = (int) (size.width * 0.1);
        int rightX = (int) (size.width * 0.9);
        int middleY = (int) (size.height * 0.66);
        System.out.println("Swipe left to right with coordinates is: \nleftX = " + leftX + " ,rightX = " + rightX + " , middleY = " + middleY);
        driver.swipe(leftX, middleY, rightX, middleY, 3000);
        Thread.sleep(2000);
    }
    public void swipeBottomToTop() throws InterruptedException {
        Dimension size = driver.manage().window().getSize();
//        System.out.println(size);
        int middleX = (int) (size.width * 0.5);
        int topY = (int) (size.height * 0.17);
        int bottomY = (int) (size.height * 0.85);
//        Swipe from Bottom to Top.
        driver.swipe(middleX, bottomY, middleX, topY, 5000);
        Thread.sleep(2000);
    }
    public void swipeTopToBottom() throws InterruptedException {
        Dimension size = driver.manage().window().getSize();
//        System.out.println(size);
        int middleX = (int) (size.width * 0.5);
        int topY = (int) (size.height * 0.125);
        int bottomY = (int) (size.height * 0.75);
//        Swipe from Bottom to Top.
        driver.swipe(middleX, topY, middleX, bottomY, 3000);
        Thread.sleep(2000);
    }

    public void swipeLeftToRightElement(WebElement el){
        // get the X coordinate of the upper left corner of the element, then add the element's width to get the rightmost X value of the element
        int leftX = el.getLocation().getX()+1;
        int rightX = leftX + el.getSize().getWidth();

        // get the Y coordinate of the upper left corner of the element, then subtract the height to get the lowest Y value of the element
        int upperY = el.getLocation().getY();
        int middleY = upperY + (el.getSize().getHeight()) / 2;
        driver.swipe(leftX, middleY, rightX, middleY, 3000);
    }
    public void swipeRightToLeftElement(WebElement el){
        // get the X coordinate of the upper left corner of the element, then add the element's width to get the rightmost X value of the element
        int leftX = el.getLocation().getX()+1;
        int rightX = leftX + el.getSize().getWidth();

        // get the Y coordinate of the upper left corner of the element, then subtract the height to get the lowest Y value of the element
        int upperY = el.getLocation().getY();
        int middleY = upperY + (el.getSize().getHeight()) / 2;
        driver.swipe(rightX, middleY, leftX, middleY, 3000);
    }
    public void swipeBottomToTopElement(WebElement el){
        size = driver.manage().window().getSize();
        System.out.println("size: "+size);
        // get the X coordinate of the upper left corner of the element, then add the element's width to get the rightmost X value of the element
        int upperY = el.getLocation().getY();
        int lowerY = upperY + el.getSize().getHeight();

        // get the Y coordinate of the upper left corner of the element, then subtract the height to get the lowest Y value of the element
//        int middleX = (el.getSize().getWidth()) / 2;
        int middleX = (int) (size.width * 0.5);
        driver.swipe(middleX, lowerY, middleX, upperY, 3000);
    }
    public void swipeTopToBottomElement(WebElement el){
        Dimension size = driver.manage().window().getSize();
        System.out.println("size: "+size);
        // get the X coordinate of the upper left corner of the element, then add the element's width to get the rightmost X value of the element
        int upperY = el.getLocation().getY();
        int lowerY = upperY + el.getSize().getHeight()/3;

        // get the Y coordinate of the upper left corner of the element, then subtract the height to get the lowest Y value of the element
        int middleX = (el.getSize().getWidth()) / 2;
        driver.swipe(middleX, upperY, middleX, lowerY, 3000);
    }

    public Boolean isElementEnabled(String locator) {
        WebElement element = findElement(locator);
        return element.isEnabled();
    }

    public Boolean waitElementByID(String locationID) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locationID)));
        return element.isEnabled();
    }

    public void waitElementIsDisplayed(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Hide android keyBoard
     */
    public void hideKeyBoard() {

        driver.hideKeyboard();
    }

    /**
     * click back button from device
     */
    public void backButtonDevice(){
        addLog("Click back button on device");
        ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);
    }

    /**
     * compare all list with a text
     * @param locator
     * @param textCompare
     */
    public void compareTextFromList(String locator, String textCompare){
        List<WebElement> list = findElements(locator);
        for (int i = 0; i < list.size(); i++) {
            String text = list.get(i).getText();
            if (text.equalsIgnoreCase(textCompare)) {
                addLog("Value is: " + text + "\nThis case is PASSED");
                break;
            }
        }
    }
    public void compareTextFromList(By locator, String textCompare){
        List<WebElement> list = driver.findElements(locator);
        for(int i=0;i<list.size();i++){
            String text = list.get(i).getText();
            if(text.equalsIgnoreCase(textCompare)){
                addLog("Compare: "+text+"\nThis case is passed");
                break;
            }
        }
    }

    /**
     * get text language application
     * @param text
     * @return
     */
    public String getLanguageDevice(String text){
        String languageKey = "en";
        Map<String, String> language = new HashMap<String, String>();
        if(Utils.getInstance().isAndroidDevice()) {

            //check language united state
            language.put("en", "english");


        }
        if (Utils.getInstance().isIosDevice()){
            //check language united state
            language.put("en", "Where can we take you?");
        }
        for(String key:language.keySet()) {
            String value = language.get(key);
            if (value.equalsIgnoreCase(text)) {
                System.out.println("key: " + key);
                languageKey = key;
            }
        }
        System.out.println("k1: " + languageKey);

        return languageKey;
    }

    /**
     * get text language to compare
     * @param
     * @return
     */
//    public MyDriver_Customer_Language getTextByLanguage(String languageKey) {
//        String fileName = "resources/" + languageKey + ".json";
//        try {
//            Gson gson = new GsonBuilder().create();
//            JsonReader jsonReader = new JsonReader(new FileReader(new File(fileName)));
//            MyDriver_Customer_Language textTranslate = gson.fromJson(jsonReader, MyDriver_Customer_Language.class);
//            return textTranslate;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public String changeFormatDate (String date, String read, String write){
        String formattedDate = "";
        SimpleDateFormat readFormat = new SimpleDateFormat(read);
        SimpleDateFormat writeFormat = new SimpleDateFormat(write);
        try{
            formattedDate = writeFormat.format(readFormat.parse(date));
        }catch (ParseException e){
            e.printStackTrace();
        }
        //System.out.println("format: "+formattedDate);
        return formattedDate;
    }

    /**
     * turn off and turn on wifi
     */
    /*public void turnOFFWifi() {
        NetworkConnectionSetting networkConnection = new NetworkConnectionSetting(false, false, false);
        networkConnection.setData(true); // enable mobile data
        networkConnection.setWifi(false); // close wifi
        ((AndroidDriver) driver).setNetworkConnection(networkConnection);
        networkConnection = ((AndroidDriver) driver).getNetworkConnection();
        System.out.println("airplaneModeEnabled() :: " + networkConnection.airplaneModeEnabled() +
                "\ndataEnabled() :: " + networkConnection.dataEnabled() +
                "\nwifiEnabled() :: " + networkConnection.wifiEnabled());
    }
    public void turnONWifi(){
        NetworkConnectionSetting networkConnection = new NetworkConnectionSetting(false, false, false);
        networkConnection.setData(true); // enable mobile data
        networkConnection.setWifi(true); // open wifi
        ((AndroidDriver)driver).setNetworkConnection(networkConnection);
        networkConnection = ((AndroidDriver)driver).getNetworkConnection();
        System.out.println("airplaneModeEnabled() :: " + networkConnection.airplaneModeEnabled() +
                "\ndataEnabled() :: " + networkConnection.dataEnabled() +
                "\nwifiEnabled() :: " + networkConnection.wifiEnabled());
    }*/

    /**
     * get textElements for list
     * @param locator
     * @param i
     * @return
     */
    public String getTextElements(String locator, int i){
        List<WebElement> list = findElements(locator);
//        System.out.println("- "+list.get(i).getText());
        return list.get(i).getText();
    }
    public String getTextElementsFromMain(String mainBranch, int iMain, String subLocator){
        //get text element from main branch --> then get sub branch
        List<WebElement> list = findElements(mainBranch);
        String getTextSubLocator = list.get(iMain).findElement(byLocator(subLocator)).getText();
//        System.out.println("- "+getTextSubLocator);
        return getTextSubLocator;
    }

    /**
     *
     * @param getDate
     * @param getTime
     * @return
     * @throws ParseException
     */
    public boolean checkDurationTime(String getDate, String getTime){
        Calendar calendarOfCurrentDate = Calendar.getInstance();
        Date currentDate = new Date();
        calendarOfCurrentDate.setTime(currentDate);

        String getDateTime = getDate + " " + getTime;
        System.out.println("-------------------------------------------------");

        DateFormat dateTimeFormat = new SimpleDateFormat("E, MMMM dd, yyyy hh:mm a", Locale.ENGLISH);
        Date compareDateTime = null;
        try {
            compareDateTime = dateTimeFormat.parse(getDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long MAX_DURATION = MILLISECONDS.convert(30, MINUTES);
        long duration = compareDateTime.getTime()-currentDate.getTime();

        if(duration<=MAX_DURATION) {
            System.out.println("return true");
            return true;
        }
        return false;
    }

    /**
     * clearDataApp
     */
    public void clearDataApp() {
        try {
            // clearing app data
            Runtime runtime = Runtime.getRuntime();
//            runtime.exec("pm clear YOUR_APP_PACKAGE_GOES HERE");
            runtime.exec("adb shell pm clear com.mydriver.driver.v2.alpha");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * update report pass/fail to testLink
     * @param testProject
     * @param testPlan
     * @param testCaseName
     * @param build
     * @param exception
     * @param result
     * @throws TestLinkAPIException
     * @throws testlink.api.java.client.TestLinkAPIException
     */
    public void updateTestLinkResult(String testProject, String testPlan, String testCaseName,String build, String exception, String result) throws TestLinkAPIException, testlink.api.java.client.TestLinkAPIException {
        TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEVKEY,URL);
        testlinkAPIClient.reportTestCaseResult(testProject, testPlan, testCaseName, build, exception, result);
//        System.out.println("str: "+testCaseName.length());
    }

}
