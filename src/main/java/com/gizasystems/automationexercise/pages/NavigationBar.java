package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class NavigationBar {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By signupLogin_link = By.cssSelector("a[href='/login']");

    // Constructor
    public NavigationBar(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    public void clickOnSignupLoginLink() {
        driver.element().click(signupLogin_link);
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\

}
