package com.gizasystems.automationexercise.apis;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import java.util.Arrays;
import java.util.List;

public class ApisProducts {
    private SHAFT.API api;

    public ApisProducts(SHAFT.API api) {
        this.api = api;
    }

    // Services
    private static final String getAllProductsList_serviceName = "/productsList";
    private static final String searchProduct_serviceName = "/searchProduct";

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("API Get All Products List")
    public ApisProducts getAllProductList() {
        api.get(getAllProductsList_serviceName)
                .setContentType(ContentType.URLENC)
                .setTargetStatusCode(Apis.SUCCESS)
                .perform();
        return this;
    }

    @Step("API Search For Product")
    public ApisProducts searchProductApi(String searchProductFiled, String searchedProduct) {
        List<List<Object>> formData = Arrays.asList(
                Arrays.asList(searchProductFiled, searchedProduct)

        );
        api.post(searchProduct_serviceName)
                .setParameters(formData, RestActions.ParametersType.FORM)
                .setContentType(ContentType.URLENC)
                .setTargetStatusCode(Apis.SUCCESS)
                .perform();
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\

    @Step("Validate the last product name is equal to: {productName} ")
    public ApisProducts validateLastProductName(String productName) {
        api.assertThatResponse()
                .extractedJsonValue("$.products[(@.length-1)].name")
                .isEqualTo(productName)
                .perform();
        return this;
    }

    public ApisProducts validateOnCategory(String category) {
        for (int i = 0; i < 10; i++) {
            api.assertThatResponse().extractedJsonValue("products[" + i + "].category.category").contains(category)
                    .perform();
        }
        return this;
    }


}