package com.beecow.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.Reporter;

import static com.beecow.model.MarketCategoriesElement.getMainViewWithSearchAndFooter;

/**
 * Created by hangpham on 2017-03-02.
 */
public class SwipeFunctions {
    private AppiumDriver driver;
    private Dimension size;

    /**
     * Swipe on the screen base on start coordinate and end coordinate
     *
     * @param startX   Start X coordinate - range 1-> 5
     * @param startY   Start X coordinate - range 1-> 10
     * @param endX     End X coordinate - range 1-> 5
     * @param endY     End Y coordinate - range 1-> 10
     * @param duration how fast it swipe, in mili-seconds
     * @throws Exception
     */
    public void Swipe(int startX, int startY, int endX, int endY, int duration) throws Exception {
        try {

            //Verify input parameters
            if (startX < 0 || startX > 10) {
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[SwipeDown] Input parameter failed: startX must be in range 1 -> 5, your input is [" + startX + "]");
            }
            if (startY < 0 || startY > 10) {
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[SwipeDown] Input parameter failed: startY must be in range 1 -> 10, your input is [" + startY + "]");
            }
            if (endX < 0 || endX > 10) {
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[SwipeDown] Input parameter failed: endX must be in range 1 -> 5, your input is [" + endX + "]");
            }
            if (endY < 0 || endY > 5) {
                Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                throw new Exception("[SwipeDown] Input parameter failed: endY must be in range 1 -> 10, your input is [" + endY + "]");
            }
            Dimension dimensions = driver.manage().window().getSize();

            int screenWidth = dimensions.getWidth();
            int screenHeight = dimensions.getHeight();

            int actualStartX = screenWidth / 10 * startX;
            int actualStartY = screenHeight / 10 * startY;
            int actualEndX = screenWidth / 10 * endX;
            int actualEndY = screenHeight / 10 * endY;
            System.out.println("Screen Width x Height (" + screenWidth + "," + screenHeight + ")");
            System.out.println("Start coordinate: [" + actualStartX + "," + actualStartY + "], End coordinate: [" + actualEndX + "," + actualEndY + "]");
            if (Utils.getInstance().isAndroidDevice()) {
                ((AndroidDriver) driver).swipe(actualStartX, actualStartY, actualEndX, actualEndY, duration);
            } else if (Utils.getInstance().isIosDevice()) {
                ((IOSDriver) driver).swipe(actualStartX, actualStartY, actualEndX, actualEndY, duration);
            }
        } catch (NoSuchElementException noElement) {
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[SwipeDown] Can't find Element: " + noElement.getMessage());
        } catch (Exception ex) {
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[SwipeDown] FAILED: " + ex.getMessage());
        }
    }

    public SwipeFunctions(AppiumDriver driver) {
        this.driver = driver;
    }

    public void swipeLeftToRightElement(WebElement el) {
        size = driver.manage().window().getSize();
        // get the X coordinate of the upper left corner of the element, then add the element's width to get the rightmost X value of the element
        int leftX = el.getLocation().getX() + el.getSize().getWidth() / 10;
        int rightX = leftX + el.getSize().getWidth() - el.getSize().getWidth() / 5;

        // get the Y coordinate of the upper left corner of the element, then subtract the height to get the lowest Y value of the element
        int upperY = el.getLocation().getY();
        int middleY = upperY + (el.getSize().getHeight()) / 2;
        System.out.println("size: " + size + " - leftX: " + leftX + " - rightX: " + rightX + " - middleY: " + middleY);
        if (Utils.getInstance().isAndroidDevice()) {
            ((AndroidDriver) driver).swipe(leftX, middleY, rightX, middleY, 1500);

        } else ((IOSDriver) driver).swipe(leftX, middleY, rightX, middleY, 1500);
    }

    public void swipeRightToLeftElement(WebElement el) {
        size = driver.manage().window().getSize();
        // get the X coordinate of the upper left corner of the element, then add the element's width to get the rightmost X value of the element
        int leftX = el.getLocation().getX() + el.getSize().getWidth() / 10;
        int rightX = leftX + el.getSize().getWidth() - el.getSize().getWidth() / 5;

        // get the Y coordinate of the upper left corner of the element, then subtract the height to get the lowest Y value of the element
        int upperY = el.getLocation().getY();
        int middleY = upperY + (el.getSize().getHeight()) / 2;
        System.out.println("size: " + size + " - leftX: " + leftX + " - rightX: " + rightX + " - middleY: " + middleY);
        if (Utils.getInstance().isAndroidDevice()) {
            ((AndroidDriver) driver).swipe(rightX, middleY, leftX, middleY, 1500);

        } else ((IOSDriver) driver).swipe(rightX, middleY, leftX, middleY, 1500);
    }

    public void swipingUpElementFromBottomToTop(WebElement el) {
        int topY = el.getLocation().getY();
        int bottomY = topY + el.getSize().getHeight() - el.getSize().getHeight() / 20;

        int centerX = el.getLocation().getX() + el.getSize().getWidth() / 2;

        System.out.println("topY " + topY + " - bottomY " + bottomY + " - centerX " + centerX);
        if (Utils.getInstance().isAndroidDevice()) {
            ((AndroidDriver) driver).swipe(centerX, bottomY, centerX, topY, 3000);

        } else ((IOSDriver) driver).swipe(centerX, bottomY, centerX, topY, 3000);
    }

    public void swipingDownElementFromBottomToTop(WebElement el) {
        int topY = el.getLocation().getY() + el.getSize().getHeight() / 20;
        int bottomY = topY + el.getSize().getHeight() - el.getSize().getHeight() / 20;

        int centerX = el.getLocation().getX() + el.getSize().getWidth() / 2;

        System.out.println("topY " + topY + " - bottomY " + bottomY + " - centerX " + centerX);
        if (Utils.getInstance().isAndroidDevice()) {
            ((AndroidDriver) driver).swipe(centerX, topY, centerX, bottomY, 3000);

        } else ((IOSDriver) driver).swipe(centerX, topY, centerX, bottomY, 3000);
    }

    public void swipingUpFromElementToOtherElement(WebElement thisElement, WebElement elementTop) {
        //get
        int topY = elementTop.getLocation().getY();
        int bottomY = thisElement.getLocation().getY() + thisElement.getSize().getHeight()/2;

        int centerX = thisElement.getLocation().getX() + thisElement.getSize().getWidth() / 2;

        System.out.println("topY " + topY + " - bottomY " + bottomY + " - centerX " + centerX);
        if (Utils.getInstance().isAndroidDevice()) {
            ((AndroidDriver) driver).swipe(centerX, bottomY, centerX, topY, 3000);
        } else ((IOSDriver) driver).swipe(centerX, bottomY, centerX, topY, 3000);

    }

    public void swipingDownFromElementToOtherElement(WebElement thisElement, WebElement elementBottom) {
        //get
        int bottomY = elementBottom.getLocation().getY() + thisElement.getSize().getHeight();
        int elementTopY = thisElement.getLocation().getY()/2;

        int centerX = thisElement.getLocation().getX() + thisElement.getSize().getWidth() / 2;

        System.out.println("topY " + elementTopY + " - bottomY " + bottomY + " - centerX " + centerX);
        if (Utils.getInstance().isAndroidDevice()) {
            ((AndroidDriver) driver).swipe(centerX, elementTopY, centerX, bottomY, 3000);
        } else ((IOSDriver) driver).swipe(centerX, elementTopY, centerX, bottomY, 3000);

    }
}
