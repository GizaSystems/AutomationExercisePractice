package com.gizasystems.automationexercise.pages;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class ProductQuantityPage {


    // Locators
    private final SHAFT.GUI.WebDriver driver;

    private final By viewProductbtn_button = By.xpath("(//div[@class='choose']//a)[2]");

    private final By verifyDetailsExist = By.xpath("//span/Label");

    private final By hoverBtn_button = By.id("quantity");

    private final By addToCartbtn_Button = By.xpath("//button [@class= \"btn btn-default cart\"]");

    private final By viewCartbtn_button = By.xpath("//a[@href=\"/view_cart\"]/u");

    private final By productAdded = By.xpath("//td[@class = \"cart_quantity\"]/button[@class=\"disabled\"]");

    // Constructor
    public ProductQuantityPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    @Step("Click on View Product")
    public ProductQuantityPage clickOnViewProduct() {
        driver.element().click(viewProductbtn_button);
        new Actions(driver.getDriver()).doubleClick().perform();
        return this;
    }

    @Step("Increase Quantity in Cart")
    public ProductQuantityPage increaseQuantityInCart(String quantity ){
        driver.element()
                .hover(hoverBtn_button)
                .click(hoverBtn_button)
                .type(hoverBtn_button, quantity);
        return this;
    }

    @Step("Add Product to Cart")
    public ProductQuantityPage addProductToCart(){
        driver.element().click(addToCartbtn_Button);
        return this;
    }

    @Step("Verify Product Added to Cart with exact quantity")
    public ProductQuantityPage verifyExactQuantityAddedToCart(String quantity){
        driver.element().click(viewCartbtn_button);
        driver.element().assertThat(productAdded).text().isEqualTo(quantity).perform();
        return this;
    }

    @Step("Verify that Product details is opened")
    public ProductQuantityPage verifyOnProductDetails(){
        driver.element().assertThat(verifyDetailsExist).exists().perform();
        return this;
    }

}
