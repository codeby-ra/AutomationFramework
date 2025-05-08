package utils;

import com.aventstack.extentreports.ExtentTest;
import config.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import reporting.ExtentTestManager;

public class ElementActions {
    private final WaitUtils wait;

    private final int TIMEOUT = Integer.parseInt(ConfigReader.get("timeout"));

    WebDriver driver;
    ExtentTest test;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        this.test = ExtentTestManager.getTest();
    }

    public void sendKeys(WebElement element, String value, String elementName) {
        WebElement webElement = wait.waitForVisibility(element, TIMEOUT);
        try {
            highlight(webElement);
            webElement.clear();
            webElement.sendKeys(value);

            ScreenshotUtil.addScreenshotToReport(driver, test, "Entered text into: " + elementName, "pass");
        } catch (Exception e) {
            ScreenshotUtil.addScreenshotToReport(driver, test, "Failed to enter text into: " + elementName + " - " + e.getMessage(), "fail");
            throw e;
        } finally {
            unhighlight(webElement);
        }
    }

    public void click(WebElement element, String elementName) {
        WebElement webElement = wait.waitForClickability(element, TIMEOUT);
        try {
            highlight(webElement);
            webElement.click();
            ScreenshotUtil.addScreenshotToReport(driver, test, "Clicked on: " + elementName, "pass");
        } catch (Exception e) {
            ScreenshotUtil.addScreenshotToReport(driver, test, "Failed to click on: " + elementName + " - " + e.getMessage(), "fail");
            throw e;
        }
    }

    private void highlight(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='5px solid deeppink'", element);
    }

    private void unhighlight(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", element);
    }

}

