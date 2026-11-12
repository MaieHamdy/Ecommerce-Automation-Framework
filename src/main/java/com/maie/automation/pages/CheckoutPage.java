
package com.maie.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By checkoutButton = By.id("checkout");
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By completeHeader = By.className("complete-header");
    private By errorMessage = By.cssSelector("[data-test='error']");

    private By overviewProductName =
            By.className("inventory_item_name");

    private By overviewProductPrice =
            By.className("inventory_item_price");

    private By itemTotal =
            By.className("summary_subtotal_label");

    private By tax =
            By.className("summary_tax_label");

    private By total =
            By.className("summary_total_label");

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Click Checkout
    public void clickCheckout() {

        WebElement checkout = wait.until(
                ExpectedConditions.elementToBeClickable(checkoutButton)
        );

        System.out.println("Before click URL: " + driver.getCurrentUrl());
        System.out.println("Checkout button text: " + checkout.getText());
        System.out.println("Checkout button displayed: " + checkout.isDisplayed());
        System.out.println("Checkout button enabled: " + checkout.isEnabled());
        System.out.println("Checkout button HTML: " +
                checkout.getAttribute("outerHTML"));

        checkout.click();

        System.out.println("After click URL: " + driver.getCurrentUrl());

        wait.until(
                ExpectedConditions.urlContains("checkout-step-one.html")
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameField)
        );

        System.out.println("Checkout page opened successfully!");
    }

    // Enter Customer Information
    public void enterCustomerInformation(
            String firstName,
            String lastName,
            String postalCode) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameField)
        ).sendKeys(firstName);

        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    // Continue to Overview or display validation error
    public void clickContinue() {

        wait.until(
                ExpectedConditions.elementToBeClickable(continueButton)
        ).click();

        wait.until(
                driver -> !driver.findElements(errorMessage).isEmpty()
                        || driver.getCurrentUrl().contains("checkout-step-two.html")
        );
    }

    // Finish Order
    public void clickFinish() {

        wait.until(
                ExpectedConditions.elementToBeClickable(finishButton)
        ).click();

        wait.until(
                ExpectedConditions.urlContains("checkout-complete.html")
        );
    }

    // Get Confirmation Message
    public String getConfirmationMessage() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(completeHeader)
        ).getText();
    }

    // Get Checkout Error Message
    public String getErrorMessage() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).getText();
    }

    // Get Overview Product Name
    public String getOverviewProductName() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(overviewProductName)
        ).getText();
    }

    // Get Overview Product Price
    public String getOverviewProductPrice() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(overviewProductPrice)
        ).getText();
    }

    // Get Item Total
    public String getItemTotal() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(itemTotal)
        ).getText();
    }

    // Get Tax
    public String getTax() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(tax)
        ).getText();
    }

    // Get Total
    public String getTotal() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(total)
        ).getText();
    }
}
