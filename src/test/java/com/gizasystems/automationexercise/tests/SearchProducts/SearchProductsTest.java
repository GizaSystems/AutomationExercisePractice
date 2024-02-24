package com.gizasystems.automationexercise.tests.SearchProducts;

import com.gizasystems.automationexercise.pages.HomePage;
import com.gizasystems.automationexercise.pages.NavigationBar;
import com.gizasystems.automationexercise.pages.ProductsPage;
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
@Story("Search Product")
public class SearchProductsTest {
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/SearchProduct.json");
    }

    @BeforeMethod
    public void beforeMethod() {

        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
    }

    @Test(description = "Search Product")
    public void searchProduct() {

        new NavigationBar(driver).navigateToProductsPage();
        new ProductsPage(driver).validateOnallProductPage()
                                .searchForProduct(testData.getTestData("SearchedProduct"))
                                .validateOnsearchedProducts()
                                .validateOnProductsRelatedToSearch(testData.getTestData("SearchResult"));
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
