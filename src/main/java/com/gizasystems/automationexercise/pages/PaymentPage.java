package com.gizasystems.automationexercise.pages;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
public class PaymentPage {
    private SHAFT.GUI.WebDriver driver;
    private String url = System.getProperty("baseUrl") + "/payment";
    private final By CardNameInput = By.xpath("//div//input[@class=\"form-control\"]");
    private final By CardNumberInput = By.xpath("//div//input[@class=\"form-control card-number\"]");
    private final By CVCInput = By.xpath("//div//input[@class=\"form-control card-cvc\"]");
    private final By CardExpMonthInput = By.xpath("//div//input[@class=\"form-control card-expiry-month\"]");
    private final By CardExpYearInput = By.xpath("//div//input[@class=\"form-control card-expiry-year\"]");
    private final By PayOrder_Btn = By.xpath("//button[@class=\"form-control btn btn-primary submit-button\"]");
    private final By OrderPlacedSucessMessage = By.xpath("//p[@style=\"font-size: 20px; font-family: garamond;\"]");
    private final By Delete_Btn = By.cssSelector("a[href='/delete_account']");

    public PaymentPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public PaymentPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    @Step("Enter payment details: Name on Card, Card Number, CVC, Expiration date")
    public PaymentPage enterPaymentDetails(String User , String Number , String CVC , String ExpMonth , String ExpYear) {
        driver.element().type(CardNameInput, User);
        driver.element().type(CardNumberInput, Number);
        driver.element().type(CVCInput, CVC);
        driver.element().type(CardExpMonthInput, ExpMonth);
        driver.element().type(CardExpYearInput, ExpYear);
        return this;
    }
    @Step("Click 'Pay and Confirm Order' button")
    public PaymentPage clickOnPayOrder() {
        driver.element().click(PayOrder_Btn);
        return this;
    }

    @Step("Click 'Delete Account' button")
    public PaymentPage DelteAccount() {
        driver.element().click(Delete_Btn);
        return this;
    }

    @Step("Verify success message 'Your order has been placed successfully!'")
    public PaymentPage VerifySucessMessage(String Message){
        driver.verifyThat().element(OrderPlacedSucessMessage).text().isEqualTo(Message).perform();
        return this;
    }
}