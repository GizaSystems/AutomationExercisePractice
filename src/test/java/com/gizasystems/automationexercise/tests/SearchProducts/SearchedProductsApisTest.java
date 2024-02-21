package com.gizasystems.automationexercise.tests.SearchProducts;

import com.gizasystems.automationexercise.apis.Apis;
import com.gizasystems.automationexercise.apis.ApisSearchProducts;
import com.shaft.driver.SHAFT;
import jdk.jfr.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchedProductsApisTest {

    private SHAFT.API api;
    private SHAFT.TestData.JSON testData;


    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/SearchProductApi.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        api = new SHAFT.API(Apis.ApisBaseUrl);
    }

    @Test(description = "Search Product Test - API")
    @Description("Search for Product Using Api")
    public void SearchProductApi() {
        new ApisSearchProducts(api).searchProductApi(testData.getTestData("searchProductFiled"), testData.getTestData("SearchedProduct"))
                .validateOnResponseCode()
                .validateOnCategory();


    }
}
