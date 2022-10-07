package ua.ithillel.registration;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ua.ithillel.browser.WebDriverFactory;
import ua.ithillel.utils.ConfigProvider;


import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions.*;

public class login {

    private WebDriver driver;
    private WebDriverWait wait;

    Faker faker = new Faker();
    String name = faker.name().name();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String Pokemon = faker.pokemon().name();

    @BeforeTest
    public void start() {
        driver = WebDriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to(ConfigProvider.BASE_URL);

        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("register-username-modal")).sendKeys(name);

        driver.findElement(By.id("register-first-modal")).sendKeys(firstName);

        driver.findElement(By.id("register-last-modal")).sendKeys(lastName);

        driver.findElement(By.id("register-email-modal")).sendKeys(firstName + "@gmail.com");
        driver.findElement(By.id("register-password-modal")).sendKeys(Pokemon);
        driver.findElement(By.cssSelector(".text-center:nth-child(6) > .btn")).click();

        driver.findElement(By.linkText("Logout")).click();
    }

    @Test
    public void loginTest() {


        driver.findElement(By.id("Login")).click();

        driver.findElement(By.id("username-modal")).sendKeys(name);

        driver.findElement(By.id("password-modal")).sendKeys(Pokemon);
        driver.findElement(By.xpath("//button[contains(.,\' Log in\')]")).click();

        Assert.assertEquals(driver.findElement(By.id("login-message")).getText(), "Login successful.");

    }

    @AfterTest
    public void AfterTest() {
        driver.close();
    }


}

