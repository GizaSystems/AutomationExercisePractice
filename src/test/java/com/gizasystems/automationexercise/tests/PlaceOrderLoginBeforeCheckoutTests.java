package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.apis.Apis;
import com.gizasystems.automationexercise.apis.ApisAccountManagement;
import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlaceOrderLoginBeforeCheckoutTests {
    //variables
    private SHAFT.API api;
    private SHAFT.TestData.JSON testData;
    private String timeStamp;
    private SHAFT.GUI.WebDriver driver;

    @Test(description = "Place Order Login Before Checkout with GUI ")
    @Description("Place Order Login Before Checkout with GUI ")
    @TmsLink("55512442")
    public void placeOrderLoginBeforeCheckoutWithGui() {
        new NavigationBar(driver)
                .clickOnSignupLoginLink();
        new SignupLoginPage(driver)
                .validateOnSignUpVisibility(testData.getTestData("Messages.Signup"))
                .newUserSignup(testData.getTestData("UserName"), testData.getTestData("UserMail.Gui") + timeStamp + "@gizasystems.com");
        new SignupPage(driver)
                .validateOnAccountInfoPage(testData.getTestData("Messages.AccountInfo"))
                .enterAccountInformation(testData.getTestData("Gender"), testData.getTestData("UserPassword"), testData.getTestData("UserFirstName"), testData.getTestData("UserLastName"), testData.getTestData("UserBirthDay"), testData.getTestData("UserBirthMonth"), testData.getTestData("UserBirthYear"))
                .enterAddressInformation(testData.getTestData("UserAddress1"), testData.getTestData("UserCountry"), testData.getTestData("UserState"), testData.getTestData("UserCity"), testData.getTestData("UserZipCode"), testData.getTestData("UserMobile"))
                .validateOnAccountCreated(testData.getTestData("Messages.AccountCreated"));
        new NavigationBar(driver)
                .clickOnSignupLoginLink()
                .clickOnLogoutLink();
        new SignupLoginPage(driver)
                .validateOnLoginVisibility(testData.getTestData("Messages.Login"))
                .registeredUserLogin(testData.getTestData("UserMail.Gui") + timeStamp + "@gizasystems.com", testData.getTestData("UserPassword"));
        new NavigationBar(driver)
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
        new NavigationBar(driver)
                .clickOnDeleteAccountLink();
        new DeleteAccountPage(driver)
                .validateAccountDeleted(testData.getTestData("Messages.AccountDeleted"))
                .clickOnContinueBtn();
    }

    @Test(description = "Place Order Login Before Checkout with GUI And API")
    @Description("Place Order Login Before Checkout with GUI And API")
    @TmsLink("55512442")
    public void placeOrderLoginBeforeCheckoutWithGUIAndApi() {
        new ApisAccountManagement(api)
                .createRegisterUserAccount(testData.getTestData("UserName"), testData.getTestData("UserMail.GuiApi") + timeStamp + "@gizasystems.com", testData.getTestData("UserPassword"), testData.getTestData("UserFirstName"), testData.getTestData("UserLastName"))
                .validateUserCreatedRegistered();
        new NavigationBar(driver)
                .clickOnSignupLoginLink();
        new SignupLoginPage(driver)
                .validateOnLoginVisibility(testData.getTestData("Messages.Login"))
                .registeredUserLogin(testData.getTestData("UserMail.GuiApi") + timeStamp + "@gizasystems.com", testData.getTestData("UserPassword"));
        new NavigationBar(driver)
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
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/PlaceOrderLoginBeforeCheckout.json");
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
