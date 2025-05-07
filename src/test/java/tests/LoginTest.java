package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecurePage;

public class LoginTest extends BaseTest {

    @Test
    public void loginShouldWork() {
        LoginPage loginPage = new LoginPage(getDriver());
        SecurePage securePage = new SecurePage(getDriver());
        loginPage.login("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(securePage.isSecureAreaDisplay());
    }
}
