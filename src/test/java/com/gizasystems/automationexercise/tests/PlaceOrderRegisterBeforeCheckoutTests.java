package com.gizasystems.automationexercise.tests;

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
@Feature("Placing Order")
@Story("Place Order : Register Before Checkout")
public class PlaceOrderRegisterBeforeCheckoutTests {
    private SHAFT.API api;
    private SHAFT.TestData.JSON testData;
    private String timeStamp = String.valueOf(System.currentTimeMillis());
    private SHAFT.GUI.WebDriver driver;

    @Test(description = "Place Order: Register before Checkout")
    @Description("Place Order: Register before Checkout")
    @TmsLink("55512432")
    public void PlaceOrderRegisterBeforeCheckout() {
        String email = testData.getTestData("UserMail.ApiTimeStamp") + timeStamp + "@gizasystems.com";

        new ApisAccountManagement(api)
                .createRegisterUserAccount(testData.getTestData("UserName"), email, testData.getTestData("UserPassword"), testData.getTestData("UserFirstName"), testData.getTestData("UserLastName"));
        new NavigationBar(driver)
                .clickOnSignupLoginLink();
        new SignupLoginPage(driver)
                .validateOnSignUpVisibility(testData.getTestData("Messages.Signup"))
                .registeredUserLogin(email, testData.getTestData("UserPassword"));
        new ProductsPage(driver)
                .navigate()
                .addProductToCart(testData.getTestData("productName"))
                .clickOnContinueButton()
                .clickCartButton();
        new CartPage(driver)
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(testData.getTestData("productName"))
                .proceedToCheckOut();
        new CheckOutPage(driver)
                .navigate()
                .verifyingAddressDetails(testData.getTestData("UserDetailsName"), testData.getTestData("UserAddress1"), testData.getTestData("UserCountry"))
                .enteringDescriptionInCommentArea("Place Order");
        new PaymentPage(driver)
                .navigate()
                .enterPaymentDetails(testData.getTestData("cardName"), testData.getTestData("cardNumber"), testData.getTestData("cvc"), testData.getTestData("cardExpMonth"), testData.getTestData("cardExpYear"))
                .clickOnPayOrder()
                .verifyOrderPlacementSuccessMessage(testData.getTestData("Messages.successMessageOfOrder"));
        new ApisAccountManagement(api)
                .deleteUserAccount(email, testData.getTestData("UserPassword"))
                .validateDeleteUser();
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\

    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/PlaceOrderTestData.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        timeStamp = String.valueOf(System.currentTimeMillis());
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