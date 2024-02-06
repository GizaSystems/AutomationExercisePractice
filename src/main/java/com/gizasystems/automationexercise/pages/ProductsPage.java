package com.gizasystems.automationexercise.pages;

import com.gizasystems.automationexercise.utils.GoogleAlert;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductsPage {
    //Variables
    private SHAFT.GUI.WebDriver driver;

    //Locators
    private final By productsPageTitle_div = By.xpath("//h2[@class='title text-center' and text()='All Products']");
    private By viewProduct_link(String productName) { return By.xpath("(//p[text()='" + productName + "'])[1]//ancestor::div[@class='product-image-wrapper']//child::a[contains(@href,'/product')]");}

    //Constructors
    public ProductsPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Pick Product")
    public ProductsPage pickProduct(String productName){
        driver.element().click(viewProduct_link(productName));
        GoogleAlert.dismissAlert(driver);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate on Visibility of the Products Page Title")
    public ProductsPage VerifyProductPageTitleVisibility(){
        driver.assertThat().element(productsPageTitle_div).isVisible().perform();
        return this;
    }

}
