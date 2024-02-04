package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private String url = System.getProperty("baseUrl");

    // Locators
    private final By featuredItems_div = By.cssSelector("div.features_items");
    private final By recommendedItems_div = By.cssSelector("div.recommended_items");
    private final By productsMenuButton = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[2]/a");
    // Constructor
    public HomePage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Navigate to Home Page")
    public HomePage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }



    @Step("Navigate to Product Page")
    public ProductsPage navigateToProductsPage() {
        driver.element().click(productsMenuButton);
        return new ProductsPage(driver);
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate on Visibility of the Home Page")
    public HomePage validateOnVisibilityOfHomePage() {
        driver.verifyThat().element(featuredItems_div).exists().perform();
        driver.verifyThat().element(recommendedItems_div).exists().perform();
        return this;
    }

}
