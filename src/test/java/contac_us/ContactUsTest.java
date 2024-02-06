package contac_us;

import com.gizasystems.automationexercise.pages.ContactUsPage;
import com.gizasystems.automationexercise.pages.HomePage;
import com.gizasystems.automationexercise.pages.NavigationBar;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactUsTest {

    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;


    @Test(description = "Contact Us")
    @Description("Given that I click on contact Us Link, When I enter valid data, And press Submit , Then The message submitted")
    public void contactUs() {
        new NavigationBar(driver)
                .clickOnContactUsLink();
        new ContactUsPage(driver)
                .validateOnContactUsPage()
                .enterContactUsInformation(testData.getTestData("Name"),testData.getTestData("Email"),testData.getTestData("Subject"),testData.getTestData("MessageTxt"))
                .clickOnSubmitButton()
                .validateOnContactInfoSubmitted(testData.getTestData("Messages.SubmittedMsg"));
    }
    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
    }

    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/ContactUs.json");
    }
    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
