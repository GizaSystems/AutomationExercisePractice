package com.gizasystems.automationexercise.pages;

import com.gizasystems.automationexercise.utils.GoogleAlert;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBar {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By signupLogin_link = By.cssSelector("a[href='/login']");
    private final By delete_link = By.cssSelector("a[href='/delete_account']");
    private final By loggedInUser_link = By.xpath("//i[@class = 'fa fa-user']/parent::a");
    private final By products_link = By.cssSelector("a[href='/products']");

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

    @Step("Click on Products Page Link")
    public NavigationBar clickOnProductsLink() {
        driver.element().click(products_link);
        GoogleAlert.dismissAlert(driver);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate the Logged In User")
    public NavigationBar validateTheLoggedInUser(String expectedUser) {
        driver.verifyThat().element(loggedInUser_link).text().isEqualTo("Logged in as " + expectedUser).perform();
        return this;
    }
}
