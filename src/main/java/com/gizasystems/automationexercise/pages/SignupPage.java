package com.gizasystems.automationexercise.pages;

import com.gizasystems.automationexercise.utils.GoogleAlert;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

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
        driver.element().click(gender_input(gender));
        driver.element().type(password_input, pass);

        driver.element().select(days_select, day);
        driver.element().select(months_select, month);
        driver.element().select(years_select, year);

        driver.element().type(firstName_input, userFirstName);
        driver.element().type(lastName_input, userLastName);
        return this;
    }

    @Step("Enter Address Information")
    public SignupPage enterAddressInformation(String userAddressOne, String country, String state, String city, String zipCode, String userMobileNumber) {
        driver.element().type(addressOne_input, userAddressOne);
        driver.element().select(country_select, country);
        driver.element().type(state_input, state);
        driver.element().type(city_input, city);
        driver.element().type(zipcode_input, zipCode);
        driver.element().type(mobileNumber_input, userMobileNumber);
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
        driver.assertThat().element(accountInfo_h1).text().isEqualTo(expectedText).perform();
        return this;
    }

    @Step("Validate On Account Created")
    public SignupPage validateOnAccountCreated(String expectedResult) {
        driver.assertThat().element(createAccount_h2).text().isEqualTo(expectedResult).perform();
        return this;
    }









}
