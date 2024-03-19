package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RegisterWhileCheckoutPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    //Locators
private final By checkoutBodyMessage_div= By.cssSelector("div.modal-content > div > h4");
private final By registerLoginLink_div=By.xpath("//p[@class='text-center']/a[@href='/login']/u");
    // Constructor
    public RegisterWhileCheckoutPage(SHAFT.GUI.WebDriver driver){
        this.driver=driver;
    }
    //////////////////// Actions \\\\\\\\\\
    @Step("Click on Register/Login button")
    public RegisterWhileCheckoutPage clickOnRegisterLoginBtn(){
        driver.element().click(registerLoginLink_div);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\
    @Step("Verify the checkout pop up is displayed")
    public RegisterWhileCheckoutPage verifyCheckoutPopUpDisplayed(String expectedMessage){
        driver.element().assertThat(checkoutBodyMessage_div).text().contains(expectedMessage).perform();
        return this;
    }
}
