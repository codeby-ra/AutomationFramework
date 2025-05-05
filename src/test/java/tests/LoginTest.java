package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecurePage;

public class LoginTest extends BaseTest {

    @Test
    public void loginShouldWork() {
        driver.get("https://the-internet.herokuapp.com/login");
        LoginPage loginPage = new LoginPage(driver);
        SecurePage securePage = new SecurePage(driver);

        loginPage.login("tomsmith ", "SuperSecretPassword!");
        Assert.assertTrue(securePage.isSecureAreaDisplay());

    }
}
