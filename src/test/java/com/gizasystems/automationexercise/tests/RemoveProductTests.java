package com.gizasystems.automationexercise.tests;

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
@Feature("Brand Module")
@Story("Brand Products")
public class RemoveProductTests {

    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    @Test(description = "Verify that Product Removed from Cart Successfully")
    public void verifyBrandPageDisplayed() {
        new ProductsPage(driver)
                .navigate()
                .addProductToCart(testData.getTestData("productName"));
        new CartPage(driver)
                .openCart()
                .verifyCartPageIsLoaded()
                .clickToRemoveProduct(testData.getTestData("productName"))
                .validateOnRemovedProduct(testData.getTestData("productName"));
    }

    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/RemoveProductTest.json");
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
