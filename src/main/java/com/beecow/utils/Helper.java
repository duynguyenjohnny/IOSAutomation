package com.beecow.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSDriver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
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

import static com.beecow.component.Constant.DEVKEY;
import static com.beecow.component.Constant.URL;
import static com.beecow.utils.PropertiesUtils.androidAppPackage;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.lept.*;
import static org.bytedeco.javacpp.tesseract.*;


/**
 * Created by HangPham on 12/18/2016.
 */
public class Helper {
    private AppiumDriver driver;
    private Dimension size;

    public Helper(AppiumDriver driver) {
        this.driver = driver;
    }

    public By byLocator(String selectorTypeStr, String value) {
        By e = null;
        UISelectorType selector = UISelectorType.fromString(selectorTypeStr);
        if (driver instanceof AndroidDriver) {
            String appPackage = androidAppPackage;
            switch (selector) {
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
    public static String sScreenShotPath;
    public void takeScreenshot(String Project, String ClassNames, String Result, String TCsID) {
        String sProjectPath = new File("src/report").getAbsolutePath().concat(File.separator).concat(Project).concat(File.separator);
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
        sScreenShotPath = fileScrShot;
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
//    public void turnOFFWifi() {
//        NetworkConnectionSetting networkConnection = new NetworkConnectionSetting(false, false, false);
//        networkConnection.setData(true); // enable mobile data
//        networkConnection.setWifi(false); // close wifi
//        ((AndroidDriver) driver).setNetworkConnection(networkConnection);
//        networkConnection = ((AndroidDriver) driver).getNetworkConnection();
//        System.out.println("airplaneModeEnabled() :: " + networkConnection.airplaneModeEnabled() +
//                "\ndataEnabled() :: " + networkConnection.dataEnabled() +
//                "\nwifiEnabled() :: " + networkConnection.wifiEnabled());
//    }
//    public void turnONWifi(){
//        NetworkConnectionSetting networkConnection = new NetworkConnectionSetting(false, false, false);
//        networkConnection.setData(true); // enable mobile data
//        networkConnection.setWifi(true); // open wifi
//        ((AndroidDriver)driver).setNetworkConnection(networkConnection);
//        networkConnection = ((AndroidDriver)driver).getNetworkConnection();
//        System.out.println("airplaneModeEnabled() :: " + networkConnection.airplaneModeEnabled() +
//                "\ndataEnabled() :: " + networkConnection.dataEnabled() +
//                "\nwifiEnabled() :: " + networkConnection.wifiEnabled());
//    }

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
     * This function will take screenshot of current screen, then use OCR to parse into Text, then verify with input text
     * @param sVerifyText Text need to verify in current screen, case sensitive
     */
    public void VerifyTextInCurrentScreen(String sVerifyText) throws Exception{
        try{
            //Take Screenshot
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            System.out.println("Start - Parse OCR");
            BytePointer outText;

            TessBaseAPI api = new TessBaseAPI();
            // Initialize tesseract-ocr with English, without specifying tessdata path
            if (api.Init(null, "eng") != 0) {
                System.err.println("Could not initialize tesseract.");
                System.exit(1);
            }
            // Open input image with leptonica library
            PIX image = pixRead(scrFile.getCanonicalPath());
            api.SetImage(image);
            // Get OCR result
            outText = api.GetUTF8Text();
            System.out.println("OCR output:\n" + outText.getString());

            // Destroy used object and release memory
            api.End();
            outText.deallocate();
            pixDestroy(image);
            System.out.println("End - Parse OCR");

            //Verify with Input parameter
            if (outText.getString().contains(sVerifyText)){
                System.out.println("[VerifyTextInCurrentScreen] Passed");
            }else{
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[VerifyTextInCurrentScreen] FAILED: Expected [" + sVerifyText + "] in the current screen, Actual [Not Found]");
            }
        }catch (Exception ex){
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[VerifyTextInCurrentScreen] - FAILED: " + ex.getMessage());
        }
    }

    /**
     * Swipe Left To Right Element
     * @param el Element need to swipe to
     */
    public void swipeLeftToRightElement(WebElement el){
        // get the X coordinate of the upper left corner of the element, then add the element's width to get the rightmost X value of the element
        int leftX = el.getLocation().getX()+1;
        int rightX = leftX + el.getSize().getWidth();

        // get the Y coordinate of the upper left corner of the element, then subtract the height to get the lowest Y value of the element
        int upperY = el.getLocation().getY();
        int middleY = upperY + (el.getSize().getHeight()) / 2;
        if(Utils.getInstance().isAndroidDevice()){
            ((AndroidDriver)driver).swipe(leftX, middleY, rightX, middleY, 3000);

        }else ((IOSDriver)driver).swipe(leftX, middleY, rightX, middleY, 3000);
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
        if(Utils.getInstance().isAndroidDevice()){
            ((AndroidDriver)driver).swipe(middleX, upperY, middleX, lowerY, 3000);
        }else         driver.swipe(middleX, upperY, middleX, lowerY, 3000);

    }
}
