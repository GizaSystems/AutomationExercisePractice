package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

@Epic("Automation Exercise")
@Feature("Home page verification")
@Story("Verify Subscription in home page")
public class VerifyingSubscriptionInHomePageTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;
    private String timeStamp = String.valueOf(System.currentTimeMillis());

    // Test Cases
    @TmsLink("55512381")
    @Test(description = "Verify subscription functionality on the homepage")
    @Description("Given I open Automation Exercise home, When I scroll down to the footer, Then I verify that 'SUBSCRIPTION' text is visible, And When I enter an email address and click the arrow button, Then I verify the success message 'You have been successfully subscribed!' is visible on the screen")
    public void verifySubscriptionInHomePageTest() {
        new FooterPage(driver)
                .verifySubscriptionText(testData.getTestData("subText"))
                .enterSubscriptionEmail(testData.getTestData("UserMail.GuiTimeStamp") + timeStamp + "@gizasystems.com")
                .clickOnSubscribeButton()
                .validateOnSuccessMessageOfSubscriptionEmail(testData.getTestData("successSubscriptionMessage"));
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("VerifyingSubscriptionInHomePageTestsTestData.json");
    }

    @BeforeMethod
    public void beforeMethod() {
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
