package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class BrandProductsPage {
    private final SHAFT.GUI.WebDriver driver;
    private final By productsbtn_button = By.xpath("//a[@href='/products']");
    private final By brandsBody = By.xpath("//div[@class='brands_products']");
    private final By brandNameBtn_button = By.xpath("//a[@href='/brand_products/Polo']");
    private final By brandName = By.xpath("//div/h2[@class='title text-center']['Brand - Polo Products']");
    private final By productsOfBrand = By.xpath("//a[@href='/product_details/1']");
    private final By secondBrandName_button = By.xpath("//a[@href='/brand_products/H&M']");
    private final By secondBrandName = By.xpath("//div/h2[@class='title text-center']['Brand - H&M Products']");
    private final By productsOfsecondBrand = By.xpath("//img[@src='/get_product_picture/2']");

    //Constructor
    public BrandProductsPage(SHAFT.GUI.WebDriver driver) { this.driver = driver; }

    @Step("Click on View Product")
    public BrandProductsPage clickOnProducts() {
        driver.element().click(productsbtn_button);
        return this;
    }

    @Step("Verify that Brands are visible")
    public BrandProductsPage verifyBrandsVisible(){
        driver.element().assertThat(brandsBody).exists().perform();
        return this;
    }

    @Step("Click on Brand name")
    public  BrandProductsPage clickOnBrandName(){
        driver.element().click(brandNameBtn_button);
        return this;
    }

    @Step("Verify that Brand Page are visible")
    public  BrandProductsPage verifyBrandPageVisible(){
        driver.element().assertThat(brandName).exists().perform();
        return this;
    }

    @Step("Verify that Products are visible")
    public  BrandProductsPage verifyProductsVisible(){
        driver.element().assertThat(productsOfBrand).exists().perform();
        return this;
    }

    @Step("Verify that other Brand Page are visible")
    public  BrandProductsPage clickOnSecondBrandName(){
        driver.element().click(secondBrandName_button);
        return this;
    }

    @Step("Verify that second Brand Page are visible")
    public  BrandProductsPage verifysecondBrandPageVisible(){
        driver.element().assertThat(secondBrandName).exists().perform();
        return this;
    }

    @Step("Verify that Products of second Brand are visible")
    public  BrandProductsPage verifyProductsOfSecondBrandVisible(){
        driver.element().assertThat(productsOfsecondBrand).exists().perform();
        return this;
    }

}
