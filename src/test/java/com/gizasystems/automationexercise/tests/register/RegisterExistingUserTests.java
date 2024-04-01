package com.gizasystems.automationexercise.tests.register;

import com.gizasystems.automationexercise.apis.Apis;
import com.gizasystems.automationexercise.apis.ApisAccountManagement;
import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("User Management")
@Story("Register")
public class RegisterExistingUserTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.API api;
    private SHAFT.TestData.JSON testData;
    private String timeStamp = String.valueOf(System.currentTimeMillis());

    // Test Cases
    @TmsLink("55512339")
    @Test(description = "Register User with existing Email - GUI")
    @Description("Given that I register with new user, When I enter Existing Email , Then I should not be registered and Error message appeared ")
    public void registerUserWithExistingEmailGui() {
        new ApisAccountManagement(api)
                .createRegisterUserAccount(testData.getTestData("UserName"), testData.getTestData("UserMail.ApiTimeStamp") + timeStamp + "@gizasystems.com", testData.getTestData("UserPassword"), testData.getTestData("UserFirstName"), testData.getTestData("UserLastName"));
        new NavigationBarPage(driver)
                .clickOnSignupLoginLink();
        new SignupLoginPage(driver)
                .validateOnSignUpVisibility(testData.getTestData("Messages.Signup"))
                .newUserSignup(testData.getTestData("UserName"), testData.getTestData("UserMail.ApiTimeStamp") + timeStamp + "@gizasystems.com")
                .validateOnErrorMessageVisibilityOfExistingEmail();
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("RegisterExistingUserTestsTestData.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        api = new SHAFT.API(Apis.apisBaseUrl);
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
