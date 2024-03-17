package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.pages.HomePage;
import com.gizasystems.automationexercise.pages.NavigationBar;
import com.gizasystems.automationexercise.pages.ProductsPage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Products")
@Story("Add Review On Product")

public class AddReviewTests {
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON productTestData;
    private final String timeStamp = String.valueOf(System.currentTimeMillis());

    @Test(description = "Add review on product")
    public void addReview() {
        new NavigationBar(driver)
                .clickOnProductsLink();
        new ProductsPage(driver)
                .verifyProductPageTitleVisibility()
                .clickOnViewProduct()
                .validateVisibilityOfReviewPage()
                .addReviewOnProduct(productTestData.getTestData("UserName"), productTestData.getTestData("UserMail") + timeStamp + "@gizasystems.com", productTestData.getTestData("ReviewText"))
                .validatethatReviewSuccessAlertIsDisplayed();
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        productTestData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/AddReviewData.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver).navigate().validateOnVisibilityOfHomePage();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
