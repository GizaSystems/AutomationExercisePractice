package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class NavigationBar {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By signupLogin_link = By.cssSelector("a[href='/login']");
    private final By delete_link = By.cssSelector("a[href='/delete_account']");
    private final By loggedInUser_link = By.xpath("//i[@class = 'fa fa-user']/parent::a");
    private final By products = By.xpath("//i[@class='material-icons card_travel']");

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

    @Step("Click on Products link")
    public NavigationBar clickOnProductsLink() {
        driver.element().click(products);
        return this;
    }
    @Step("Handle ADS")
    public NavigationBar handleAd(){
        Actions actions = new Actions(driver.getDriver());
        actions.pause(1000).doubleClick().perform();
        return this;
    }
    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate the Logged In User")
    public NavigationBar validateTheLoggedInUser(String expectedUser) {
        driver.verifyThat().element(loggedInUser_link).text().isEqualTo("Logged in as " + expectedUser).perform();
        return this;
    }



}
