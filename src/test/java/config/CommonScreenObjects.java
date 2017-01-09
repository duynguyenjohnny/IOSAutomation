package config;

import io.appium.java_client.AppiumDriver;
import support.Helper;
import support.Result;

/**
 * Created by HangPham on 12/18/2016.
 */
public class CommonScreenObjects{
    public AppiumDriver driver;
    public Result result;
    public CommonScreenObjects(AppiumDriver driver){
        this.driver=driver;
        result=new Result();
    }
    public Helper getHelper(){
        return new Helper(driver);
    }

}
