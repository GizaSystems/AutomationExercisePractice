package com.gizasystems.automationexercise.pages;

import com.gizasystems.automationexercise.utils.GoogleAlert;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBar {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By signupLogin_link = By.cssSelector("div.shop-menu a[href='/login']");
    private final By delete_link = By.cssSelector("a[href='/delete_account']");
    private final By loggedInUser_link = By.xpath("//i[@class = 'fa fa-user']/parent::a");
    private final By products_link = By.cssSelector("a[href='/products']");
    private final By cartBtn_button = By.xpath("//a[contains(text(),' Cart') ]");
    private final By testCase_link = By.cssSelector("li > a[href='/test_cases']");
    private final By contactUs_link = By.cssSelector("a[href='/contact_us']");
    private final By logout_link = By.cssSelector("div.shop-menu a[href='/logout']");

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

    @Step("click on LogOut Link")
    public NavigationBar clickOnLogoutLink() {
        driver.element().click(logout_link);
        return this;
    }

    @Step("Click on Delete Account Link")
    public NavigationBar clickOnDeleteAccountLink() {
        driver.element().click(delete_link);
        GoogleAlert.dismissAlert(driver,delete_link);
        return this;
    }

    @Step("Click on Products Page Link")
    public NavigationBar clickOnProductsLink() {
        driver.element().click(products_link);
        GoogleAlert.dismissAlert(driver, products_link);
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

    @Step("Click on test Case")
    public NavigationBar ClickOnTestCase() {
        driver.element().click(testCase_link);
        GoogleAlert.dismissAlert(driver, testCase_link);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate the Logged In User")
    public NavigationBar validateTheLoggedInUser(String expectedUser) {
        driver.verifyThat().element(loggedInUser_link).textTrimmed().isEqualTo("Logged in as " + expectedUser).perform();
        return this;
    }
}
