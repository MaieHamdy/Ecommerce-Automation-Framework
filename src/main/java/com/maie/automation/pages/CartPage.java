
package com.maie.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By cartIcon = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartItems = By.className("cart_item");

    private By cartTitle = By.xpath(
            "//span[@class='title' and text()='Your Cart']"
    );

    private By productName = By.className("inventory_item_name");
    private By productPrice = By.className("inventory_item_price");

    private By removeButton = By.xpath(
            "//div[contains(@class,'cart_item')]//button[normalize-space()='Remove']"
    );

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Open Cart Page
    public void openCart() {

        wait.until(
                ExpectedConditions.elementToBeClickable(cartIcon)
        ).click();

        wait.until(
                ExpectedConditions.urlContains("cart.html")
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartTitle)
        );
    }

    // Get Cart Page Title
    public String getCartTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartTitle)
        ).getText();
    }

    // Get Cart Items Count
    public int getCartItemsCount() {
        return driver.findElements(cartItems).size();
    }

    // Get Cart Badge Count
    public String getCartBadgeCount() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartBadge)
        ).getText();
    }

    // Get Product Name
    public String getCartProductName() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(productName)
        ).getText();
    }

    // Get Product Price
    public String getCartProductPrice() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(productPrice)
        ).getText();
    }

    // Remove Product From Cart
    public void removeProductFromCart() {

        wait.until(
                ExpectedConditions.elementToBeClickable(removeButton)
        ).click();

        wait.until(
                ExpectedConditions.numberOfElementsToBe(
                        cartItems, 0
                )
        );
    }
}
