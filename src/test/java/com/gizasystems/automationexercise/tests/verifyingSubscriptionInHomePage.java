package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

@Epic("Automation Exercise")
@Feature("Home page verification")
@Story("Verify Subscription in home page")

public class verifyingSubscriptionInHomePage {
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testUser;
    private String timeStamp = String.valueOf(System.currentTimeMillis());

    @Test(description = "Verify Subscription is on Home Page")
    public void VerifySubscriptionInHomePage() {
        new Footer(driver).verifySubscriptionText(testUser.getTestData("subText")).enterSubscriptionEmail(testUser.getTestData("UserMail.GuiTimeStamp") + timeStamp + "@gizasystems.com").clickOnSubscribeButton().validateOnSuccessMessageOfSubscriptionEmail(testUser.getTestData("successSubscriptionMessage"));
    }

    @BeforeClass
    public void beforeClass() {
        testUser = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/Footer.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver).navigate().validateOnVisibilityOfHomePage();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
