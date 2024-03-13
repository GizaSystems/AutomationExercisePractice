package com.gizasystems.automationexercise.pages;

import com.gizasystems.automationexercise.utils.GoogleAlert;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class SignupPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private String url = System.getProperty("baseUrl") + "/signup";

    // Locators
    private final By accountInfo_h1 = By.xpath("//div[@class='login-form']/h2[@class='title text-center']/b");

    private By gender_input(String gender) {
        return By.xpath("//input[@value='" + gender + "']");
    }

    private final By password_input = By.id("password");
    private final By days_select = By.id("days");
    private final By months_select = By.id("months");
    private final By years_select = By.id("years");
    private final By firstName_input = By.id("first_name");
    private final By lastName_input = By.id("last_name");
    private final By addressOne_input = By.id("address1");
    private final By country_select = By.id("country");
    private final By state_input = By.id("state");
    private final By city_input = By.id("city");
    private final By zipcode_input = By.id("zipcode");
    private final By mobileNumber_input = By.id("mobile_number");
    private final By createAccount_button = By.xpath("//button[@data-qa='create-account']");
    private final By createAccount_h2 = By.xpath("//h2[@data-qa='account-created']//b");
    private final By continueBtn_link = By.xpath("//a[@data-qa='continue-button']");


    // Constructor
    public SignupPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    public SignupPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    @Step("Enter Account Information")
    public SignupPage enterAccountInformation(String gender, String pass, String userFirstName, String userLastName, String day, String month, String year) {
        driver.async().element()
                .click(gender_input(gender))
                .type(password_input, pass)
                .select(days_select, day)
                .select(months_select, month)
                .select(years_select, year)
                .type(firstName_input, userFirstName)
                .type(lastName_input, userLastName)
                .synchronize();
        return this;
    }

    @Step("Enter Address Information")
    public SignupPage enterAddressInformation(String userAddressOne, String country, String state, String city, String zipCode, String userMobileNumber) {
        driver.async().element()
                .type(addressOne_input, userAddressOne)
                .select(country_select, country)
                .type(state_input, state)
                .type(city_input, city)
                .type(zipcode_input, zipCode)
                .type(mobileNumber_input, userMobileNumber)
                .synchronize();
        driver.element().click(createAccount_button);
        return this;
    }

    @Step("Click on Continue Button")
    public SignupPage clickOnContinueButton() {
        driver.element().click(continueBtn_link);
        GoogleAlert.dismissAlert(driver, continueBtn_link);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate on Account Info Page")
    public SignupPage validateOnAccountInfoPage(String expectedText) {
        driver.verifyThat().element(accountInfo_h1).text().equalsIgnoringCaseSensitivity(expectedText).perform();
        return this;
    }

    @Step("Validate On Account Created")
    public SignupPage validateOnAccountCreated(String expectedResult) {
        driver.verifyThat().element(createAccount_h2).text().equalsIgnoringCaseSensitivity(expectedResult).perform();
        return this;
    }
}
