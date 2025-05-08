package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private final WebDriver driver;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForVisibility(WebElement element, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds)).until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickability(WebElement element, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean isDisplayed(WebElement element){
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e){
            return false;
        }
    }
}
