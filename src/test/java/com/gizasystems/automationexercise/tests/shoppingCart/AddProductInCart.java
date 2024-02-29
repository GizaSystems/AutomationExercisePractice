package com.gizasystems.automationexercise.tests.shoppingCart;

import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Add  Items To Cart")
@Story("Add  The First Two Items To Cart")
public class AddProductInCart {
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    @Test(description = "Verify Adding Multiple Products to Cart")
    @Description("Given homepage is open, when the user adds the first product to the cart, continues shopping, adds the second product, and views the cart, then both products along with their accurate prices, quantities, and total price should be displayed.")
    public void addProductsToCart() {
        new NavigationBar(driver).
                clickOnProductsLink();
        new ProductsPage(driver)
                .navigate()
                .addProductsToCart(testData.getTestData("FirstProduct.productDescription"))
                .addProductsToCart(testData.getTestData("SecondProduct.productDescription"));


        new CartPage(driver)
                .navigate()
                .validateOnItemsAddedInCart(testData.getTestData("FirstProduct.productDescription"))
                .validateOnItemsAddedInCart(testData.getTestData("SecondProduct.productDescription"))
                .validateOnProductPrices(testData.getTestData("FirstProduct.productDescription"), testData.getTestData("FirstProduct.productPrice"))
                .validateOnProductPrices(testData.getTestData("SecondProduct.productDescription"), testData.getTestData("SecondProduct.productPrice"))
                .validateOnProductQuantity(testData.getTestData("FirstProduct.productDescription"), testData.getTestData("FirstProduct.productQuantity"))
                .validateOnProductQuantity(testData.getTestData("SecondProduct.productDescription"), testData.getTestData("SecondProduct.productQuantity"))
                .validateOnTotalPrice(testData.getTestData("FirstProduct.productDescription"), testData.getTestData("FirstProduct.total"))
                .validateOnTotalPrice(testData.getTestData("SecondProduct.productDescription"), testData.getTestData("SecondProduct.total"));
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/Products.json");
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
