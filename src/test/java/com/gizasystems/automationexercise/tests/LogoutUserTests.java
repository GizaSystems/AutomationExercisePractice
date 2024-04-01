package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.pages.HomePage;
import com.gizasystems.automationexercise.pages.LogoutUserPage;
import com.gizasystems.automationexercise.pages.NavigationBarPage;
import com.gizasystems.automationexercise.pages.SignupLoginPage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("User Management")
@Story("Logout User")
public class LogoutUserTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    // Test Cases
    @TmsLink("55512326")
    @Test(description = "Verify user logout functionality")
    @Description("Given I open Automation Exercise home, When I click on the 'Signup / Login' button, Then I verify 'Login to your account' is visible, When I enter the correct email address and password and click the 'login' button, Then I verify that 'Logged in as username' is visible, When I click the 'Logout' button, Then I verify that the user is navigated to the login page")
    public void successfulLogOut() {
        new LogoutUserPage(driver).logOut();
        new SignupLoginPage(driver).validateOnLoginVisibility(testData.getTestData("LoginMessage"));
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("LogoutUserTestsTest Data.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
        new NavigationBarPage(driver)
                .clickOnSignupLoginLink();
        new SignupLoginPage(driver)
                .registeredUserLogin(testData.getTestData("UserEMail"), testData.getTestData("UserPassWD"));
        new NavigationBarPage(driver)
                .validateTheLoggedInUser(testData.getTestData("username"));
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
