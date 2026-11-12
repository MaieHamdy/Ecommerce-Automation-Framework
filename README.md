
# E-Commerce Automation Testing Framework

A Web UI Test Automation Framework built using Java, Selenium WebDriver, TestNG, Maven, and Allure Reports.

This project automates key e-commerce user journeys on [SauceDemo](https://www.saucedemo.com/), following the Page Object Model (POM) design pattern to improve test maintainability and reusability.

## Tech Stack

- **Programming Language:** Java 17
- **Automation Tool:** Selenium WebDriver
- **Test Framework:** TestNG
- **Build Tool:** Maven
- **Reporting:** Allure Reports
- **Design Pattern:** Page Object Model (POM)
- **IDE:** IntelliJ IDEA

## Project Structure

```text
Ecommerce-Automation-Framework
│
├── src
│   └── test
│       └── java
│           └── com.maie.automation
│               ├── base
│               ├── pages
│               └── tests
│
├── pom.xml
├── testng.xml
├── .gitignore
└── README.md
```

## Automated Test Coverage

### 1. Login
- Verify successful login.
- Verify login with invalid credentials.
- Verify login validation.

### 2. Products
- Verify products page is displayed.
- Verify products information.
- Verify product sorting.

### 3. Cart
- Verify product can be added to the cart.
- Verify product name and price in the cart.
- Verify product can be removed from the cart.

### 4. Checkout
- Verify successful checkout.
- Verify checkout validation when First Name is missing.
- Verify checkout validation when Last Name is missing.
- Verify checkout validation when Postal Code is missing.
- Verify checkout overview details.

## Test Execution

### Prerequisites
- Java JDK 17 or higher
- IntelliJ IDEA or another Java IDE
- Maven

### Run Tests

Clone the repository:

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

Navigate to the project directory and run:

```bash
mvn clean test
```

## Test Reports

This framework integrates Allure Reports for test execution reporting.

To generate and view the Allure report, use:

```bash
allure serve allure-results
```

## Test Execution Results

- **Total Test Cases:** 17
- **Passed:** 17
- **Failed:** 0
- **Skipped:** 0

All 17 automated test cases passed successfully in the latest full regression execution.

## Application Under Test

[SauceDemo](https://www.saucedemo.com/)

---

**Author:** Maie Hamdy
