package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
    private SHAFT.GUI.WebDriver driver;

    private final By firstProductName = By.xpath("//a[normalize-space()='Blue Top']");

    private final By secondProductName = By.xpath("//a[normalize-space()='Men Tshirt']");

    private final By firstProductPrice = By.xpath("//td[@class='cart_price']//p[contains(text(),'Rs. 500')]");

    private final By secondProductPrice = By.xpath("//td[@class='cart_price']//p[contains(text(),'Rs. 400')]");

    private final By firstProductQuantity = By.xpath("//tr[@id='product-1']//button[@class='disabled'][normalize-space()='1']");

    private final By secondProductQuantity = By.xpath("//tr[@id='product-2']//button[@class='disabled'][normalize-space()='1']");

    private final By firstProductTotal = By.xpath("//p[@class='cart_total_price'][normalize-space()='Rs. 500']");

    private final By secondProductTotal = By.xpath("//p[@class='cart_total_price'][normalize-space()='Rs. 400']");

    public CartPage(SHAFT.GUI.WebDriver driver) {
            this.driver = driver;
    }
    @Step
    public CartPage bb (){

        return this;
    }
}
