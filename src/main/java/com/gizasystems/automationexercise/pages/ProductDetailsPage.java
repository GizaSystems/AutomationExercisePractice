package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductDetailsPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By productDetails_div = By.className("product-details");
    private final By productName_h2 = By.xpath("//div[@class='product-information']//h2");
    private final By productCategory_p = By.xpath("//div[@class='col-sm-7']//p[1]");
    private final By productAvailability_p = By.xpath("//div[@class='col-sm-7']//p[2]");
    private final By productCondition_p = By.xpath("//div[@class='col-sm-7']//p[3]");
    private final By productBrand_p = By.xpath("//div[@class='col-sm-7']//p[4]");
    private final By productPrice_span = By.xpath("//div[@class='col-sm-7']//span//span");

    // Constructor
    public ProductDetailsPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Verify Product Details Page Visibility")
    public ProductDetailsPage verifyProductDetailsPageVisibility(){
        driver.verifyThat().element(productDetails_div).isVisible().perform();
        return this;
    }

    @Step("Verify Product Details")
    public ProductDetailsPage verifyProductDetails(String productName, String productCategory, String productPrice, String productAvailability
            , String productionCondition, String productBrand ){
        driver.verifyThat().element(productName_h2).text().isEqualTo(productName).perform();
        driver.verifyThat().element(productCategory_p).text().contains(productCategory).perform();
        driver.verifyThat().element(productPrice_span).text().isEqualTo(productPrice).perform();
        driver.verifyThat().element(productAvailability_p).textTrimmed().contains(productAvailability).perform();
        driver.verifyThat().element(productCondition_p).textTrimmed().contains(productionCondition).perform();
        driver.verifyThat().element(productBrand_p).textTrimmed().contains(productBrand).perform();
        return this;
    }
}
