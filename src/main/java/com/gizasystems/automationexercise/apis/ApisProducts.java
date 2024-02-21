package com.gizasystems.automationexercise.apis;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import com.shaft.validation.*;
import io.restassured.response.Response;

public class ApisProducts {
    private SHAFT.API api;

    public ApisProducts(SHAFT.API api) {
        this.api = api;
    }

    // Services
    private static final String getAllProductsList_serviceName = "/productsList";

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("API Get All Products List")
    public ApisProducts getAllProductList() {
        api.get(getAllProductsList_serviceName)
                .setContentType(ContentType.URLENC)
                .setTargetStatusCode(Apis.SUCCESS)
                .perform();

        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate All products are listed")
    public ApisProducts validateAllProductsAreListed(String expectedResponseFilePath) {
        Response actualResponse = api.getResponse();
        Validations.assertThat().response(actualResponse).isEqualToFileContentIgnoringOrder(expectedResponseFilePath)
                .perform();

        return this;
    }

}