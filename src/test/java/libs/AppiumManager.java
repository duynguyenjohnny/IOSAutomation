package libs;

import io.appium.java_client.AppiumDriver;

import java.io.IOException;

/**
 * Created by HangPham on 12/14/2016.
 */
/**
 * Appium Manager - this class contains method to start and stops appium server
 */
public class AppiumManager {
    CommandPrompt cp = new CommandPrompt();
    AvailablePorts ap = new AvailablePorts();

    /**
     * start appium with auto generated ports : appium port, chrome port, and bootstap port
     */
    public String startAppium() throws IOException, InterruptedException {
        //start appium server
        String port = ap.getPort();
        String chromePort = ap.getPort();
        String bootstrapPort = ap.getPort();
        System.out.println("get port at Appium manager: " + port);
        System.out.println("get chrome port at Appium manager: " + chromePort);
        System.out.println("get boot strap port at Appium manager: " + bootstrapPort);

        String command = "\"C:\\Program Files (x86)\\Appium\\node.exe\" \"C:\\Program Files (x86)\\Appium\\node_modules\\appium\\lib\\server\\main.js\" --session-override -p " + port + " --chromedriver-port " + chromePort + " -bp " + bootstrapPort;

        // E:\Android\sdk\build-tools\25.0.0\zipalign.exe -f 4 E:\Company\automationTest\project_Appium\BeeCowProjectMobile
//"C:\Program Files (x86)\Appium\node.exe" "C:\Program Files (x86)\Appium\node_modules\appium\lib\server\main.js" --address 127.0.0.1 --port 4723 --app E:\Company\automationTest\project_Appium\BeeCowProjectMobile\FoodyVN.apk --pre-launch --platform-name Android --platform-version 19 --automation-name Appium --log-no-color

        System.out.println(command);
        String output = cp.runCommand(command);
        System.out.println(output);

        if(output.contains("not"))
        {
            System.out.println("\nAppium is not installed");
            System.exit(0);
        }
        return port;
    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//        AppiumManager manager = new AppiumManager();
//        manager.startAppium();
//    }
}
