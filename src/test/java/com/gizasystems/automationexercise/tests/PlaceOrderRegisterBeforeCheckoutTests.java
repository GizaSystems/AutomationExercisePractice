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
@Feature("Order Placement")
@Story("Place Order After Registering")
public class PlaceOrderRegisterBeforeCheckoutTests {
    // Variables
    private SHAFT.API api;
    private SHAFT.TestData.JSON testData;
    private String timeStamp;
    private SHAFT.GUI.WebDriver driver;

    // Test Cases
    @TmsLink("55512432")
    @Test(description = "Verify the order placement process with registration before checkout")
    @Description("Given I open Automation Exercise home, When I click 'Signup / Login' and fill in all details to create an account, Then I verify 'ACCOUNT CREATED!' and click 'Continue', And verify 'Logged in as username' at the top, When I add products to the cart and proceed to checkout, Then I verify that the cart page is displayed, And I click 'Proceed To Checkout', Then I verify Address Details and Review Your Order, When I enter a description in the comment text area and click 'Place Order', And enter payment details: Name on Card, Card Number, CVC, Expiration date, Then I click 'Pay and Confirm Order' and verify the success message 'Your order has been placed successfully!', When I click 'Delete Account', Then I verify 'ACCOUNT DELETED!' and click 'Continue'")
    public void placeOrderRegisterBeforeCheckoutGUI() {
        new NavigationBarPage(driver)
                .clickOnSignupLoginLink();
        new SignupLoginPage(driver)
                .validateOnSignUpVisibility(testData.getTestData("Messages.Signup"))
                .newUserSignup(testData.getTestData("UserName"), testData.getTestData("UserMail.Gui") + timeStamp + "@gizasystems.com");
        new SignupPage(driver)
                .validateOnAccountInfoPage(testData.getTestData("Messages.AccountInfo"))
                .enterAccountInformation(testData.getTestData("Gender"), testData.getTestData("UserPassword"), testData.getTestData("UserFirstName"), testData.getTestData("UserLastName"), testData.getTestData("UserBirthDay"), testData.getTestData("UserBirthMonth"), testData.getTestData("UserBirthYear"))
                .enterAddressInformation(testData.getTestData("UserAddress1"), testData.getTestData("UserCountry"), testData.getTestData("UserState"), testData.getTestData("UserCity"), testData.getTestData("UserZipCode"), testData.getTestData("UserMobile"))
                .validateOnAccountCreated(testData.getTestData("Messages.AccountCreated"))
                .clickOnContinueButton();
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
                .fillPaymentInformation(testData.getTestData("cardName"),testData.getTestData("cardNumber"),testData.getTestData("cvc"),testData.getTestData("cardExpMonth"),testData.getTestData("cardExpYear"))
                .clickOnPayAndConfirmBtn()
                .verifySuccessMessage(testData.getTestData("Messages.successMessageOfOrder"));
        new NavigationBarPage(driver)
                .validateTheLoggedInUser(testData.getTestData("UserName"))
                .clickOnDeleteAccountLink();
        new DeleteAccountPage(driver)
                .validateAccountDeleted(testData.getTestData("Messages.AccountDeleted"));
    }

    @TmsLink("55512432")
    @Test(description = "Place Order: Register Using APIs before Checkout ")
    @Description("Given I have created a new user account via API and navigate to the Automation Exercise homepage, When I login with the newly registered user, add a product to the cart, and proceed to checkout, Then I verify the cart page is loaded and the product is added. After proceeding to checkout, I verify address details, enter a description in the comment area, and fill in payment information. Upon confirming the payment, I verify the success message for order placement. Finally, I delete the user account via API to ensure cleanup.")
    public void placeOrderRegisterBeforeCheckoutAPI() {
        new ApisAccountManagement(api)
                .createRegisterUserAccount(testData.getTestData("UserName"), testData.getTestData("UserMail.ApiTimeStamp") + timeStamp + "@gizasystems.com", testData.getTestData("UserPassword"), testData.getTestData("UserFirstName"), testData.getTestData("UserLastName"));
        new NavigationBarPage(driver)
                .clickOnSignupLoginLink();
        new SignupLoginPage(driver)
                .validateOnSignUpVisibility(testData.getTestData("Messages.Signup"))
                .registeredUserLogin(testData.getTestData("UserMail.ApiTimeStamp") + timeStamp + "@gizasystems.com", testData.getTestData("UserPassword"));
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
                .verifyingAddressDetails(testData.getTestData("UserDetailsNameAPI"), testData.getTestData("UserAddressAPI"), testData.getTestData("UserCountry"))
                .enteringDescriptionInCommentArea("Place Order");
        new PaymentPage(driver)
                .navigate()
                .fillPaymentInformation(testData.getTestData("cardName"),testData.getTestData("cardNumber"),testData.getTestData("cvc"),testData.getTestData("cardExpMonth"),testData.getTestData("cardExpYear"))
                .clickOnPayAndConfirmBtn()
                .verifySuccessMessage(testData.getTestData("Messages.successMessageOfOrder"));
        new ApisAccountManagement(api)
                .deleteUserAccount(testData.getTestData("UserMail.ApiTimeStamp") + timeStamp + "@gizasystems.com", testData.getTestData("UserPassword"))
                .validateDeleteUser();
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("PlaceOrderRegisterBeforeCheckoutTestsTestData.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        timeStamp = String.valueOf(System.currentTimeMillis());
        api = new SHAFT.API(Apis.apisBaseUrl);
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