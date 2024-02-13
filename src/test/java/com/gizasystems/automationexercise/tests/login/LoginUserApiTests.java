package com.gizasystems.automationexercise.tests.login;
import com.gizasystems.automationexercise.apis.Apis;
import com.gizasystems.automationexercise.apis.ApisAccountManagement;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("User Management")
@Story("Login")
public class LoginUserApiTests {
    private SHAFT.API api;
    private SHAFT.TestData.JSON testData;
    private String userEmail;

    @Test(description = "Login User Test - API - Time Stamp")
    @Description("Given that I am a registered user, When I click in Signup/Login button, And I entered valid email and valid password, And I clicked on Login button, Then I should be logged in successfully to the system")
    public void loginUserTestApi() {
        new ApisAccountManagement(api)
                .createRegisterUserAccount(testData.getTestData("UserName"), userEmail, testData.getTestData("UserPassword"), testData.getTestData("UserFirstName"), testData.getTestData("UserLastName"))
                .validateUserCreatedRegistered()
                .logIntoUserAccount(userEmail, testData.getTestData("UserPassword"))
                .validateUserLoggedIn()
                .deleteUserAccount(userEmail, testData.getTestData("UserPassword"))
                .validateDeleteUser()
                .validateUserNotFound(userEmail);
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/RegisterUser.json");
        userEmail=testData.getTestData("UserMail") + System.currentTimeMillis()  + "@gizasystems.com";
    }

    @BeforeMethod
    public void beforeMethod() {
        api = new SHAFT.API(Apis.ApisBaseUrl);
    }

}
