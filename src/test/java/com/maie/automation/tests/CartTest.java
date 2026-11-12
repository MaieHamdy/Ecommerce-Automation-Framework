package com.maie.automation.tests;

import com.maie.automation.base.BaseTest;
import com.maie.automation.pages.LoginPage;
import com.maie.automation.pages.ProductsPage;
import com.maie.automation.pages.CartPage;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.AllureTestNg;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

@Listeners(AllureTestNg.class)
@Epic("E-Commerce Automation")
@Feature("Cart Module")
public class CartTest extends BaseTest {

    @Test
    @Story("Add Product to Cart")
    @Description("Verify that a product can be added to the cart and displayed correctly.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyProductCanBeAddedToCart() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getCartBadgeCount(),
                "1",
                "Cart badge count is incorrect!"
        );

        cartPage.openCart();

        Assert.assertEquals(
                cartPage.getCartTitle(),
                "Your Cart",
                "Cart page title is incorrect!"
        );

        Assert.assertEquals(
                cartPage.getCartItemsCount(),
                1,
                "Cart should contain one product!"
        );
    }

    @Test
    @Story("Cart Product Details")
    @Description("Verify that the correct product name and price are displayed in the cart.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyCartProductNameAndPrice() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        Assert.assertEquals(
                cartPage.getCartProductName(),
                "Sauce Labs Backpack",
                "Product name is incorrect!"
        );

        Assert.assertEquals(
                cartPage.getCartProductPrice(),
                "$29.99",
                "Product price is incorrect!"
        );
    }

    @Test
    @Story("Remove Product from Cart")
    @Description("Verify that a product can be removed from the cart and the cart badge disappears.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyProductCanBeRemovedFromCart() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        cartPage.removeProductFromCart();

        Assert.assertEquals(
                cartPage.getCartItemsCount(),
                0,
                "Cart should be empty after removing the product!"
        );

        Assert.assertEquals(
                driver.findElements(
                        By.className("shopping_cart_badge")
                ).size(),
                0,
                "Cart badge should disappear after removing the product!"
        );
    }
}