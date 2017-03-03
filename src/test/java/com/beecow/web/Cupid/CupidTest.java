package com.beecow.web.Cupid;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.beecow.component.BaseTestWeb;
import web.beecow.screen.Cupid.Home;

/**
 * Created by Hoang Nguyen on 3/1/2017.
 */
public class CupidTest extends BaseTestWeb{
    private Home home;
    @BeforeMethod
    public void setUp(){
        super.setUp();
        home= new Home(driverWeb);
    }
    @Test
    public void Test()throws Exception{
        home.GoToURL("http://tindecken.com");
    }

    @AfterMethod
    public void releaseDriver() throws Exception {
        if(driverWeb != null){
            driverWeb.close();
            driverWeb.quit();
        }
    }
}
