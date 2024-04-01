package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Cart Module")
@Story("Cart Operations")
public class CartTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;
    private String timeStamp = String.valueOf(System.currentTimeMillis());

    // Test Cases
    @TmsLink("55512390")
    @Test(description = "Subscription By email")
    @Description("Given that I Want to subscribe, When I enter valid Email, Then I should be Subscribed ")
    public void subscriptionCartPage() {
        new NavigationBarPage(driver)
                .navigateToCartPage();
        new CartPage(driver)
                .validateOnVisibilityOfSubscriptionText()
                .enterSubscriptionEmail(testData.getTestData("EmailGuiTimeStamp") + timeStamp + "@gizasystems.com")
                .clickOnSubscribeButton()
                .validateOnSuccessMessageOfSubscriptionEmail(testData.getTestData("successSubscriptionMessage"));
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("CartTestsTestData.json");
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