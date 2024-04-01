package com.gizasystems.automationexercise.pages;

import com.gizasystems.automationexercise.utils.GoogleAlert;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductsPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private String url = System.getProperty("baseUrl") + "/products";

    // Locators
    private final By productsPageTitle_div = By.xpath("//h2[@class='title text-center' and text()='All Products']");
    private final By searchTextArea_input = By.xpath("//input[@id='search_product']");
    private final By searchedProducts_h2 = By.xpath("//h2[text()='Searched Products']");
    private final By allProducts_h2 = By.xpath("//h2[text()='All Products']");
    private final By search_button = By.xpath("//button[@id='submit_search']");
    private final By searchResult_div = By.xpath("//div[@class='productinfo text-center']//p");
    private final By viewCartButtonPopUp_link = By.xpath("//a[@href='/view_cart']//u");
    private final By viewProduct_link = By.xpath("(//i[@class='fa fa-plus-square'])[1]");
    private final By writeReviewSection_link = By.xpath("//ul[@class='nav nav-tabs']");
    private final By reviewerName_input = By.id("name");
    private final By reviewerEmail_input = By.id("email");
    private final By reviewText_input = By.name("review");
    private final By submitBtn_button = By.id("button-review");
    private final By reviewSuccessAlert_div = By.xpath("//div[@class='alert-success alert']//span");
    private final By continueBtn_button = By.xpath("(//button[@class='btn btn-success close-modal btn-block'])[1]");
    private final By viewCartBtn_button = By.xpath("//a[normalize-space()='View Cart']");
    private By hoverOnProduct(String itemName) {
        return By.xpath("//div[@class='productinfo text-center']//child::p[text()='" + itemName + "']");
    }

    private By clickOnProduct(String itemName) {
        return By.xpath("//div[@class='overlay-content']//p[text()='" + itemName + "']//following-sibling::a[text()='Add to cart']");
    }

    private By viewProduct_link(String productName) {
        return By.xpath("(//p[text()='" + productName + "'])[1]//ancestor::div[@class='product-image-wrapper']//child::a[contains(@href,'/product')]");
    }

    // Constructor
    public ProductsPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Pick Product")
    public ProductsPage pickProduct(String productName) {
        driver.element().click(viewProduct_link(productName));
        GoogleAlert.dismissAlert(driver, viewProduct_link(productName));
        return this;
    }

    @Step("Enter Product Name In Search Input And Click Search Button")
    public ProductsPage searchForProduct(String SearchedProduct) {
        driver.element().type(searchTextArea_input, SearchedProduct);
        driver.element().click(search_button);
        return this;
    }

    @Step("Navigate To Products Page")
    public ProductsPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    @Step("Add Products To Cart")
    public ProductsPage addProductToCart(String productName) {
        driver.element().hover(hoverOnProduct(productName));
        driver.element().click(clickOnProduct(productName));
        return this;
    }

    @Step("Click On View Cart Popup Link Button")
    public ProductsPage clickOnViewCartpopupLinkButton() {
        driver.element().click(viewCartButtonPopUp_link);
        driver.browser().refreshCurrentPage();
        return this;
    }

    @Step("Click On View Product Link")
    public ProductsPage clickOnViewProduct() {
        driver.element().click(viewProduct_link);
        return this;
    }

    @Step("Click On Continue Button")
    public ProductsPage clickOnContinueButton() {
        driver.element().click(continueBtn_button);
        return this;
    }

    @Step("Add Review")
    public ProductsPage addReviewOnProduct(String reviewerName, String reviewerEmail, String reviewText) {
        driver.element()
                .type(reviewerName_input, reviewerName)
                .type(reviewerEmail_input, reviewerEmail)
                .type(reviewText_input, reviewText)
                .click(submitBtn_button);
        return this;
    }

    @Step("Click On Cart Button")
    public ProductsPage clickCartButton() {
        driver.element().click(viewCartBtn_button);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate On Visibility Of The Products Page Title")
    public ProductsPage verifyProductPageTitleVisibility() {
        driver.verifyThat().element(productsPageTitle_div).isVisible().perform();
        return this;
    }

    @Step("Verify User Is Navigated To ALL Produtcs Page Successfully")
    public ProductsPage validateOnallProductPage() {
        GoogleAlert.dismissAlert(driver, allProducts_h2);
        driver.verifyThat().element(allProducts_h2).isVisible().perform();
        return this;
    }

    @Step("Verify 'SEARCHED PRODUCTS' Is Visible")
    public ProductsPage validateOnsearchedProductsPage() {
        driver.element().verifyThat(searchedProducts_h2).isVisible().perform();
        return this;
    }

    @Step("Verify All The Products Related To Search Are Visible")
    public ProductsPage validateOnProductsRelatedToSearch(String SearchResult) {
        driver.element().verifyThat(searchResult_div).text().isEqualTo(SearchResult).perform();
        return this;
    }

    @Step("Validate That Review Page Is Displayed")
    public ProductsPage validateVisibilityOfReviewPage() {
        driver.verifyThat().element(writeReviewSection_link).exists().perform();
        return this;
    }

    @Step("Validate That Review Success Alert Is Displayed")
    public ProductsPage validatethatReviewSuccessAlertIsDisplayed() {
        driver.verifyThat().element(reviewSuccessAlert_div).exists().perform();
        return this;
    }
}
