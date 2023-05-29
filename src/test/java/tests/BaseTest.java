package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;

public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeEach
    public void SetupWebDriver() {
        WebDriverManager.chromedriver().setup();

        final var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(chromeOptions);
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void CloseWebDriver() {
        driver.quit();
    }
}
