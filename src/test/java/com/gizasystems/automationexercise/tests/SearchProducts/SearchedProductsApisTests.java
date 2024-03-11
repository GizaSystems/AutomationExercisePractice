package com.gizasystems.automationexercise.tests.SearchProducts;

import com.gizasystems.automationexercise.apis.Apis;
import com.gizasystems.automationexercise.apis.ApisBrands;
import com.gizasystems.automationexercise.apis.ApisProducts;
import com.shaft.driver.SHAFT;
import jdk.jfr.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchedProductsApisTests {
    private SHAFT.API api;
    private SHAFT.TestData.JSON testData;

    @Test(description = "Search Product Test - API")
    @Description("Search for Product Using Api")
    public void SearchProductApi() {
        new ApisProducts(api).searchProductApi(testData.getTestData("searchProductFiled"), testData.getTestData("SearchedProduct"))
                .validateOnCategory(testData.getTestData("category"));

    }

    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/SearchProductApi.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        api = new SHAFT.API(Apis.ApisBaseUrl);
    }
}
