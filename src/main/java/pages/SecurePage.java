package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class SecurePage {
    private final WaitUtils wait;

    public SecurePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new WaitUtils(driver);
    }

    @FindBy(xpath = "//div[@id='flash' and contains(text(),'You logged into a secure area!')]")
    private WebElement success;


    public boolean isSecureAreaDisplay() {
        return wait.waitForVisibility(success, 5).isDisplayed();
    }
}
