package com.gizasystems.automationexercise.apis;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import java.util.Arrays;
import java.util.List;

public class ApisAccountManagement {
    private SHAFT.API api;

    public ApisAccountManagement(SHAFT.API api) {
        this.api = api;
    }

    // Services
    private static final String createAccount_serviceName = "/createAccount";
    private static final String deleteAccount_serviceName = "/deleteAccount";
    private static final String getUserDetailByEmail_serviceName = "/getUserDetailByEmail";


    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("API Create/Register User Account")
    public ApisAccountManagement createRegisterUserAccount(String username, String email, String pass, String firstName, String lastName) {
        List<List<Object>> formData = Arrays.asList(
                Arrays.asList("name", username),
                Arrays.asList("email", email),
                Arrays.asList("password", pass),
                Arrays.asList("title", "Mr."),
                Arrays.asList("birth_date", "04"),
                Arrays.asList("birth_month", "sep"),
                Arrays.asList("birth_year", "1994"),
                Arrays.asList("firstname", firstName),
                Arrays.asList("lastname", lastName),
                Arrays.asList("company", "company"),
                Arrays.asList("address1", "address1"),
                Arrays.asList("address2", "address2"),
                Arrays.asList("country", "India"),
                Arrays.asList("zipcode", "0000"),
                Arrays.asList("state", "state"),
                Arrays.asList("city", "city"),
                Arrays.asList("mobile_number", "01111111")
        );

        api.post(createAccount_serviceName)
                .setParameters(formData, RestActions.ParametersType.FORM)
                .setContentType(ContentType.URLENC)
                .setTargetStatusCode(Apis.SUCCESS)
                .perform();

        return this;
    }

    @Step("API Create/Register User Account")
    public ApisAccountManagement deleteUserAccount(String email, String pass) {
        List<List<Object>> formData = Arrays.asList(
                Arrays.asList("email", email),
                Arrays.asList("password", pass));
        api.delete(deleteAccount_serviceName)
                .setParameters(formData, RestActions.ParametersType.FORM)
                .setContentType(ContentType.URLENC)
                .setTargetStatusCode(Apis.SUCCESS)
                .perform();
        return this;
    }

    @Step("API Get UserDetailByEmail")
    public ApisAccountManagement getUserDetailByEmail(String email) {
        List<List<Object>> queryParam = Arrays.asList(
                Arrays.asList("email", email));
        api.get(getUserDetailByEmail_serviceName)
                .setParameters(queryParam, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.URLENC)
                .setTargetStatusCode(Apis.SUCCESS)
                .perform();
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate User Created/Registered")
    public ApisAccountManagement validateUserCreatedRegistered() {
//        api.verifyThatResponse().extractedJsonValue("message").contains(expectedMessage).perform();
        api.verifyThatResponse().body().contains("User created!").perform();
        return this;
    }

    @Step("Validate Account Deleted")
    public ApisAccountManagement validateDeleteUser() {
        api.verifyThatResponse().body().contains("Account deleted!").perform();
        return this;
    }

    @Step("Validate User Not Found in the System")
    public ApisAccountManagement validateUserNotFound(String email) {
        getUserDetailByEmail(email);
        api.verifyThatResponse().body().contains("Account not found with this email, try another email!").perform();
        return this;
    }

}