package allure_reports;

import base.TestBase;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import listeners.AllureListeners;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.MessageFormat;

import static io.qameta.allure.Allure.step;

@Listeners({AllureListeners.class})
public class AllureReporting extends TestBase {
    static WebDriver driver;

    @BeforeClass
    public void setUp(){
        TestBase testBase = new TestBase();
        testBase.initialize();
        driver = TestBase.getDriver();
        driver.get("https://www.amazon.ca/");
    }

    @Test
    @TmsLink("28987")
    @Description("This test case helps to validate the title of page")
    public void validateTitle(){
        step("test fetching the title of the page");
        Assert.assertEquals(this.getPageTitle(), "Amazon.ca: Low Prices – Fast Shipping – Millions of Items");
    }

    @Test
    @TmsLink("88766")
    @Description("This test case helps to validate sign in link")
    public void validateSignInLink(){
        WebElement signInLink = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
        Assert.assertFalse(this.isElementPresent(signInLink, "Hello, Sign in"));
    }

    @Step("validating if element {0} is displayed on page with text {1}")
    private boolean isElementPresent(WebElement element, String value) {
        Allure.step(MessageFormat.format("validating if element {0} is displayed on page with text {1}", element, value));
        return element.isDisplayed() && element.getText().equalsIgnoreCase(value);
    }

    @Step("fetching the title of the page")
    private String getPageTitle() {
        return driver.getTitle();
    }

    @AfterClass
    public void TearDown(){
        driver.quit();
    }
}
