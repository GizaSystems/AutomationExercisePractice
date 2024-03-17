package com.gizasystems.automationexercise.tests.SearchProducts;

import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Download Invoice")
@Story("Download Invoice After Purchase Order ")
public class DownloadInvoiceAfterPurchaseOrder {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;
    private String timeStamp = String.valueOf(System.currentTimeMillis());
    // Test Cases
    @Test(description = "Download Invoice after purchase order")
    public void downloadInvoiceAfterPurchaseOrder() {

        new NavigationBar(driver)
                .clickOnProductsLink();
        new ProductsPage(driver)
                .validateOnallProductPage()
                .searchForProduct(testData.getTestData("SearchedProduct"))
                .validateOnsearchedProductsPage()
                .validateOnProductsRelatedToSearch(testData.getTestData("SearchResult"))
                .addProductsToCart(testData.getTestData("SecondProduct.productDescription"))
                .ClickOnViewCartpopupLinkButton();
        new CartPage(driver)
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(testData.getTestData("SecondProduct.productDescription"))
                .proceedToCheckOut()
                .clickOnRegisterLoginPopupLink();
        new SignupLoginPage(driver)
                .validateOnSignUpVisibility(testData.getTestData("Messages.Signup"))
                .newUserSignup(testData.getTestData("UserName"), testData.getTestData("UserMail.Gui") + timeStamp + "@gizasystems.com");
        new SignupPage(driver)
                .validateOnAccountInfoPage(testData.getTestData("Messages.AccountInfo"))
                .enterAccountInformation(testData.getTestData("Gender"), testData.getTestData("UserPassword"), testData.getTestData("UserFirstName"), testData.getTestData("UserLastName"), testData.getTestData("UserBirthDay"), testData.getTestData("UserBirthMonth"), testData.getTestData("UserBirthYear"))
                .enterAddressInformation(testData.getTestData("UserAddress1"), testData.getTestData("UserCountry"), testData.getTestData("UserState"), testData.getTestData("UserCity"), testData.getTestData("UserZipCode"), testData.getTestData("UserMobile"))
                .validateOnAccountCreated(testData.getTestData("Messages.AccountCreated"))
                .clickOnContinueButton();
        new NavigationBar(driver)
                .validateTheLoggedInUser(testData.getTestData("UserName"))
                .navigateToCartPage();
        new CartPage(driver)
                .verifyCartPageIsLoaded()
                .proceedToCheckOut();
        new CheckOutPage(driver)
                .verifiyingAddressDetails(testData.getTestData("UserFirstName"), testData.getTestData("Gender"), testData.getTestData("UserLastName"), testData.getTestData("UserAddress1"), testData.getTestData("UserCountry"), testData.getTestData("UserCity"))
                .enteringDescriptionInCommentArea(testData.getTestData("Comment.text"));
        new PaymentPage(driver)
                .cardDetailsEntryForPayment(testData.getTestData("Card.name"), testData.getTestData("Card.number"), testData.getTestData("Card.cvc"), testData.getTestData("Card.expirymonth"), testData.getTestData("Card.expiryyear"))
                .validateOnPaymentSuccessValidationMessage(testData.getTestData("Messages.SuccessMessages"))
                .clickPayAndConfirmOrderButton()
                .downloadInvoice()
                .validateInviceDownloaded(testData.getTestData("File.path"), testData.getTestData("File.name"))
                .deleteInvoice(testData.getTestData("File.path"),testData.getTestData("File.name"))
                .clickContinueButton();
        new NavigationBar(driver)
                .clickOnDeleteAccountLink();
        new DeleteAccountPage(driver)
                .validateAccountDeleted(testData.getTestData("Messages.AccountDeleted"))
                .clickOnContinueButton();
    }
    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/DownloadInvoiceAfterPurchaseOrder.json");
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


