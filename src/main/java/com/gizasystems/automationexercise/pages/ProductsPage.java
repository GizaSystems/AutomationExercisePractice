package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class ProductsPage {

    private SHAFT.GUI.WebDriver driver;

    private String url = System.getProperty("baseUrl") + "/products";

    public ProductsPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public ProductsPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    private By hoverOnProduct(Integer Index) {
        return By.xpath("(//div[@class=\"single-products\"]//img)[" + Index + "]");
    }

    private By ClickOnProduct(Integer Index) {
        return By.xpath("(//a[@data-product-id=\"" + Index + "\"])[" + Index + "]");

    }

    @Step("Add  Products to Cart")
    public ProductsPage addProductsToCart(Integer index, Integer itemID) {
        driver.element().hover(hoverOnProduct(index));
        driver.element().click(ClickOnProduct(itemID));
        return this;
    }


}
