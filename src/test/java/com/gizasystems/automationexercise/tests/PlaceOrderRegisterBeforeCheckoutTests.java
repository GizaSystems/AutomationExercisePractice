package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

@Epic("Automation Exercise")
@Feature("Placing Order")
@Story("Place Order : Register Before Checkout")

public class PlaceOrderRegisterBeforeCheckoutTests {

    private SHAFT.TestData.JSON testData;
    private SHAFT.GUI.WebDriver driver;
    private String timeStamp = String.valueOf(System.currentTimeMillis());

    @Test(description = "Place Order: Register before Checkout")
    @Description("Place Order: Register before Checkout")
    @TmsLink("55512432")

    public void PlaceOrderRegisterBeforeCheckout() {
        new NavigationBar(driver)
                .clickOnSignupLoginLink();
        new SignupLoginPage(driver)
                .validateOnSignUpVisibility(testData.getTestData("Messages.Signup"))
                .newUserSignup(testData.getTestData("UserName"), testData.getTestData("UserMail.GuiTimeStamp") + timeStamp + "@gizasystems.com");
        new SignupPage(driver)
                .validateOnAccountInfoPage(testData.getTestData("Messages.AccountInfo"))
                .enterAccountInformation(testData.getTestData("Gender"), testData.getTestData("UserPassword"), testData.getTestData("UserFirstName"), testData.getTestData("UserLastName"), testData.getTestData("UserBirthDay"), testData.getTestData("UserBirthMonth"), testData.getTestData("UserBirthYear"))
                .enterAddressInformation(testData.getTestData("UserAddress1"), testData.getTestData("UserCountry"), testData.getTestData("UserState"), testData.getTestData("UserCity"), testData.getTestData("UserZipCode"), testData.getTestData("UserMobile"))
                .validateOnAccountCreated(testData.getTestData("Messages.AccountCreated"))
                .clickOnContinueButton();
        new NavigationBar(driver)
                .validateTheLoggedInUser(testData.getTestData("UserName"));
        new ProductsPage(driver)
                .navigate()
                .addProductToCart("Blue Top")
                .clickOnContinueButton()
                .clickCartButton();
        new CartPage(driver)
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(testData.getTestData("productName"))
                .proceedToCheckOut();
        new CheckOutPage(driver)
                .navigate()
                .verifyingAddressDetails("Mr. Automation Bot", testData.getTestData("UserAddress1"), testData.getTestData("UserCountry"))
                .enteringDescriptionInCommentArea("Place Order");
        new PaymentPage(driver)
                .navigate()
                .enterPaymentDetails(testData.getTestData("cardName"), testData.getTestData("cardNumber"), testData.getTestData("cvc"), testData.getTestData("cardExpMonth"), testData.getTestData("cardExpYear"))
                .clickOnPayOrder()
                .verifySucessMessage(testData.getTestData("Messages.successMessageOfOrder"))
                .delteAccount();
        new DeleteAccountPage(driver)
                .validateAccountDeleted(testData.getTestData("Messages.AccountDeleted"));
    }

    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/PlaceOrderTestData.json");
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

