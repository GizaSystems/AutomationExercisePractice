package com.gizasystems.automationexercise.tests.SearchProducts;

import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Product Search and Cart Verification After Login")
@Story("Search Products and Verify Cart After Login")
public class SearchProductsAndVerifyCartAfterLoginTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    // Test Cases
    @TmsLink("55512478")
    @Test(description = "Search Products and Verify Cart After Login")
    @Description("Given I open Automation Exercise home, When I click on the 'Products' button, Then I verify that the user is navigated to the ALL PRODUCTS page successfully, When I enter a product name in the search input and click the search button, Then I verify 'SEARCHED PRODUCTS' is visible, And I verify that all the products related to the search are visible, When I add those products to cart, And click 'Cart' button and verify that products are visible in cart, When I click 'Signup / Login' button and submit login details, And again, go to the Cart page, Then I verify that those products are visible in cart after login as well.")
    public void searchProductsAndVerifyCart() {
        new NavigationBarPage(driver)
                .clickOnProductsLink();
        new ProductsPage(driver)
                .validateOnallProductPage()
                .searchForProduct(testData.getTestData("SearchedProduct"))
                .validateOnsearchedProductsPage()
                .validateOnProductsRelatedToSearch(testData.getTestData("SearchResult"))
                .addProductToCart(testData.getTestData("SecondProduct.productDescription"))
                .clickOnViewCartpopupLinkButton();
        new CartPage(driver)
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(testData.getTestData("SecondProduct.productDescription"));
        new NavigationBarPage(driver)
                .clickOnSignupLoginLink();
        new SignupLoginPage(driver)
                .validateOnLoginVisibility(testData.getTestData("Messages.Login"))
                .registeredUserLogin(testData.getTestData("RegisteredUserEmail"), testData.getTestData("RegisteredUserPassword"));
        new NavigationBarPage(driver)
                .validateTheLoggedInUser(testData.getTestData("RegisteredUserUsername"));
        new CartPage(driver)
                .navigate()
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(testData.getTestData("SecondProduct.productDescription"));
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("SearchProductsAndVerifyCartAfterLoginTestsTestData.json");
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


