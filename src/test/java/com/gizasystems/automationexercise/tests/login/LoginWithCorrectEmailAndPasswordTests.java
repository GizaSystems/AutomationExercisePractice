package com.gizasystems.automationexercise.tests.login;

import com.gizasystems.automationexercise.apis.Apis;
import com.gizasystems.automationexercise.apis.ApisAccountManagement;
import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginWithCorrectEmailAndPasswordTests {
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.API api;
    private SHAFT.TestData.JSON testData;
    private String timeStamp = String.valueOf(System.currentTimeMillis());

    @Test(description = "Login With Correct Email and Password Test - Register and Delete user with APIs ")
    @Description("Given that I am a registered user, When I enter correct email, And Correct Password , And I Clicked on Login button And I clicked on delete user, Then I should be Logged in successfully to the system, And user be deleted from the system")
    public void LoginWithCorrectEmailAndPassword() {
        new ApisAccountManagement(api)
                .createRegisterUserAccount(testData.getTestData("UserName"), testData.getTestData("UserMail.ApiTimeStamp") + timeStamp + "@gizasystems.com", testData.getTestData("UserPassword"), testData.getTestData("UserFirstName"), testData.getTestData("UserLastName"))
                .validateUserCreatedRegistered();
        new NavigationBar(driver)
                .clickOnSignupLoginLink();
        new SignupLoginPage(driver)
                .validateOnLoginVisibility(testData.getTestData("Messages.Login"))
                .registeredUserLogin(testData.getTestData("UserMail.ApiTimeStamp")+ timeStamp + "@gizasystems.com",testData.getTestData("UserPassword"));
        new NavigationBar(driver)
                .validateTheLoggedInUser(testData.getTestData("UserName"));
        new ApisAccountManagement(api)
                .deleteUserAccount(testData.getTestData("UserMail.ApiTimeStamp")+ timeStamp + "@gizasystems.com",testData.getTestData("UserPassword"))
                .validateDeleteUser()
                .validateUserNotFound(testData.getTestData("UserMail.ApiTimeStamp")+ timeStamp + "@gizasystems.com");
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/LoginUser.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        api = new SHAFT.API(Apis.ApisBaseUrl);
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
