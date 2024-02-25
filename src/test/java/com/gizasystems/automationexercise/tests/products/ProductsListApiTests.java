package com.gizasystems.automationexercise.tests.products;

import com.gizasystems.automationexercise.apis.Apis;
import com.gizasystems.automationexercise.apis.ApisProducts;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Products Management")
@Story("Product listing")
public class ProductsListApiTests {
    private SHAFT.API api;
    private SHAFT.TestData.JSON testData;

    @Test(description = "Get all products list - API")
    @Description("Given that I am a guest user And I am at Home Page When I click on Products tab Then all products should be listed ")
    public void getAllProductsListApiTest() {
        new ApisProducts(api)
                .getAllProductList()
                .validateAllProductsAreListed(testData.getTestData("productName"));
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {

        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/ProductsList.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        api = new SHAFT.API(Apis.ApisBaseUrl);
    }
}
