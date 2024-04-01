package com.gizasystems.automationexercise.pages;

import com.gizasystems.automationexercise.utils.GoogleAlert;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBarPage {
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
    public NavigationBarPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("click On Signup Login Link")
    public NavigationBarPage clickOnSignupLoginLink() {
        driver.element().click(signupLogin_link);
        return this;
    }

    @Step("click On LogOut Link")
    public NavigationBarPage clickOnLogoutLink() {
        driver.element().click(logout_link);
        return this;
    }

    @Step("Click On Delete Account Link")
    public NavigationBarPage clickOnDeleteAccountLink() {
        driver.element().click(delete_link);
        GoogleAlert.dismissAlert(driver,delete_link);
        return this;
    }

    @Step("Click On Products Page Link")
    public NavigationBarPage clickOnProductsLink() {
        driver.element().click(products_link);
        GoogleAlert.dismissAlert(driver, products_link);
        return this;
    }

    @Step("Click On ContactUs Account Link")
    public NavigationBarPage clickOnContactUsLink() {
        driver.element().clickUsingJavascript(contactUs_link);
        return this;
    }

    @Step("Navigate To Cart Page")
    public NavigationBarPage navigateToCartPage() {
        driver.element().click(cartBtn_button);
        return this;
    }

    @Step("Click On Test Case")
    public NavigationBarPage clickOnTestCase() {
        driver.element().click(testCase_link);
        GoogleAlert.dismissAlert(driver, testCase_link);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate The Logged In User")
    public NavigationBarPage validateTheLoggedInUser(String expectedUser) {
        driver.verifyThat().element(loggedInUser_link).textTrimmed().isEqualTo("Logged in as " + expectedUser).perform();
        return this;
    }
}
