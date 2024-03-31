package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Brand Module")
@Story("Brand Products")
public class BrandProductsTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    // Test Cases
    @TmsLink("55512469")
    @Test(description = "Verify that Brand Products are displayed")
    @Description("Given I open Automation Exercise home, When I click on the 'Products' button, Then I verify that Brands are visible on the left side bar, When I click on any brand name, Then I verify that the user is navigated to the brand page and brand products are displayed, When I click on any other brand link on the left side bar, Then I verify that the user is navigated to that brand page and can see the products")
    public void verifyBrandPageDisplayed() {
        new BrandProductsPage(driver)
                .clickOnProducts()
                .verifyBrandsVisible()
                .clickOnBrandName()
                .verifyBrandPageVisible(testData.getTestData("firstBrand"))
                .verifyProductsVisible()
                .clickOnSecondBrandName()
                .verifySecondBrandPageVisible(testData.getTestData("secondBrand"))
                .verifyProductsOfSecondBrandVisible();
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("BrandProductsTestsTestData.json");
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
