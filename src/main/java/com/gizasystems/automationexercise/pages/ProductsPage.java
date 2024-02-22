package com.gizasystems.automationexercise.pages;

import com.gizasystems.automationexercise.utils.GoogleAlert;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductsPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By searchProduct = By.id("search_product");
    private final By viewProduct = By.xpath("(//i[@class='fa fa-plus-square'])[1]");
    private final By writeReviewSection = By.xpath("//li[@class='active']");
    private final By reviewerName_input = By.id("name");
    private final By reviewerEmail_input = By.id("email");
    private final By reviewText_input = By.name("review");
    private final By submitBtn = By.id("button-review");
    private final By reviewSuccessAlert = By.xpath("//div[@class='alert-success alert']//span");

    // Constructor
    public ProductsPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Click on View Product Link")
    public ProductsPage clickOnViewProduct() {
        driver.element().click(viewProduct);
        return this;
    }

    @Step("Add Review")
    public ProductsPage AddReviewOnProduct(String reviewerName, String reviewerEmail, String reviewText) {
        driver.element()
                .type(reviewerName_input, reviewerName)
                .type(reviewerEmail_input, reviewerEmail)
                .type(reviewText_input, reviewText)
                .click(submitBtn);

        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate on Visibility of the Products Page")
    public ProductsPage validateOnVisibilityOfProductsPage() {
        GoogleAlert.dismissAlert(driver);
        driver.verifyThat().element(searchProduct).exists().perform();
        return this;
    }

    @Step("Validate that Review page is displayed")
    public ProductsPage validateVisibilityOfReviewPage() {
        driver.verifyThat().element(writeReviewSection).exists().perform();
        return this;
    }

    @Step("Validate that Review success alert is displayed")
    public ProductsPage validatethatReviewSuccessAlertIsDisplayed() {
        driver.verifyThat().element(reviewSuccessAlert).exists().perform();
        return this;
    }

}

