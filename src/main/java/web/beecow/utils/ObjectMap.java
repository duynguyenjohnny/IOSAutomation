package web.beecow.utils;

import org.openqa.selenium.By;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * Created by Hoang Nguyen on 3/1/2017.
 */
public class ObjectMap {
    //property file and provide the locator information to the test.

    Properties properties;

    public ObjectMap(String mapFile)
    {
        properties = new Properties();
        try {
            String workingdir = Paths.get(".").toAbsolutePath().normalize().toString() + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "web" + File.separator + "beecow" + File.separator + "model" + File.separator;
            FileInputStream Master = new FileInputStream(workingdir + mapFile);
            properties.load(Master);
            Master.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * get xpath string by using name in .properties file
     * @param: driver
     * @return: xpath string if exist, null if the name is not exist
     */
    public  String locatorValue(String ElementName) throws Exception{
        try{
            String locator = properties.getProperty(ElementName);
            String[] arrLocatorValue = locator.split(":",2);
            if(arrLocatorValue.length > 1){
                String locatorValue = arrLocatorValue[1];
                return locatorValue;
            }else{
                System.out.println("locatorValue function failed. Can not split the xpath of [" + ElementName + "], please check it from file .properties");
                return null;
            }
        }catch (Exception e){
            System.out.println("locatorValue function failed" + e.getMessage());
        }
        return null;
    }
    public By getLocator(String ElementName) throws Exception{
        try{
            //Read value using the logical name as Key
            String locator = properties.getProperty(ElementName, "not found");
            if(locator.toLowerCase() == "not found"){
                System.out.println("Not found value for key [" + ElementName + "], Please check key name in properties file");
                return null;
            }else{
                //Split the value which contains locator type and locator value
                String locatorType = locator.split(":",2)[0];
                String locatorValue = locator.split(":",2)[1];
                //Return a instance of By class based on type of locator
                if(locatorType.toLowerCase().equals("id"))
                    return By.id(locatorValue);
                else if(locatorType.toLowerCase().equals("name"))
                    return By.name(locatorValue);
//		        else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
//		              return By.className(locatorValue);
//		        else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
//		              return By.className(locatorValue);
//		        else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
//		              return By.linkText(locatorValue);
//		        else if(locatorType.toLowerCase().equals("partiallinktext"))
//		              return By.partialLinkText(locatorValue);
//		        else if((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
//		              return By.cssSelector(locatorValue);
                else if(locatorType.toLowerCase().equals("xpath"))
                    return By.xpath(locatorValue);
            }
        }catch (NoSuchElementException e) {
            System.out.println("Not found object [" + ElementName + "]" + " " + e.toString());
        }
        return null;
    }
}
