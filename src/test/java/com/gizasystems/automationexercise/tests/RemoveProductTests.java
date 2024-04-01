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
@Story("Remove Products From Cart")
public class RemoveProductTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    // Test Cases
    @TmsLink("55512450")
    @Test(description = "Verify removing products from the cart functionality")
    @Description("Given I open Automation Exercise home, When I add products to the cart and click the 'Cart' button, Then I verify that the cart page is displayed, When I click the 'X' button corresponding to a particular product, Then I verify that the product is removed from the cart")
    public void verifyProductRemovedFromCart() {
        new ProductsPage(driver)
                .navigate()
                .addProductToCart(testData.getTestData("productName"));
        new CartPage(driver)
                .openCart()
                .verifyCartPageIsLoaded()
                .clickToRemoveProduct(testData.getTestData("productName"))
                .validateOnRemovedProduct(testData.getTestData("productName"));
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("RemoveProductTestsTestData.json");
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
