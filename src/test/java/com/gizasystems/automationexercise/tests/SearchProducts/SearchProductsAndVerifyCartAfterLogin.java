package com.gizasystems.automationexercise.tests.SearchProducts;


import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Product Search and Cart Verification After Login")
@Story("Search Products and Verify Cart After Login")
public class SearchProductsAndVerifyCartAfterLogin {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;
    //Testcases
    @TmsLink("55512478")
    @Test(description = "Search Products and Verify Cart After Login")
    public void SearchProductsAndVerifyCart() {

        new NavigationBar(driver)
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
        new NavigationBar(driver)
                .clickOnSignupLoginLink();
        new SignupLoginPage(driver)
                .validateOnLoginVisibility(testData.getTestData("Messages.Login"))
                .registeredUserLogin(testData.getTestData("RegisteredUserEmail"), testData.getTestData("RegisteredUserPassword"));
        new NavigationBar(driver)
                .validateTheLoggedInUser(testData.getTestData("RegisteredUserUsername"));
        new CartPage(driver)
                .navigate()
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(testData.getTestData("SecondProduct.productDescription"));
    }

    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/SearchProductsAndVerifyCartAfterLogin.json");
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


