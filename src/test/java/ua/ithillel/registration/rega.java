package ua.ithillel.registration;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ua.ithillel.browser.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import ua.ithillel.utils.ConfigProvider;
import ua.ithillel.browser.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver.Navigation;

import java.time.Duration;


public class rega {
    private WebDriver driver;
    Faker faker = new Faker();
    String name = faker.name().name();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String Pokemon = faker.pokemon().name();

    @BeforeTest
    public void start() {
        driver = WebDriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void regTest() {
        driver.navigate().to(ConfigProvider.BASE_URL);

        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("register-username-modal")).sendKeys(name);

        driver.findElement(By.id("register-first-modal")).sendKeys(firstName);

        driver.findElement(By.id("register-last-modal")).sendKeys(lastName);

        driver.findElement(By.id("register-email-modal")).sendKeys((firstName) + (lastName) + "@gmail.com");
        driver.findElement(By.id("register-password-modal")).sendKeys(Pokemon);
        driver.findElement(By.cssSelector(".text-center:nth-child(6) > .btn")).click();
        Assert.assertEquals(driver.findElement(By.id("registration-message")).getText(),"Registration and login successful.");

    }

    @AfterTest
    public void AfterTest() {
        driver.close();
    }

}
