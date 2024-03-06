package com.gizasystems.automationexercise.tests;
import com.gizasystems.automationexercise.pages.HomePage;
import com.gizasystems.automationexercise.pages.LogoutUser;
import com.gizasystems.automationexercise.pages.NavigationBar;
import com.gizasystems.automationexercise.pages.SignupLoginPage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("User Management")
@Story("Logout User")

public class LogoutUserTest {

    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    @Test
    public void SuccessfulLogOut() {
        new LogoutUser(driver).logOut();
        new SignupLoginPage(driver).validateOnLoginVisibility(testData.getTestData("LoginMessage"));
    }

    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/LogoutTestData.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver).navigate().validateOnVisibilityOfHomePage();
        new NavigationBar(driver).clickOnSignupLoginLink();
        new SignupLoginPage(driver).registeredUserLogin(testData.getTestData("UserEMail"), testData.getTestData("UserPassWD"));
        new NavigationBar(driver).validateTheLoggedInUser(testData.getTestData("username"));
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
