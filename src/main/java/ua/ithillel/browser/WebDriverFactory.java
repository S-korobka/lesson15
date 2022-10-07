package ua.ithillel.browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ua.ithillel.utils.ConfigProvider;


public class WebDriverFactory {
    private static WebDriver driver;


    public static WebDriver getDriver(){
        driver = getDriver(Browser.valueOf(ConfigProvider.BROWSER.toUpperCase()));

        return driver;
    }

    private static WebDriver getDriver(Browser browser){
        switch (browser){
            case CHROME:
                return getChromeDriver();
            case FIREFOX:
                return getFirefoxDriver();
            default:
                throw new IllegalArgumentException("Wrong browser type provided.Choices are: chrome, firefox ");
        }
    }
    public static WebDriver getChromeDriver() {
        if (driver==null){
            WebDriverManager.chromedriver().setup();
            driver= new ChromeDriver();
        }
        return driver;
    }
    public static WebDriver getFirefoxDriver() {
        if (driver==null){
            WebDriverManager.firefoxdriver().setup();
            driver= new ChromeDriver();
        }
        return driver;
    }
}

