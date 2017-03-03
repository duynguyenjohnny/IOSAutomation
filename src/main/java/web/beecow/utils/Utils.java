package web.beecow.utils;

import org.openqa.selenium.*;

/**
 * Created by Hoang Nguyen on 3/1/2017.
 */
public class Utils {
    /**
     * Click on an Element Object - Due to sometime Selenium can't not click on WebElement successfully by the normal way
     * @author: Hoang Nguyen
     * @param: driver
     * @param: WebElement need to click on
     */
    public static void Click(WebElement element, WebDriver driver) throws Exception {
        try {
            if (element.isEnabled() && element.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                //Log.Info("", "Clicked object [" + element.toString() + "]");
                //System.out.println("Clicked object [" + element.toString() + "]");
            } else {
                //System.out.println("Unable to click on element");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is not attached to the page document "+ e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element was not found in DOM "+ e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to click on element "+ e.getStackTrace());
        }
    }
}
