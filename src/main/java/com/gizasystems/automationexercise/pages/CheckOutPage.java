package com.gizasystems.automationexercise.pages;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
public class CheckOutPage {
    private SHAFT.GUI.WebDriver driver;
    // Locators
    private String url = System.getProperty("baseUrl") + "/checkout";
    private final By FirstName = By.xpath("//ul[@class = \"address item box\"]//li[@class=\"address_firstname address_lastname\"]");
    private  final By Address1 = By.xpath("//ul[@class = \"address item box\"]//li[@class=\"address_address1 address_address2\"][2]");
    private  final By City = By.xpath("//ul[@class=\"address item box\"]//li[@class=\"address_country_name\"]");
    private final By PlaceOrder_Btn = By.xpath("//div//a[@class=\"btn btn-default check_out\"]");
    private  final By CommentTextArea = By.className("form-control");
    public CheckOutPage (SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }
    public CheckOutPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    @Step (" Enter description in comment text area and click 'Place Order'")
    public CheckOutPage enteringDescriptionInCommentArea (String Text){
        driver.element().type(CommentTextArea,Text);
        driver.element().click(PlaceOrder_Btn);
        return this;
    }

    @Step ("Verifiying address details")
    public CheckOutPage verififyingAddressDetails(String FirstName , String Address , String City){
        driver.verifyThat().element(this.FirstName).text().equalsIgnoringCaseSensitivity(FirstName).perform();
        driver.verifyThat().element(Address1).text().equalsIgnoringCaseSensitivity(Address).perform();
        driver.verifyThat().element(this.City).text().equalsIgnoringCaseSensitivity(City).perform();
        return  this;
    }
}