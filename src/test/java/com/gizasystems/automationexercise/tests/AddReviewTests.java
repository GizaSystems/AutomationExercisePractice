package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.pages.HomePage;
import com.gizasystems.automationexercise.pages.NavigationBarPage;
import com.gizasystems.automationexercise.pages.ProductsPage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Products")
@Story("Add Review On Product")
public class AddReviewTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;
    private final String timeStamp = String.valueOf(System.currentTimeMillis());

    // Test Cases
    @TmsLink("55512487")
    @Test(description = "Add review on product")
    @Description("Given I open Automation Exercise home, When I click on the 'Products' button, Then I verify that the user is navigated to the ALL PRODUCTS page successfully, When I click on a 'View Product' button, Then I verify 'Write Your Review' is visible, When I enter name, email, and review, And click the 'Submit' button, Then I verify the success message 'Thank you for your review.'")
    public void addReview() {
        new NavigationBarPage(driver)
                .clickOnProductsLink();
        new ProductsPage(driver)
                .verifyProductPageTitleVisibility()
                .clickOnViewProduct()
                .validateVisibilityOfReviewPage()
                .addReviewOnProduct(testData.getTestData("UserName"), testData.getTestData("UserMail") + timeStamp + "@gizasystems.com", testData.getTestData("ReviewText"))
                .validatethatReviewSuccessAlertIsDisplayed();
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("AddReviewTestsTestData.json");
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
