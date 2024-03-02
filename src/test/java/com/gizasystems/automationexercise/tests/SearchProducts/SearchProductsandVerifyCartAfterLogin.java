package com.gizasystems.automationexercise.tests.SearchProducts;


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
@Feature("Products Page")
@Story("Search Products and Verify Cart After Login")
public class SearchProductsandVerifyCartAfterLogin {

    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON searchProductTestData;
    private SHAFT.TestData.JSON productsTestData;
    private SHAFT.TestData.JSON LoginUserTestData;


    @Test(description = "Search Products and Verify Cart After Login")
    public void SearchProductsandVerifyCart() {

        new NavigationBar(driver)
                .clickOnProductsLink();
        new ProductsPage(driver)
                .validateOnallProductPage()
                .searchForProduct(searchProductTestData.getTestData("SearchedProduct"))
                .validateOnsearchedProducts()
                .validateOnProductsRelatedToSearch(searchProductTestData.getTestData("SearchResult"))
                .addProductsToCart(productsTestData.getTestData("SecondProduct.productDescription"))
                .ClickOnViewCartpopupLinkButton();
        new CartPage(driver)
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(productsTestData.getTestData("SecondProduct.productDescription"));
        new NavigationBar(driver)
                .clickOnSignupLoginLink();

        new SignupLoginPage(driver)
                .validateOnLoginVisibility(LoginUserTestData.getTestData("Messages.Login"))
                .registeredUserLogin(LoginUserTestData.getTestData("RegisteredUserEmail"), LoginUserTestData.getTestData("RegisteredUserPassword"));

        new NavigationBar(driver)
                .validateTheLoggedInUser(LoginUserTestData.getTestData("RegisteredUserUsername"));
        new CartPage(driver)
                .navigate()
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(productsTestData.getTestData("SecondProduct.productDescription"));

    }

    @BeforeClass
    public void beforeClass() {
        searchProductTestData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/SearchProduct.json");
        productsTestData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/Products.json");
        LoginUserTestData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/LoginUser.json");
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


