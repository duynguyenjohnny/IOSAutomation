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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.NoSuchElementException;

import static com.beecow.component.Constant.*;
import static com.beecow.utils.PropertiesUtils.testlinkDevKey;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * Created by HangPham on 12/18/2016.
 */
public class TestLink {
    /**
     *
     * @param testProject
     * @param testPlan
     * @param testCaseName
     * @param build
     * @param ExcutionNote
     * @param result
     * @throws TestLinkAPIException
     */
    public static void updateResult(String testProject, String testPlan, String testCaseName,String build, String ExcutionNote, String result) throws TestLinkAPIException {
        System.out.println("Start update result to Testlink");
        TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient("bed1de6a318ab11678ee0b96272eefba",URL);
        testlinkAPIClient.reportTestCaseResult(testProject, testPlan, testCaseName, build, ExcutionNote, result);
        System.out.println("End update result to Testlink");
    }

}
