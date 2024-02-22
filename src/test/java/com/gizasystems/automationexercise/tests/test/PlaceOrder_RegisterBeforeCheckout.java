package com.gizasystems.automationexercise.tests.test;

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
@Feature("Placing Order")
@Story("Place Order : Register Before Checkout")

public class PlaceOrder_RegisterBeforeCheckout {
    private SHAFT.TestData.JSON testData;
    private SHAFT.TestData.JSON productData;
    private SHAFT.TestData.JSON CardData;
    private SHAFT.GUI.WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/RegisterUser.json");
        productData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/Product.json");
        CardData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/CardData.json");
    }
    private  String timeStamp = String.valueOf(System.currentTimeMillis());
    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
    }
    @Test(description = "Place Order: Register before Checkout")
    public void PlaceOrderRegisterBeforeCheckout ()
    {
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
                .addProductsToCart(1)
                .ClickCartButton();
        new CartPage(driver)
                .openCart()
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(productData.getTestData("productName"))
                .proceedToCheckOut();
        new CheckOutPage(driver)
                .navigate()
                .verififyingAddressDetails("Mr. Automation Bot",testData.getTestData("UserAddress1"),testData.getTestData("UserCountry"))
                .enteringDescriptionInCommentArea( "Place Order");
        new PaymentPage(driver)
                .navigate()
                .enterPaymentDetails(CardData.getTestData("CardName"),CardData.getTestData("CardNumber") , CardData.getTestData("CVC"), CardData.getTestData("CardExpMonth"),CardData.getTestData("CardExpYear"))
                .clickOnPayOrder()
                .VerifySucessMessage(CardData.getTestData("SuccessMessage"))
                .DelteAccount();
        new DeleteAccountPage(driver)
                .validateAccountDeleted("ACCOUNT DELETED!");
    }
    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}

