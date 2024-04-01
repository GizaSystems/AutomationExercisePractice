package com.gizasystems.automationexercise.tests.SearchProducts;

import com.gizasystems.automationexercise.apis.Apis;
import com.gizasystems.automationexercise.apis.ApisProducts;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import jdk.jfr.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Product Management")
@Story("Retrieve All Products List")
public class SearchedProductsApisTests {
    // Variables
    private SHAFT.API api;
    private SHAFT.TestData.JSON testData;

    // Test Cases
    @TmsLink("55512372")
    @Test(description = "Verify getting all products list - API")
    @Description("Given the API endpoint 'https://automationexercise.com/api/productsList' is available, When I make a GET request to the endpoint, Then I verify that the response code is 200, And I verify that the response JSON contains the list of all products.")
    public void searchProductApi() {
        new ApisProducts(api)
                .searchProductApi(testData.getTestData("SearchedProduct"))
                .validateOnCategory(testData.getTestData("category"));
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("SearchedProductsApisTestsTestData.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        api = new SHAFT.API(Apis.apisBaseUrl);
    }
}
