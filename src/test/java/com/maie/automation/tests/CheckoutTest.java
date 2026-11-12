package com.maie.automation.tests;

import com.maie.automation.base.BaseTest;
import com.maie.automation.pages.LoginPage;
import com.maie.automation.pages.ProductsPage;
import com.maie.automation.pages.CartPage;
import com.maie.automation.pages.CheckoutPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test
    public void verifySuccessfulCheckout() {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Add Product to Cart
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();

        // Open Cart
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        // Start Checkout
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickCheckout();

        // Enter Customer Information
        checkoutPage.enterCustomerInformation(
                "Maie",
                "Hamdy",
                "12345"
        );

        // Continue to Overview
        checkoutPage.clickContinue();

        // Finish Order
        checkoutPage.clickFinish();

        // Verify Order Confirmation
        Assert.assertEquals(
                checkoutPage.getConfirmationMessage(),
                "Thank you for your order!",
                "Checkout was not completed successfully!"
        );
    }
    @Test
    public void verifyCheckoutWithoutFirstName() {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Add Product to Cart
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();

        // Open Cart
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        // Start Checkout
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickCheckout();

        // Leave First Name Empty
        checkoutPage.enterCustomerInformation(
                "",
                "Hamdy",
                "12345"
        );

        checkoutPage.clickContinue();

        // Verify Error Message
        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                "Error: First Name is required",
                "Expected validation error was not displayed!"
        );
    }
    @Test
    public void verifyCheckoutWithoutLastName() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickCheckout();

        checkoutPage.enterCustomerInformation(
                "Maie",
                "",
                "12345"
        );

        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                "Error: Last Name is required"
        );
    }
    @Test
    public void verifyCheckoutWithoutPostalCode() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickCheckout();

        checkoutPage.enterCustomerInformation(
                "Maie",
                "Hamdy",
                ""
        );

        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                "Error: Postal Code is required"
        );
    }

    @Test
    public void verifyCheckoutOverviewDetails() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickCheckout();

        checkoutPage.enterCustomerInformation(
                "Maie",
                "Hamdy",
                "12345"
        );

        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getOverviewProductName(),
                "Sauce Labs Backpack"
        );

        Assert.assertEquals(
                checkoutPage.getOverviewProductPrice(),
                "$29.99"
        );

        Assert.assertEquals(
                checkoutPage.getItemTotal(),
                "Item total: $29.99"
        );

        Assert.assertEquals(
                checkoutPage.getTax(),
                "Tax: $2.40"
        );

        Assert.assertEquals(
                checkoutPage.getTotal(),
                "Total: $32.39"
        );
    }

}