package com.gizasystems.automationexercise.tests.SearchProducts;

import com.gizasystems.automationexercise.apis.Apis;
import com.gizasystems.automationexercise.pages.HomePage;
import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchProductsTest {
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.API api;
    private SHAFT.TestData.JSON testData;

    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/RegisterUser.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        api = new SHAFT.API(Apis.ApisBaseUrl);
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
    }
    @Test(description = "Search Product")
    public void searchProduct(){

        new HomePage(driver).navigate().navigateToProductsPage()
                .validateOnallProductPage()
                .searchForProduct()
                .searchForProduct()
                .validateOnsearchedProducts()
                .validateOnProductsRelatedToSearch();



    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
