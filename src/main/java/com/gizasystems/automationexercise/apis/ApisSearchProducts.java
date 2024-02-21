package com.gizasystems.automationexercise.apis;

import com.gizasystems.automationexercise.utils.HtmlResponse;
import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import java.util.Arrays;
import java.util.List;



public class ApisSearchProducts {
    private SHAFT.API api;

    public ApisSearchProducts(SHAFT.API api) {
        this.api = api;
    }

    // Services
    private static final String searchProduct_serviceName = "/searchProduct";


    @Step("API Search For Product")
    public ApisSearchProducts searchProductApi(String searchProductFiled, String searchedProduct) {
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
    @Step("Validate User Created/Registered")
    public ApisSearchProducts validateOnResponseCode() {
        SHAFT.Validations.assertThat()
                .object(HtmlResponse.getResponseJSONValue("responseCode"))
                .isEqualTo("200")
                .perform();
        return this;
    }

    public ApisSearchProducts validateOnCategory() {
        SHAFT.Validations.assertThat()
                .object(HtmlResponse.getResponseJSONValue("products[0].category.category"))
                .isEqualTo("Tops")
                .perform();
        return this;
    }


}
