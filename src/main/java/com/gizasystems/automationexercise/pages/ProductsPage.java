package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductsPage {
    private SHAFT.GUI.WebDriver driver;
    private String url = System.getProperty("baseUrl") + "/products";
    private final By Continue_Btn = By.xpath("(//button[@class=\"btn btn-success close-modal btn-block\"])[1]");
    private final By ViewCart_Btn = By.xpath("//a[normalize-space()='View Cart']");
    public ProductsPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }
    private By HooverOnProduct(Integer Index) {
        return By.xpath("(//div[@class=\"single-products\"]//img)[" + Index + "]");
    }
    private By ClickOnProduct(Integer Index) {
        return By.xpath("(//a[@data-product-id=\""+ Index+ "\"])["+ Index + "]");

    }
    public ProductsPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }
    @Step("Add  Products to Cart")
    public ProductsPage addProductsToCart(Integer index) {
        driver.element().hover(HooverOnProduct(index));
        driver.element().clickUsingJavascript(ClickOnProduct(index));
        driver.element().click(Continue_Btn);
        return this;
    }
    public  ProductsPage ClickCartButton(){
        driver.element().click(ViewCart_Btn);
        return this;
    }
}