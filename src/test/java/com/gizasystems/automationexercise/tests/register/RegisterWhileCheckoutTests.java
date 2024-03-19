package com.gizasystems.automationexercise.tests.register;

import com.gizasystems.automationexercise.apis.Apis;
import com.gizasystems.automationexercise.pages.CartPage;
import com.gizasystems.automationexercise.pages.HomePage;
import com.gizasystems.automationexercise.pages.RecommendedSection;
import com.gizasystems.automationexercise.pages.RegisterWhileCheckoutPage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@TmsLink("")
@Epic("")
@Feature("")
@Story("")
public class RegisterWhileCheckoutTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.API api;
    private SHAFT.TestData.JSON testData;
    private String timeStamp;

    // Test Cases
    @Test(description = "Place order : Register user While Checkout")
    @Description("Given I open Automation Exercise home," +
            " When I navigate to Recommended Products," +
            " And I add Product to Cart," +
            " And I click on Cart button ," +
            " And I proceed to checkout," +
            " And I click on Register /Login button " +
            ", And I Fill all data and Create account," +
            " And I Verify Account is created, " +
            "And I Verify ' Logged in as username' at top," +
            " And I .Click 'Cart' button, " +
            "And I Click 'Proceed To Checkout' button," +
            " And I Verify Address Details and Review Your Order," +
            " And I Enter description in comment text area and click 'Place Order' ," +
            " And I Enter payment details: Name on Card, Card Number, CVC, Expiration date," +
            " And I Click 'Pay and Confirm Order' button," +
            " And I Verify success message 'Your order has been placed successfully!' ," +
            " And I Click 'Delete Account' button," +
            " And I Verify 'ACCOUNT DELETED!' " +
            "And click 'Continue' button")
    public void registerWhileCheckoutTest() {
        new RecommendedSection(driver)
                .openRecommendedSection()
                .verifyRecommendedSectionVisibility()
                .addToCart(testData.getTestData("ProductDetails.productName"))
                .verifyProductAddedToCart(testData.getTestData("Messages.ProductAddedMessage"));
        new CartPage(driver)
                .openCart()
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(testData.getTestData("ProductDetails.productName"))
                .proceedToCheckOut();
        new RegisterWhileCheckoutPage(driver)
                .verifyCheckoutPopUpDisplayed(testData.getTestData("Messages.CheckoutBodyMessage"))
                .clickOnRegisterLoginBtn();
    }

    //////////////////// Configurations \\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("RegisterWhileCheckoutData.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        api = new SHAFT.API(Apis.ApisBaseUrl);
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
        timeStamp = String.valueOf(System.currentTimeMillis());
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
