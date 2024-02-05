package com.gizasystems.automationexercise.tests.SearchProducts;

import com.gizasystems.automationexercise.apis.Apis;
import com.gizasystems.automationexercise.pages.HomePage;
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
    private SHAFT.API api;
    private SHAFT.TestData.JSON testData;



    @BeforeMethod
    public void beforeMethod() {
       // api = new SHAFT.API(Apis.ApisBaseUrl);
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
    }
    @Test(description = "Search Product")
    public void searchProduct(){

        new HomePage(driver).navigateToProductsPage()
                .validateOnallProductPage()
                .searchForProduct()
                .validateOnsearchedProducts()
                .validateOnProductsRelatedToSearch();



    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
