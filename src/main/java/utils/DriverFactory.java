package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();


    public static WebDriver initDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            tlDriver.set(new ChromeDriver());
        }
        getDriver().manage().window().maximize();
        return getDriver();
    }


    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        getDriver().quit();
        tlDriver.remove();
    }


}
