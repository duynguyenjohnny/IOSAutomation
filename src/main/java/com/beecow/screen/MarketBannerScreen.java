package com.beecow.screen;

import com.beecow.component.CommonScreenObjects;
import io.appium.java_client.AppiumDriver;

import static com.beecow.model.CommonElement.failed;
import static com.beecow.model.MarketBannerElement.*;

/**
 * Created by hangpham on 2017-02-07.
 */
public class MarketBannerScreen extends CommonScreenObjects{

    public MarketBannerScreen(AppiumDriver driver) {
        super(driver);
    }

    public void checkExistIndicatorBanner(){
        result.setExpectation("The indicator is shown correctly!");
        if(getHelper().isElementEnabled(getIndicatorBanner())){
            result.setResult(failed);
            result.setObservation("The indicator does not show at banner");
        }
        result.check();
    }
}
