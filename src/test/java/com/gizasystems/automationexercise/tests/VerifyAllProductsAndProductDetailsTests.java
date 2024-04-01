package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Products")
@Story("Verify All Products and Product Details")
public class VerifyAllProductsAndProductDetailsTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    // Test Cases
    @TmsLink("55512367")
    @Test(description = "Verify The Product and the Details on ProductDetails Page")
    @Description("Given I open Automation Exercise home, When I open Products, And I pick a Product, Then I am able to check the Product Details")
    public void verifyAllProductsAndProductDetailPage() {
        new NavigationBarPage(driver)
                .clickOnProductsLink();
        new ProductsPage(driver)
                .verifyProductPageTitleVisibility()
                .pickProduct(testData.getTestData("productName"));
        new ProductDetailsPage(driver)
                .verifyProductDetailsPageVisibility()
                .verifyProductDetails(testData.getTestData("productName"), testData.getTestData("productCategory"),
                        testData.getTestData("productPrice"), testData.getTestData("productAvailability"),
                        testData.getTestData("productCondition"), testData.getTestData("productBrand"));
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("VerifyAllProductsAndProductDetailsTestsTestData.json");
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
