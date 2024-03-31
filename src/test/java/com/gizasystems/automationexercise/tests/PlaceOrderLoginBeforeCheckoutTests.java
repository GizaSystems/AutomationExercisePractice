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
@Story("Place Order After Login")
public class PlaceOrderLoginBeforeCheckoutTests {
    // Variables
    private SHAFT.API api;
    private SHAFT.TestData.JSON testData;
    private String timeStamp;
    private SHAFT.GUI.WebDriver driver;

    // Test Cases
    @TmsLink("55512442")
    @Test(description = "Verify the order placement process with login before checkout")
    @Description("Given I open Automation Exercise home, When I click 'Signup / Login' and fill in my email and password to log in, Then I verify 'Logged in as username' at the top, When I add products to the cart and click 'Cart' button, Then I verify that the cart page is displayed, And I click 'Proceed To Checkout', Then I verify Address Details and Review Your Order, When I enter a description in the comment text area and click 'Place Order', And enter payment details: Name on Card, Card Number, CVC, Expiration date, Then I click 'Pay and Confirm Order' and verify the success message 'Your order has been placed successfully!', When I click 'Delete Account', Then I verify 'ACCOUNT DELETED!' and click 'Continue'")
    public void placeOrderLoginBeforeCheckoutWithGui() {
        new NavigationBarPage(driver)
                .clickOnSignupLoginLink();
        new SignupLoginPage(driver)
                .validateOnSignUpVisibility(testData.getTestData("Messages.Signup"))
                .newUserSignup(testData.getTestData("UserName"), testData.getTestData("UserMail.Gui") + timeStamp + "@gizasystems.com");
        new SignupPage(driver)
                .validateOnAccountInfoPage(testData.getTestData("Messages.AccountInfo"))
                .enterAccountInformation(testData.getTestData("Gender"), testData.getTestData("UserPassword"), testData.getTestData("UserFirstName"), testData.getTestData("UserLastName"), testData.getTestData("UserBirthDay"), testData.getTestData("UserBirthMonth"), testData.getTestData("UserBirthYear"))
                .enterAddressInformation(testData.getTestData("UserAddress1"), testData.getTestData("UserCountry"), testData.getTestData("UserState"), testData.getTestData("UserCity"), testData.getTestData("UserZipCode"), testData.getTestData("UserMobile"))
                .validateOnAccountCreated(testData.getTestData("Messages.AccountCreated"));
        new NavigationBarPage(driver)
                .clickOnSignupLoginLink()
                .clickOnLogoutLink();
        new SignupLoginPage(driver)
                .validateOnLoginVisibility(testData.getTestData("Messages.Login"))
                .registeredUserLogin(testData.getTestData("UserMail.Gui") + timeStamp + "@gizasystems.com", testData.getTestData("UserPassword"));
        new NavigationBarPage(driver)
                .validateTheLoggedInUser(testData.getTestData("UserName"));
        new ProductsPage(driver)
                .navigate()
                .addProductToCart(testData.getTestData("productName"))
                .clickOnContinueButton()
                .clickCartButton();
        new CartPage(driver)
                .navigate()
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(testData.getTestData("productName"))
                .proceedToCheckOut();
        new CheckOutPage(driver)
                .verifyingAddressDetails(testData.getTestData("UserFirstName"), testData.getTestData("Gender"), testData.getTestData("UserLastName"), testData.getTestData("UserAddress1"), testData.getTestData("UserCountry"), testData.getTestData("UserCity"))
                .enteringDescriptionInCommentArea(testData.getTestData("Comment.text"));
        new PaymentPage(driver)
                .fillPaymentInformation(testData.getTestData("Card.name"), testData.getTestData("Card.number"), testData.getTestData("Card.cvc"), testData.getTestData("Card.expirymonth"), testData.getTestData("Card.expiryyear"))
                .validateOnPaymentSuccessValidationMessage(testData.getTestData("Messages.SuccessMessages"))
                .clickPayAndConfirmOrderButton()
                .clickOnDownloadInvoiceButton()
                .validateInviceDownloaded(testData.getTestData("File.name"))
                .clickContinueButton();
        new NavigationBarPage(driver)
                .clickOnDeleteAccountLink();
        new DeleteAccountPage(driver)
                .validateAccountDeleted(testData.getTestData("Messages.AccountDeleted"))
                .clickOnContinueBtn();
    }

    @TmsLink("55512442")
    @Test(description = "Place Order Login Before Checkout with GUI And API")
    @Description("Given I have created and validated a new user account via API, When I navigate to the Automation Exercise homepage and login with the newly registered credentials, Then I validate the login success message. Given I navigate to the products page, When I add a product to the cart and proceed through the cart to checkout, Then I verify the cart page loads correctly and the product is added. After entering a description in the comment area and filling in payment information, I validate the payment success message, confirm the order, and download the invoice. Finally, I delete the user account via API, validating the account deletion and ensuring the user is not found thereafter.")
    public void placeOrderLoginBeforeCheckoutWithGUIAndApi() {
        new ApisAccountManagement(api)
                .createRegisterUserAccount(testData.getTestData("UserName"), testData.getTestData("UserMail.GuiApi") + timeStamp + "@gizasystems.com", testData.getTestData("UserPassword"), testData.getTestData("UserFirstName"), testData.getTestData("UserLastName"))
                .validateUserCreatedRegistered();
        new NavigationBarPage(driver)
                .clickOnSignupLoginLink();
        new SignupLoginPage(driver)
                .validateOnLoginVisibility(testData.getTestData("Messages.Login"))
                .registeredUserLogin(testData.getTestData("UserMail.GuiApi") + timeStamp + "@gizasystems.com", testData.getTestData("UserPassword"));
        new NavigationBarPage(driver)
                .validateTheLoggedInUser(testData.getTestData("UserName"));
        new ProductsPage(driver)
                .navigate()
                .addProductToCart(testData.getTestData("productName"))
                .clickOnContinueButton()
                .clickCartButton();
        new CartPage(driver)
                .navigate()
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(testData.getTestData("productName"))
                .proceedToCheckOut();
        new CheckOutPage(driver)
                .enteringDescriptionInCommentArea(testData.getTestData("Comment.text"));
        new PaymentPage(driver)
                .fillPaymentInformation(testData.getTestData("Card.name"), testData.getTestData("Card.number"), testData.getTestData("Card.cvc"), testData.getTestData("Card.expirymonth"), testData.getTestData("Card.expiryyear"))
                .validateOnPaymentSuccessValidationMessage(testData.getTestData("Messages.SuccessMessages"))
                .clickPayAndConfirmOrderButton()
                .clickOnDownloadInvoiceButton()
                .validateInviceDownloaded(testData.getTestData("File.name"))
                .clickContinueButton();
        new ApisAccountManagement(api)
                .deleteUserAccount(testData.getTestData("UserMail.GuiApi") + timeStamp + "@gizasystems.com", testData.getTestData("UserPassword"))
                .validateDeleteUser()
                .validateUserNotFound(testData.getTestData("UserMail.GuiApi") + timeStamp + "@gizasystems.com");
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("PlaceOrderLoginBeforeCheckoutTestsTestData.json");
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
