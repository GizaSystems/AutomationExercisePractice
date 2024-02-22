package com.gizasystems.automationexercise.tests.test;

import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Cart Module")
@Story("Cart Operations")
public class ProductQuantityTests {
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    @Test(description = "Verify Product Exact Quantity Added To Cart")
    public void verifyProductQuantity() {
        new ProductQuantityPage(driver)
                .clickOnViewProduct()
                .verifyOnProductDetails()
                .increaseQuantityInCart(testData.getTestData("DisplayedQuantity"))
                .addProductToCart()
                .clickOnCart()
                .verifyExactQuantityAddedToCart(testData.getTestData("DisplayedQuantity"));
    }

    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/Cart.json");
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