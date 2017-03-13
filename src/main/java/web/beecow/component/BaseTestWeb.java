package web.beecow.component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hoang Nguyen on 3/1/2017.
 */
public class BaseTestWeb {
    String osName = System.getProperty("os.name");
    public WebDriver driverWeb;

    public void setUp(){

        String webdriverPath = Paths.get(".").toAbsolutePath().normalize().toString() + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "web" + File.separator + "beecow" + File.separator + "webdrivers" + File.separator;;
        if (osName.toLowerCase().contains("win")){
            webdriverPath += "chromedriver_win.exe";
        }else{
            webdriverPath += "chromedriver_mac";
        }
        System.setProperty("webdriver.chrome.driver", webdriverPath);
        Map<String, Object> plugin_Chrome = new HashMap<String, Object>();
        plugin_Chrome.put("enabled", false);
        plugin_Chrome.put("name", "Chrome PDF Viewer");
        Map<String, Object> prefs_Chrome = new HashMap<String, Object>();
        prefs_Chrome.put("profile.default_content_settings.popups", 0);
        prefs_Chrome.put("plugins.plugins_list", Arrays.asList(plugin_Chrome));
        DesiredCapabilities caps_Chrome = DesiredCapabilities.chrome();
        ChromeOptions options_Chrome = new ChromeOptions();
        options_Chrome.setExperimentalOption("prefs", prefs_Chrome);
        options_Chrome.addArguments("start-maximized");
        caps_Chrome.setCapability(ChromeOptions.CAPABILITY, options_Chrome);
        caps_Chrome.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        driverWeb = new ChromeDriver(caps_Chrome);
    }



}




