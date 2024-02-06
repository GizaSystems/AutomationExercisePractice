package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductDetailsPage {

    //Variables
    private SHAFT.GUI.WebDriver driver;

    //Locators
    private final By productDetails_div = By.className("product-details");
    private final By productName_h2 = By.xpath("//div[@class='product-information']//h2");
    private final By productCategory_p = By.xpath("//div[@class='col-sm-7']//p[1]");
    private final By productAvailability_p = By.xpath("//div[@class='col-sm-7']//p[2]");
    private final By productCondition_p = By.xpath("//div[@class='col-sm-7']//p[3]");
    private final By productBrand_p = By.xpath("//div[@class='col-sm-7']//p[4]");
    private final By productPrice_span = By.xpath("//div[@class='col-sm-7']//span//span");

    //Constructor
    public ProductDetailsPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Verify Product Details")
    public ProductDetailsPage verifyProductDetails(String productName, String productCategory, String productPrice, String productAvailability
            , String productionCondition, String productBrand ){
        driver.assertThat().element(productName_h2).text().isEqualTo(productName).perform();
        driver.assertThat().element(productCategory_p).text().contains(productCategory).perform();
        driver.assertThat().element(productPrice_span).text().isEqualTo(productPrice).perform();
        driver.assertThat().element(productAvailability_p).textTrimmed().contains(productAvailability).perform();
        driver.assertThat().element(productCondition_p).textTrimmed().contains(productionCondition).perform();
        driver.assertThat().element(productBrand_p).textTrimmed().contains(productBrand).perform();
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Verify Product Details Page Visibility")
    public ProductDetailsPage verifyProductDetailsPageVisibility(){
        driver.assertThat().element(productDetails_div).isVisible().perform();
        return this;
    }

}
