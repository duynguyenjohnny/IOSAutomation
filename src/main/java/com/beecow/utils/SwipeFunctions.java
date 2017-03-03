package com.beecow.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import static com.beecow.model.MarketCategoriesElement.getMainViewWithSearchAndFooter;

/**
 * Created by hangpham on 2017-03-02.
 */
public class SwipeFunctions {
    private AppiumDriver driver;
    private Dimension size;

    public SwipeFunctions(AppiumDriver driver){
        this.driver=driver;
    }

    public void swipeLeftToRightElement(WebElement el) {
        size = driver.manage().window().getSize();
        // get the X coordinate of the upper left corner of the element, then add the element's width to get the rightmost X value of the element
        int leftX = el.getLocation().getX() + el.getSize().getWidth()/10;
        int rightX = el.getSize().getWidth() - leftX;

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
        int leftX = el.getLocation().getX() + el.getSize().getWidth()/10;
        int rightX = el.getSize().getWidth() - leftX;

        // get the Y coordinate of the upper left corner of the element, then subtract the height to get the lowest Y value of the element
        int upperY = el.getLocation().getY();
        int middleY = upperY + (el.getSize().getHeight()) / 2;
        System.out.println("size: " + size + " - leftX: " + leftX + " - rightX: " + rightX + " - middleY: " + middleY);
        if (Utils.getInstance().isAndroidDevice()) {
            ((AndroidDriver) driver).swipe(rightX, middleY, leftX, middleY, 1500);

        } else ((IOSDriver) driver).swipe(rightX, middleY, leftX, middleY, 1500);
    }

    public void swipingUpFromBottomToTop(WebElement el) {
        int topY = el.getLocation().getY();
        int bottomY = topY + el.getSize().getHeight() - el.getSize().getHeight() / 10;

        int centerX = el.getLocation().getX() + el.getSize().getWidth() / 2;

        System.out.println("topY " + topY + " - bottomY " + bottomY + " - centerX " + centerX);
        if (Utils.getInstance().isAndroidDevice()) {
            ((AndroidDriver) driver).swipe(centerX, bottomY, centerX, topY, 3000);

        } else ((IOSDriver) driver).swipe(centerX, bottomY, centerX, topY, 3000);
    }

    public void swipingDownFromBottomToTop(WebElement el) {
        int topY = el.getLocation().getY() + el.getSize().getHeight() / 20;
        int bottomY = topY + el.getSize().getHeight() - el.getSize().getHeight() / 10;

        int centerX = el.getLocation().getX() + el.getSize().getWidth() / 2;

        System.out.println("topY " + topY + " - bottomY " + bottomY + " - centerX " + centerX);
        if (Utils.getInstance().isAndroidDevice()) {
            ((AndroidDriver) driver).swipe(centerX, topY, centerX, bottomY, 3000);

        } else ((IOSDriver) driver).swipe(centerX, topY, centerX, bottomY, 3000);
    }

    public void swipingUpFromElementToTop(WebElement el, WebElement locatorTop) {
        //get
        int topY = locatorTop.getLocation().getY();
        int bottomY = el.getLocation().getY() + el.getSize().getHeight();

        int centerX = el.getLocation().getX() + el.getSize().getWidth() / 2;

        System.out.println("topY " + topY + " - bottomY " + bottomY + " - centerX " + centerX);
        if (Utils.getInstance().isAndroidDevice()) {
            ((AndroidDriver) driver).swipe(centerX, bottomY, centerX, topY, 3000);
        } else ((IOSDriver) driver).swipe(centerX, bottomY, centerX, topY, 3000);

    }

    public void swipingDownFromElementToBottom(WebElement el, WebElement locatorBottom) {
        //get
        int bottomY = locatorBottom.getLocation().getY() + el.getSize().getHeight();
        int elementTopY = el.getLocation().getY();

        int centerX = el.getLocation().getX() + el.getSize().getWidth() / 2;

        System.out.println("topY " + elementTopY + " - bottomY " + bottomY + " - centerX " + centerX);
        if (Utils.getInstance().isAndroidDevice()) {
            ((AndroidDriver) driver).swipe(centerX, elementTopY, centerX, bottomY, 3000);
        } else ((IOSDriver) driver).swipe(centerX, elementTopY, centerX, bottomY, 3000);

    }
}
