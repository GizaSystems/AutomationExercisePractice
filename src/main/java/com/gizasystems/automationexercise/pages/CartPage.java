package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By shoppingCartPageTitle = By.cssSelector("li.active");
    private final By productName = By.xpath("//td[@class='cart_description']//h4");

    // Constructor
    public CartPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\


    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate Cart Page is Loaded")
    public CartPage verifyCartPageIsLoaded(){
        driver.assertThat().element(shoppingCartPageTitle).isVisible().perform();
        return this;
    }
    @Step("Validate on Product Added To Cart Page")
    public CartPage verifyProductAddedToCart(String addedProductName){
        driver.assertThat().element(productName).text().contains(addedProductName).perform();
        return this;
    }
}
