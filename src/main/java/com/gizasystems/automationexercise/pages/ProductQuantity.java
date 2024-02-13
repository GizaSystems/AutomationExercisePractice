package com.gizasystems.automationexercise.pages;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class ProductQuantity {

    // Locators
    private final SHAFT.GUI.WebDriver driver;

    private final By viewProductbtn_button
            = By.xpath("(//div[@class='choose']//a)[2]");

    private final By verifyDetailsExist
            = By.xpath("//span/input[@id=\"quantity\"]");

    private final By hoverBtn_button
            = By.xpath("//span/input[@id=\"quantity\"]");

    private final By addToCartbtn_Button
            = By.xpath("//button [@class= \"btn btn-default cart\"]");

    private final By viewCartbtn_button
            = By.xpath("//a[@href=\"/view_cart\"]/u");

    private final By productAdded
            = By.xpath("//td[@class = \"cart_quantity\"]/button[@class=\"disabled\"]");

    // Constructor
    public ProductQuantity(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    @Step("Click on View Product")
    public ProductQuantity clickOnViewProduct() {
        driver.element().click(viewProductbtn_button);
        new Actions(driver.getDriver()).doubleClick().perform();
        return this;
    }

    @Step("Verify that Product details is opened")
    public ProductQuantity verifyOnProductDetails(){
        driver.element().assertThat(verifyDetailsExist);
        return this;
    }

    @Step("Increase Quantity in Cart")
    public ProductQuantity increaseQuantityInCart(){
        driver.element()
                .hover(hoverBtn_button)
                .click(hoverBtn_button)
                .type(hoverBtn_button,"4");
        return this;
    }

    @Step("Add Product to Cart")
    public ProductQuantity addProductToCart(){
        driver.element().click(addToCartbtn_Button);
        return this;
    }

    @Step("Verify Product Added to Cart with exact quantity")
    public ProductQuantity verifyExactQuantityAddedToCart(){
        driver.element().click(viewCartbtn_button);
        driver.element().assertThat(productAdded).text().isEqualTo("4").perform();
        return this;
    }

}
