package com.beecow.component;

        import com.beecow.utils.Helper;
        import com.beecow.utils.Result;

        import io.appium.java_client.AppiumDriver;

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
