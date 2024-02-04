package com.gizasystems.automationexercise.tests.addRecommendItemToCart;

import com.gizasystems.automationexercise.pages.HomePage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Recommended Items")
@Story("Add to cart from Recommended items")
public class AddRecommendedItemTest {
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON jsonData;

    @Test(description = "Add RecommendedProducts To Card ")
    @Description("Given I open Automation Exercise home, When I navigate to Recommended Products, And I add Product to Cart, Then I am able to see the Product added to Cart")
    public void addToCartFromRecommendedItems(){
       new HomePage(driver)
                .openRecommendedSection()
                .isRecommendedSectionVisible()
                .addToCart(jsonData.getTestData("productName"))
                .isProductAddedSuccessfullyToCart(jsonData.getTestData("ProductAddedMessage"))
                .openCart()
                .verifyCartPageIsLoaded()
                .verifyProductAddedToCart(jsonData.getTestData("productName"));

    }

    @BeforeClass
    public void setUpConfigurations(){
        String dataFileName = "Product.json";
        jsonData = new SHAFT.TestData.JSON(dataFileName);

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
