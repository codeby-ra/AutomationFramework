package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class LoginPage {
    private WebDriver driver;
    private WaitUtils wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WaitUtils(driver);
    }


    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;


    public void login(String username, String password) {
        wait.waitForVisibility(usernameInput, 5).sendKeys(username);
        wait.waitForVisibility(passwordInput, 5).sendKeys(password);
        wait.waitForClickability(loginBtn, 5).click();
    }
}
