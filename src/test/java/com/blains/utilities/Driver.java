package com.blains.utilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;


public class Driver {

    /*
     Creating a private constructor, so we are closing access to the object of this class
     from outside of any classes
     */
    private Driver(){}

    /*
    Making our 'driver' instance private, so that it is not reachable from outside of any class
    We make it static, because we want it to run before anyting else,
    also we will use it in static method
     */
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    /*
    Create re-usable utility method which will return same driver instance when we call it.
     */
    public static WebDriver getDriver(){

        if(driverPool.get() == null){  // if driver/browser was never opened

            String browserType = ConfigurationReader.getProperty("browser");

        /*
        Depending on the browserType our switch statement will determine
        to open specific type of browser/driver
         */
            switch(browserType){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "headless-chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions option = new ChromeOptions();
                    option.setHeadless(true);
                    driverPool.set(new ChromeDriver(option));
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
            }
        }

        // Same driver instance will be returned every time we call Driver.getDriver() method
        return driverPool.get();

    }


    public static void closeDriver(){
        if(driverPool.get() !=null) {
            driverPool.get().quit(); // this line will kill the session. value will noy be null
            driverPool.remove();
        }
    }

}
