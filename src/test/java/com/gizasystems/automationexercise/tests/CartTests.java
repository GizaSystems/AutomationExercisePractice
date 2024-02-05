package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Cart Module")
@Story("Cart Operations")
public class CartTests {
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testUserData,testCartData;

    private String timeStamp = String.valueOf(System.currentTimeMillis());


    @Test(description = "Subscription By email")
    @Description("Given that I Want to subscribe, When I enter valid Email, Then I should be Subscribed ")
    public void subscriptionCartPage() {
        new HomePage(driver).navigateToCartPage();
        new CartPage(driver).validateOnVisibilityOfSubscriptionText()
                .enterSubscriptionEmail(testUserData.getTestData("UserMail.GuiTimeStamp") + timeStamp + "@gizasystems.com")
                .clickOnSubscribeButton()
                .ValidateOnSuccessMessageOfSubscriptionEmail(testCartData.getTestData("successSubscriptionMessage"));

    }
    @BeforeClass
    public void beforeClass() {
        testUserData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/RegisterUser.json");
        testCartData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/Cart.json");
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

