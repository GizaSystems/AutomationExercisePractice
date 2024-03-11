package com.gizasystems.automationexercise.apis;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;

public class ApisBrands {
    private SHAFT.API api;

    public ApisBrands(SHAFT.API api) {
        this.api = api;
    }

    // Services
    private static final String brandsList_serviceName = "/brandsList";

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\

    @Step("API Get All Brands List")
    public ApisBrands getAllBrandsList() {
        api.get(brandsList_serviceName)
                .setTargetStatusCode(Apis.SUCCESS)
                .perform();
        return this;
    }
}