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
    private String timeStamp;
    private SHAFT.GUI.WebDriver driver;

    @Test(description = "Place Order: Register before Checkout")
    @Description("Place Order: Register before Checkout")
    @TmsLink("55512432")
    public void PlaceOrderRegisterBeforeCheckoutGUI(){
        new NavigationBar(driver)
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
                .typeUserCardName(testData.getTestData("cardName"))
                .typeCardNumber(testData.getTestData("cardNumber"))
                .typeCardCvc( testData.getTestData("cvc"))
                .typeExpiryMonth(testData.getTestData("cardExpMonth"))
                .typeExpiryYear(testData.getTestData("cardExpYear"))
                .clickOnPayAndConfirmBtn()
                .verifySuccessMessage(testData.getTestData("Messages.successMessageOfOrder"));
        new NavigationBar(driver)
                .validateTheLoggedInUser(testData.getTestData("UserName"))
                .clickOnDeleteAccountLink();
        new DeleteAccountPage(driver)
                .validateAccountDeleted(testData.getTestData("Messages.AccountDeleted"));
    }

    @Test(description = "Place Order: Register Using APIs before Checkout ")
    public void PlaceOrderRegisterBeforeCheckoutAPI() {

        new ApisAccountManagement(api)
                .createRegisterUserAccount(testData.getTestData("UserName"), testData.getTestData("UserMail.ApiTimeStamp") + timeStamp + "@gizasystems.com", testData.getTestData("UserPassword"), testData.getTestData("UserFirstName"), testData.getTestData("UserLastName"));
        new NavigationBar(driver)
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
                .typeUserCardName(testData.getTestData("cardName"))
                .typeCardNumber(testData.getTestData("cardNumber"))
                .typeCardCvc( testData.getTestData("cvc"))
                .typeExpiryMonth(testData.getTestData("cardExpMonth"))
                .typeExpiryYear(testData.getTestData("cardExpYear"))
                .clickOnPayAndConfirmBtn()
                .verifySuccessMessage(testData.getTestData("Messages.successMessageOfOrder"));
        new ApisAccountManagement(api)
                .deleteUserAccount(testData.getTestData("UserMail.ApiTimeStamp") + timeStamp + "@gizasystems.com", testData.getTestData("UserPassword"))
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