package com.beecow.mobile.Market.IOS;

import com.beecow.component.BaseTest;
import com.beecow.screen.ActivityFirstScreen;
import com.beecow.screen.ActivitySecondScreen;
import com.beecow.screen.HomeScreen;
import com.beecow.screen.MarketScreen;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.beecow.model.CommonElement.marketPropertiesFile;

/**
 * Created by hangpham on 2017-02-07.
 */
public class CategotiesTest extends BaseTest{

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp(marketPropertiesFile);
    }

    @Test
    public void testIOSDemo() throws InterruptedException {
        System.out.println("click Cupid tab bar");
        driver.findElement(By.xpath("//*[@label='Cupid']")).click();
        Thread.sleep(5000);
    }
    @Test
    public void testIOSDemo1() throws InterruptedException {
        System.out.println("click Cupid tab bar");
        driver.findElement(By.xpath("//*[@label='Cupid']")).click();
        Thread.sleep(5000);
    }

}
