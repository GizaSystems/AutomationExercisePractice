package com.gizasystems.automationexercise.pages;

import com.gizasystems.automationexercise.utils.GoogleAlert;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductsPage {
    //Variables
    private SHAFT.GUI.WebDriver driver;
    private String url = System.getProperty("baseUrl") + "/products";


    //Locators
    private final By productsPageTitle_div = By.xpath("//h2[@class='title text-center' and text()='All Products']");

    private By viewProduct_link(String productName) {
        return By.xpath("(//p[text()='" + productName + "'])[1]//ancestor::div[@class='product-image-wrapper']//child::a[contains(@href,'/product')]");
    }

    private final By searchTextArea = By.xpath("//input[@id='search_product']");
    private final By searchedProducts = By.xpath("//h2[text()='Searched Products']");
    private final By allProducts = By.xpath("//h2[text()='All Products']");
    private final By searchButton = By.xpath("//button[@id='submit_search']");
    private final By searchResult = By.xpath("//div[@class='productinfo text-center']//p");
    private final By viewCartButtonPopUp = By.xpath("//a[@href=\"/view_cart\"]//u");

    private By hoverOnProduct(String itemName) {
        return By.xpath("//div[@class='productinfo text-center']//child::p[text()='" + itemName + "']");
    }

    private By ClickOnProduct(String itemName) {
        return By.xpath("//div[@class='overlay-content']//p[text()='" + itemName + "']//following-sibling::a[text()='Add to cart']");

    }

    //Constructors
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

    @Step("Enter product name in search input and click search button")
    public ProductsPage searchForProduct(String SearchedProduct) {
        driver.element().type(searchTextArea, SearchedProduct);
        driver.element().click(searchButton);
        return this;
    }

    public ProductsPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    @Step("Add Products to Cart")
    public ProductsPage addProductsToCart(String ProductName) {
        driver.element().hover(hoverOnProduct(ProductName));
        driver.element().click(ClickOnProduct(ProductName));
        return this;
    }

    @Step("Click On View Cart popup Link Button")
    public ProductsPage ClickOnViewCartpopupLinkButton() {
        driver.element().click(viewCartButtonPopUp);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate on Visibility of the Products Page Title")
    public ProductsPage VerifyProductPageTitleVisibility() {
        driver.assertThat().element(productsPageTitle_div).isVisible().perform();
        return this;
    }

    @Step("Verify user is navigated to ALL PRODUCTS page successfully")
    public ProductsPage validateOnallProductPage() {
        GoogleAlert.dismissAlert(driver, allProducts);
        driver.assertThat().element(allProducts).isVisible().perform();
        return this;
    }

    @Step("Verify 'SEARCHED PRODUCTS' is visible")
    public ProductsPage validateOnsearchedProducts() {
        driver.element().assertThat(searchedProducts).isVisible().perform();
        return this;
    }

    @Step("Verify all the products related to search are visible")
    public ProductsPage validateOnProductsRelatedToSearch(String SearchResult) {
        driver.element().assertThat(searchResult).text().isEqualTo(SearchResult).perform();
        return this;
    }
}
