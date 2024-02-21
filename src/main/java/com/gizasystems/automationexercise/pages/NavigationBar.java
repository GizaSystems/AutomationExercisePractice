package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBar {
    // Variables
    private  SHAFT.GUI.WebDriver driver;

    // Locators
    private final By signupLogin_link = By.cssSelector("a[href='/login']");
    private final By delete_link = By.cssSelector("a[href='/delete_account']");
    private final By loggedInUser_link = By.xpath("//i[@class = 'fa fa-user']/parent::a");
    private final By cartBtn_button = By.xpath("//a[contains(text(),' Cart') ]");
    private final By productsMenuButton = By.xpath("//li[2]/a/i");
    private final By contactUs_link = By.cssSelector("a[href='/contact_us']");

    // Constructor
    public NavigationBar(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("click on Signup Login Link")
    public NavigationBar clickOnSignupLoginLink() {
        driver.element().click(signupLogin_link);
        return this;
    }

    @Step("Click on Delete Account Link")
    public NavigationBar clickOnDeleteAccountLink() {
        driver.element().click(delete_link);
        return this;
    }
    @Step("Click on ContactUs Account Link")
    public NavigationBar clickOnContactUsLink() {
        driver.element().clickUsingJavascript(contactUs_link);
        return this;
    }

    @Step("Navigate to Cart Page")
    public NavigationBar navigateToCartPage() {
        driver.element().click(cartBtn_button);
        return this;
    }

    @Step("Click on 'Products' button")
    public NavigationBar  clickOnProductMenuButton() {
        driver.element().click(productsMenuButton);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate the Logged In User")
    public NavigationBar validateTheLoggedInUser(String expectedUser) {
        driver.verifyThat().element(loggedInUser_link).text().isEqualTo("Logged in as " + expectedUser).perform();
        return this;
    }
}
