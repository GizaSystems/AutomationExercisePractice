package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.pages.CartPage;
import com.gizasystems.automationexercise.pages.HomePage;
import com.gizasystems.automationexercise.pages.RecommendedSectionPage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Recommended Items")
@Story("Add to cart from Recommended items")
public class AddRecommendedItemTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    // Test Cases
    @TmsLink("55512495")
    @Test(description = "Add RecommendedProducts To Card ")
    @Description("Given I open Automation Exercise home, When I navigate to Recommended Products, And I add Product to Cart, Then I am able to see the Product added to Cart")
    public void addToCartFromRecommendedItems(){
       new RecommendedSectionPage(driver)
                .openRecommendedSection()
                .verifyRecommendedSectionVisibility()
                .addToCart(testData.getTestData("productName"))
                .verifyProductAddedToCart(testData.getTestData("ProductAddedMessage"));
       new CartPage(driver)
                .openCart()
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(testData.getTestData("productName"));
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void setUpConfigurations(){
        testData = new SHAFT.TestData.JSON("AddRecommendedItemTestsTestData.json");
    }

    @BeforeMethod
    public void setUp(){
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
