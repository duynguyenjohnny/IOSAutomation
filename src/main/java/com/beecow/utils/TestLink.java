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
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * Created by HangPham on 12/18/2016.
 */
public class TestLink {
    /**
     * update report pass/fail to testLink
     * @param testProject
     * @param testPlan
     * @param testCaseName
     * @param build
     * @param exception
     * @param result
     * @throws TestLinkAPIException
     * @throws TestLinkAPIException
     */
    public static void updateTestLinkResult(String testProject, String testPlan, String testCaseName,String build, String exception, String result) throws TestLinkAPIException, TestLinkAPIException {
        TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEVKEY,URL);
        testlinkAPIClient.reportTestCaseResult(testProject, testPlan, testCaseName, build, exception, result);
    }

}
