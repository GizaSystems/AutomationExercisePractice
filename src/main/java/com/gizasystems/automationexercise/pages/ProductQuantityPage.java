package com.gizasystems.automationexercise.pages;

import com.gizasystems.automationexercise.utils.GoogleAlert;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductQuantityPage {
    // Variables
    private final SHAFT.GUI.WebDriver driver;

    // Locators
    private final By viewProductBtn_button = By.xpath("(//div[@class='choose']//a)[2]");
    private final By productDetails_label = By.xpath("//span/Label");
    private final By hoverBtn_button = By.id("quantity");
    private final By addToCartBtn_button = By.xpath("//button[@class='btn btn-default cart']");
    private final By viewCartBtn_button = By.xpath("//p[@class='text-center']/a[@href='/view_cart']/u");
    private final By productQuantityBtn_button = By.xpath("//td[@class ='cart_quantity']/button[@class='disabled']");

    // Constructor
    public ProductQuantityPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Click On View Product")
    public ProductQuantityPage clickOnViewProduct() {
        driver.element().click(viewProductBtn_button);
        GoogleAlert.dismissAlert(driver, viewProductBtn_button);
        return this;
    }

    @Step("Increase Quantity In Cart")
    public ProductQuantityPage increaseQuantityInCart(String quantity) {
        driver.element()
                .hover(hoverBtn_button)
                .click(hoverBtn_button)
                .type(hoverBtn_button, quantity);
        return this;
    }

    @Step("Add Product To Cart")
    public ProductQuantityPage addProductToCart() {
        driver.element().click(addToCartBtn_button);
        return this;
    }

    @Step("Click On View Cart Button")
    public ProductQuantityPage clickOnCart() {
        driver.element().click(viewCartBtn_button);
        return this;
    }

    @Step("Refresh Cart Page")
    public ProductQuantityPage refreshCartPage(){
        driver.browser().refreshCurrentPage();
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Verify That Product Details Is Opened")
    public ProductQuantityPage verifyOnProductDetails() {
        driver.element().verifyThat(productDetails_label).exists().perform();
        return this;
    }

    @Step("Verify Product Added To Cart With Exact Quantity")
    public ProductQuantityPage verifyExactQuantityAddedToCart(String quantity) {
        driver.element().verifyThat(productQuantityBtn_button).text().isEqualTo(quantity).perform();
        return this;
    }
}
