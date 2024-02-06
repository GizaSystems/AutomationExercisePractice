package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class ProductsPage {

    private SHAFT.GUI.WebDriver driver;

    private String url = System.getProperty("baseUrl") + "/products";

    private final By HoverOnItemOne = By.xpath("(//div[@class=\"single-products\"]//img)[1]");
    private final By ClickOnItemOne = By.xpath("(//a[@data-product-id=\"1\"])[1]");

    private final By HoverOnItemTwo = By.xpath("(//div[@class=\"single-products\"]//img)[2]");
    private final By ClickOnItemTwo = By.xpath("(//a[@data-product-id=\"2\"])[2]");

    private final By continueButton = By.xpath("(//button[@class=\"btn btn-success close-modal btn-block\"])[1]");
    private final By viewCartButton = By.xpath("//u[normalize-space()='View Cart']");

    public ProductsPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public ProductsPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    @Step("Add First Product to Cart")
    public ProductsPage addFirstProductToCart() {
        driver.element().hover(HoverOnItemOne);
        driver.element().click(ClickOnItemOne);
        driver.element().click(continueButton);
        return this;
    }

    @Step("Add Second Product to Cart")
    public ProductsPage addSecondProductToCart() {
        driver.element().hover(HoverOnItemTwo);
        driver.element().click(ClickOnItemTwo);
        driver.element().click(viewCartButton);
        return this;
    }


}
