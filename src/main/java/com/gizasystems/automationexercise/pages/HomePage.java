package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private String url = System.getProperty("baseUrl");

    // Locators
    private final By featuredItems_div = By.cssSelector("div.features_items");
    private final By recommendedItems_div = By.cssSelector("div.recommended_items");
    private final By categoryTitle_h2= By.xpath("//h2[@class='title text-center']");
    private final By scrollUpArrow_button = By.xpath("//i[@class='fa fa-angle-up']");
    private final By slideHeading_h2= By.xpath("(//h2[contains(text(), 'Full-Fledged practice website for Automation Engineers')])[1]");

    // Constructor
    public HomePage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Navigate to Home Page")
    public HomePage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    @Step("Click On Scroll Up Arrow")
    public HomePage clickOnScrollUpArrow() {
        driver.element().click(scrollUpArrow_button);
        return this;
    }

    @Step("Scroll To Top Page")
    public HomePage scrollToTopPage() {
        driver.element().scrollToElement(slideHeading_h2);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate On Visibility Of The Home Page")
    public HomePage validateOnVisibilityOfHomePage() {
        driver.verifyThat().element(featuredItems_div).exists().perform();
        driver.verifyThat().element(recommendedItems_div).exists().perform();
        return this;
    }

    @Step("Validate On Visibility Of The Category Title")
    public HomePage validateOnVisibilityOfCategoryTitle(String categoryTitle) {
        driver.verifyThat().element(categoryTitle_h2).text().equalsIgnoringCaseSensitivity(categoryTitle).perform();
        return this;
    }

    @Step("Validate On Visibility Of The Text On The Carousel Slide On The Homepage")
    public HomePage validateOnVisibilityOfCarouselSlideText(String slideText) {
        driver.verifyThat().element(slideHeading_h2).text().equalsIgnoringCaseSensitivity(slideText).perform();
        return this;
    }
}
