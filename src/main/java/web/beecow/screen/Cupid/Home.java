package web.beecow.screen.Cupid;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import web.beecow.component.BaseTestWeb;
import web.beecow.utils.ObjectMap;
import web.beecow.utils.Utils;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Hoang Nguyen on 3/1/2017.
 */
public class Home {
    public WebDriver driver;
    public Home(WebDriver driver){
        this.driver=driver;
    }
    ObjectMap objmap = new ObjectMap("Cupid" + File.separator + "home.properties");

    public void GoToURL(String url) throws  Exception{
        driver.get("http://api.mediastep.com");
        TimeUnit.SECONDS.sleep(2);
        WebElement WEspan_Account = driver.findElement(objmap.getLocator("span_Account"));
        Utils.Click(WEspan_Account, driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement WEbtn_Login = driver.findElement(objmap.getLocator("btn_Login"));
        Utils.Click(WEbtn_Login, driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement WEtxt_UserName = driver.findElement(objmap.getLocator("txt_UserName"));
        WEtxt_UserName.sendKeys("Phuoc Ha");
        TimeUnit.SECONDS.sleep(2);
        WebElement WEtxt_Password = driver.findElement(objmap.getLocator("txt_Password"));
        WEtxt_Password.sendKeys("Hang Pham");
        TimeUnit.SECONDS.sleep(2);
    }
}
