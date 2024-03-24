package com.gizasystems.automationexercise.tests.checkout;

import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyAddressDetailsTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;
    private String timeStamp;

    // Test Cases
    @TmsLink("55512505")
    @Description("Given I open Automation Exercise home," +
            "And I Verify that home page is visible successfully" +
            "And I Click 'Signup / Login' button" +
            "And I Fill all details in Signup and create account" +
            "And I Verify 'ACCOUNT CREATED!' and click 'Continue' button" +
            "And I Verify ' Logged in as username' at top" +
            "And I Add products to cart" +
            "And I Click 'Cart' button" +
            "And I Verify that cart page is displayed" +
            "And I Click Proceed To Checkout" +
            "And I Verify that the delivery address is same address filled at the time registration of account" +
            "And I Verify that the billing address is same address filled at the time registration of account" +
            "Click 'Delete Account' button" +
            "Then Addresses should be the same as the time of registration" +
            "And Account should be deleted successfully")
    @Test(description = "Verify address details in checkout page")
    public void verifyAddressDetailsInCheckoutPage() {
        new NavigationBar(driver)
                .clickOnSignupLoginLink();
        new SignupLoginPage(driver)
                .validateOnSignUpVisibility(testData.getTestData("Messages.Signup"))
                .newUserSignup(testData.getTestData("RegisterData.UserName"), testData.getTestData("RegisterData.UserMail") + timeStamp + "@gizasystems.com");
        new SignupPage(driver)
                .validateOnAccountInfoPage(testData.getTestData("Messages.AccountInfo"))
                .enterAccountInformation(testData.getTestData("RegisterData.Gender"), testData.getTestData("RegisterData.UserPassword"), testData.getTestData("RegisterData.UserFirstName"), testData.getTestData("RegisterData.UserLastName"), testData.getTestData("RegisterData.UserBirthDay"), testData.getTestData("RegisterData.UserBirthMonth"), testData.getTestData("RegisterData.UserBirthYear"))
                .enterAddressInformation(testData.getTestData("RegisterData.UserAddress1"), testData.getTestData("RegisterData.UserCountry"), testData.getTestData("RegisterData.UserState"), testData.getTestData("RegisterData.UserCity"), testData.getTestData("RegisterData.UserZipCode"), testData.getTestData("RegisterData.UserMobile"))
                .validateOnAccountCreated(testData.getTestData("Messages.AccountCreated"))
                .clickOnContinueButton();
        new NavigationBar(driver)
                .validateTheLoggedInUser(testData.getTestData("RegisterData.UserName"));
        new RecommendedSection(driver)
                .openRecommendedSection()
                .verifyRecommendedSectionVisibility()
                .addToCart("ProductDetails.productName")
                .verifyProductAddedToCart("Messages.ProductAddedMessage");
        new CartPage(driver)
                .openCart()
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(testData.getTestData("ProductDetails.productName"))
                .proceedToCheckOut();

    }

    //////////////////// Configurations \\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("AddressDetails.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
        timeStamp = String.valueOf(System.currentTimeMillis());
    }

    @AfterMethod
    public void afterMethod() {
        //driver.quit();
    }
}

