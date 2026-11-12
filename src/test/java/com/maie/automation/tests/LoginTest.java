
package com.maie.automation.tests;

import com.maie.automation.base.BaseTest;
import com.maie.automation.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import io.qameta.allure.testng.AllureTestNg;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Epic("E-Commerce Automation")
@Feature("Login Functionality")
@Listeners(AllureTestNg.class)
public class LoginTest extends BaseTest {

    @Test
    @Story("Verify Login Page")
    @Description("Verify that the login page is displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    public void verifyLoginPageIsDisplayed() {

        LoginPage loginPage = new LoginPage(driver);

        String actualTitle = driver.getTitle();

        Assert.assertEquals(
                actualTitle,
                "Swag Labs",
                "Login page title is incorrect!"
        );
    }

    @Test
    @Story("Successful Login")
    @Description("Verify that a user can log in with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void verifySuccessfulLogin() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Login failed!"
        );
    }

    @Test
    @Story("Invalid Login")
    @Description("Verify that an error message appears with an invalid password")
    @Severity(SeverityLevel.NORMAL)
    public void verifyLoginWithInvalidPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "wrong_password");

        String actualError = loginPage.getErrorMessage();

        Assert.assertTrue(
                actualError.contains("Username and password do not match"),
                "Incorrect error message!"
        );
    }
}

