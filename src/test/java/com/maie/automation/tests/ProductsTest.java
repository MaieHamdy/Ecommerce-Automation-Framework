package com.maie.automation.tests;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.maie.automation.base.BaseTest;
import com.maie.automation.pages.LoginPage;
import com.maie.automation.pages.ProductsPage;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.AllureTestNg;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

@Listeners(AllureTestNg.class)
@Epic("E-Commerce Automation")
@Feature("Products Module")
public class ProductsTest extends BaseTest {

    @Test
    @Story("Products Page Display")
    @Description("Verify that the products page is displayed successfully after login.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyProductsPageIsDisplayed() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);

        Assert.assertEquals(
                productsPage.getPageTitle(),
                "Products",
                "Products page title is incorrect!"
        );
    }

    @Test
    @Story("Products Count")
    @Description("Verify that exactly six products are displayed on the products page.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyProductsCount() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);

        Assert.assertEquals(
                productsPage.getProductsCount(),
                6,
                "Products count is incorrect!"
        );
    }

    @Test
    @Story("Products Sorting A to Z")
    @Description("Verify that products can be sorted alphabetically from A to Z.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyProductsSortingAtoZ() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.sortProducts("az");

        List<String> actualNames = productsPage.getProductNames();

        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);

        Assert.assertEquals(
                actualNames,
                expectedNames,
                "Products are not sorted alphabetically!"
        );
    }

    @Test
    @Story("Products Sorting Z to A")
    @Description("Verify that products can be sorted alphabetically from Z to A.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyProductsSortingZtoA() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.sortProducts("za");

        List<String> actualNames = productsPage.getProductNames();

        List<String> expectedNames = new ArrayList<>(actualNames);
        expectedNames.sort(Collections.reverseOrder());

        Assert.assertEquals(
                actualNames,
                expectedNames,
                "Products are not sorted from Z to A!"
        );
    }

    @Test
    @Story("Products Sorting Price Low to High")
    @Description("Verify that products can be sorted by price from lowest to highest.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyProductsSortingPriceLowToHigh() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.sortProducts("lohi");

        List<Double> actualPrices = productsPage.getProductPrices();

        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        Assert.assertEquals(
                actualPrices,
                expectedPrices,
                "Products are not sorted by price from Low to High!"
        );
    }

    @Test
    @Story("Products Sorting Price High to Low")
    @Description("Verify that products can be sorted by price from highest to lowest.")
    @Severity(SeverityLevel.NORMAL)
    public void verifyProductsSortingPriceHighToLow() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.sortProducts("hilo");

        List<Double> actualPrices = productsPage.getProductPrices();

        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Collections.reverseOrder());

        Assert.assertEquals(
                actualPrices,
                expectedPrices,
                "Products are not sorted by price from High to Low!"
        );
    }
}