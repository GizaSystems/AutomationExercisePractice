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
    public ApisSearchProducts validateOnCategory() {
        for (int i = 0; i < 10; i++) {
            String category = HtmlResponse.getResponseJSONValue("products[" + i + "].category.category");
            SHAFT.Validations.assertThat()
                    .object(category)
                    .contains("Top")
                    .perform();
        }
        return this;
    }
}
