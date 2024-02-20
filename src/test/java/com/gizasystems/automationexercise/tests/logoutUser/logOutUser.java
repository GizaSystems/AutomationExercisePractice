package com.gizasystems.automationexercise.tests.logoutUser;

import com.gizasystems.automationexercise.pages.HomePage;
import com.gizasystems.automationexercise.pages.NavigationBar;
import com.gizasystems.automationexercise.pages.SignupLoginPage;
import com.gizasystems.automationexercise.pages.SignupPage;
import com.shaft.driver.SHAFT;
import groovy.lang.GString;
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

public class logOutUser {


    private SHAFT.GUI.WebDriver driver;

    private SHAFT.TestData.JSON testData;

    private String url = System.getProperty("url");
    @Test
    public void SuccessfulLogOut(){



        new com.gizasystems.automationexercise.pages.LogoutUser(driver).logOut();
        new SignupLoginPage(driver).validateOnLoginVisibility(testData.getTestData("Messages.Login"));
    }


    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/LoginUser.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
        new NavigationBar(driver).clickOnSignupLoginLink();
        new SignupLoginPage(driver).registeredUserLogin("abdelazizaliabdelaziz@gmail.com" ,"karimAhmed");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
