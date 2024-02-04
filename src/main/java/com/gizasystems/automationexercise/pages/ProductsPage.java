package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductsPage {
    private SHAFT.GUI.WebDriver driver;
    public ProductsPage (SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    private String url = System.getProperty("baseUrl") +"/products";
   // private final By productsMenuButton = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[2]/a");
    private final By searchTextArea =By.xpath("//input[@id='search_product']");

    private final By searchedProducts =By.xpath("//div/h2[@class='title text-center']");
    private final By allProducts = By.xpath("//div/h2[@class='title text-center']");

    private final By searchButton= By.xpath("//button[@id='submit_search']");

    private final By searchResult =By.xpath("(//div/p)[3]");
   /* @Step("Navigate to Products Page")
    public ProductsPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }
*/

    /*@Step("Click on 'Products' button")
    public void clickOnProductMenuButton() {
        driver.element().click(productsMenuButton);
    }
    */

    @Step("Enter product name in search input and click search button")
    public ProductsPage searchForProduct() {
        driver.element().type(searchTextArea, "Men Tshirt");
        driver.element().click(searchButton);
        return this;
    }

    @Step("Verify user is navigated to ALL PRODUCTS page successfully")
    public ProductsPage validateOnallProductPage() {

        driver.element().assertThat(allProducts).text().isEqualTo("ALL PRODUCTS").perform();
        return this;
    }
    @Step("Verify 'SEARCHED PRODUCTS' is visible")
    public ProductsPage  validateOnsearchedProducts() {


        driver.element().assertThat(searchedProducts).text().isEqualTo("SEARCHED PRODUCTS").perform();
    return this;
    }

    @Step("Verify all the products related to search are visible")
    public ProductsPage  validateOnProductsRelatedToSearch() {
        driver.element().assertThat(searchResult).text().isEqualTo("Men Tshirt").perform();
        return this;
    }



}
