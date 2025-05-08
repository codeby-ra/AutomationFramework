package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementActions;

public class LoginPage {

    WebDriver driver;
    ElementActions actions;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new ElementActions(driver);
    }


    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;


    public void login(String username, String password){
        actions.sendKeys(usernameInput, username, "Username Field");
        actions.sendKeys(passwordInput, password, "Password Field");
        actions.click(loginBtn, "Login Button");
    }
}
