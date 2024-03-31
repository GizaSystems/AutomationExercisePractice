package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Cart Management")
@Story("Verify Product Quantity in Cart")
public class ProductQuantityTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    // Test Cases
    @TmsLink("55512405")
    @Test(description = "Verify Product Exact Quantity Added To Cart")
    @Description("Given I open Automation Exercise home, When I click 'View Product' for any product on the home page, Then I verify that the product detail page is opened, When I increase the product quantity to 4 and click 'Add to cart', And then click 'View Cart', Then I verify that the product is displayed in the cart page with the exact quantity of 4")
    public void verifyProductQuantity() {
        new ProductQuantityPage(driver)
                .clickOnViewProduct()
                .verifyOnProductDetails()
                .increaseQuantityInCart(testData.getTestData("DisplayedQuantity"))
                .addProductToCart()
                .clickOnCart()
                .refreshCartPage()
                .verifyExactQuantityAddedToCart(testData.getTestData("DisplayedQuantity"));
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("ProductQuantityTestsTestData.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}