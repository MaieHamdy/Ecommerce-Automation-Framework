
package com.maie.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By pageTitle = By.className("title");
    private By productsList = By.className("inventory_item");
    private By sortDropdown = By.className("product_sort_container");
    private By productNames = By.className("inventory_item_name");

    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Get page title
    public String getPageTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText();
    }

    // Get products count
    public int getProductsCount() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(productsList)
        );
        return driver.findElements(productsList).size();
    }

    // Sort products
    public void sortProducts(String option) {
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(sortDropdown)
        );

        Select sort = new Select(dropdown);
        sort.selectByValue(option);
    }

    // Get product elements
    public List<WebElement> getProducts() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(productNames)
        );
        return driver.findElements(productNames);
    }

    // Get product names
    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();

        for (WebElement product : getProducts()) {
            names.add(product.getText());
        }

        return names;
    }
    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<>();

        List<WebElement> priceElements =
                driver.findElements(By.className("inventory_item_price"));

        for (WebElement price : priceElements) {
            String priceText = price.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }

        return prices;
    }
    // Add first product to cart

    public void addFirstProductToCart() {

        By firstProduct = By.className("inventory_item");

        By addToCartButton = By.xpath(
                "(//div[@class='inventory_item']//button[contains(@class,'btn_inventory')])[1]"
        );

        WebElement product = wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstProduct)
        );

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(addToCartButton)
        );

        button.click();

        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.cssSelector(".shopping_cart_badge"),
                        "1"
                )
        );

        System.out.println("Cart badge text: " +
                driver.findElement(By.cssSelector(".shopping_cart_badge")).getText());


    }
}