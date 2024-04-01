package com.gizasystems.automationexercise.tests.SearchProducts;

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
@Feature("Download Invoice")
@Story("Download Invoice After Purchase Order")
public class DownloadInvoiceAfterPurchaseOrderTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;
    private SHAFT.API api;
    private String timeStamp = String.valueOf(System.currentTimeMillis());

    // Test Cases
    @TmsLink("55512515")
    @Test(description = "Download Invoice after purchase order")
    @Description("Given I open Automation Exercise home, When I add products to the cart and proceed to checkout, Then I register or login, fill in all required details, and place the order, And verify that the order is successfully placed, When I click the 'Download Invoice' button, Then I verify that the invoice is downloaded successfully, And I continue with the deletion of the account, ensuring the account is deleted successfully.")
    public void downloadInvoiceAfterPurchaseOrder() {
        new NavigationBarPage(driver)
                .clickOnProductsLink();
        new ProductsPage(driver)
                .validateOnallProductPage()
                .searchForProduct(testData.getTestData("SearchedProduct"))
                .validateOnsearchedProductsPage()
                .validateOnProductsRelatedToSearch(testData.getTestData("SearchResult"))
                .addProductToCart(testData.getTestData("SecondProduct.productDescription"))
                .clickOnViewCartpopupLinkButton();
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
        new NavigationBarPage(driver)
                .validateTheLoggedInUser(testData.getTestData("UserName"))
                .navigateToCartPage();
        new CartPage(driver)
                .verifyCartPageIsLoaded()
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

    @TmsLink("55512515")
    @Test(description = "Download Invoice after purchase order Using API and GUI")
    @Description("Given I navigate to the Products page and validate its presence, When I search for a product and validate the search results, Then I add a second product to the cart and view the cart through the popup link. Given a new user account is created and verified via API, When I login with the newly registered credentials and proceed through the cart to checkout, Then I enter a description in the comment area and fill in the payment information. After validating the payment success message and confirming the order, I download the invoice and verify its download. Finally, I delete the user account via API, ensuring the account deletion is validated and the user is no longer found.")
    public void downloadInvoiceAfterPurchaseOrderWithApi() {
        new NavigationBarPage(driver)
                .clickOnProductsLink();
        new ProductsPage(driver)
                .validateOnallProductPage()
                .searchForProduct(testData.getTestData("SearchedProduct"))
                .validateOnsearchedProductsPage()
                .validateOnProductsRelatedToSearch(testData.getTestData("SearchResult"))
                .addProductToCart(testData.getTestData("SecondProduct.productDescription"))
                .clickOnViewCartpopupLinkButton();
        new CartPage(driver)
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(testData.getTestData("SecondProduct.productDescription"))
                .proceedToCheckOut();
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
        new CartPage(driver)
                .openCart()
                .verifyCartPageIsLoaded()
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
        testData = new SHAFT.TestData.JSON("DownloadInvoiceAfterPurchaseOrderTestsTestData.json");
    }

    @BeforeMethod
    public void beforeMethod() {
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


