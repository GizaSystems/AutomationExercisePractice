package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class BrandProductsPage {
    // Variables
    private final SHAFT.GUI.WebDriver driver;

    // Locators
    private final By productsBtn_button = By.xpath("//a[@href='/products']");
    private final By brandsBody_div = By.xpath("//div[@class='brands_products']");
    private final By brandNameBtn_button = By.xpath("//a[@href='/brand_products/Polo']");
    private final By productsOfBrand_link = By.xpath("//a[@href='/product_details/1']");
    private final By secondBrandName_button = By.xpath("//a[@href='/brand_products/H&M']");
    private final By productsOfsecondBrand_img = By.xpath("//img[@src='/get_product_picture/2']");
    private By brandName_h2(String brand) {
        return By.xpath("//div/h2[@class='title text-center']['" + brand + "']");
    }

    // Constructor
    public BrandProductsPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    ///////////////////// Actions /////////////////////////////
    @Step("Click on View Product")
    public BrandProductsPage clickOnProducts() {
        driver.element().click(productsBtn_button);
        return this;
    }

    @Step("Click On Brand Name")
    public  BrandProductsPage clickOnBrandName(){
        driver.element().click(brandNameBtn_button);
        return this;
    }

    @Step("Verify That Other Brand Pages Are Visible")
    public  BrandProductsPage clickOnSecondBrandName(){
        driver.element().click(secondBrandName_button);
        return this;
    }

    ///////////////////// Validations /////////////////////////////
    @Step("Verify That Brands Are Visible")
    public BrandProductsPage verifyBrandsVisible(){
        driver.element().verifyThat(brandsBody_div).exists().perform();
        return this;
    }

    @Step("Verify That Brand Pages Are Visible")
    public  BrandProductsPage verifyBrandPageVisible(String brand){
        driver.element().verifyThat(brandName_h2(brand)).exists().perform();
        return this;
    }

    @Step("Verify That Products Are Visible")
    public  BrandProductsPage verifyProductsVisible(){
        driver.element().verifyThat(productsOfBrand_link).exists().perform();
        return this;
    }

    @Step("Verify That Second Brand Page Are Visible")
    public  BrandProductsPage verifySecondBrandPageVisible(String brand){
        driver.element().verifyThat(brandName_h2(brand)).exists().perform();
        return this;
    }

    @Step("Verify That Products Of Second Brand Are Visible")
    public  BrandProductsPage verifyProductsOfSecondBrandVisible(){
        driver.element().verifyThat(productsOfsecondBrand_img).exists().perform();
        return this;
    }
}