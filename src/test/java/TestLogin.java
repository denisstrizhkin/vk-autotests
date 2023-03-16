import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestLogin {
    private static final String TARGET_URL = "https://ok.ru/messages";

    private static final String FIELD_USERNAME = "field_email";
    private static final String FIELD_PASSWORD = "field_password";

    private static final String CORRECT_USERNAME = "usr";
    private static final String CORRECT_PASSWORD = "pass";

    private static final String WRONG_USERNAME = "random_name";
    private static final String WRONG_PASSWORD = "password";

    private static final String WRONG_CREDENTIALS_MSG = "Incorrect username and/or password";

    private static WebDriver driver;

    @BeforeAll
    public static void SetupWebDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(chromeOptions);
    }

    @Test
    public void TestCorrectPassword() {
        driver.get(TARGET_URL);

        WebElement elementUsername = driver.findElement(By.id(FIELD_USERNAME));
        WebElement elementPassword = driver.findElement(By.id(FIELD_PASSWORD));

        elementUsername.sendKeys(CORRECT_USERNAME);
        elementPassword.sendKeys(CORRECT_PASSWORD);
        elementPassword.sendKeys(Keys.ENTER);

        String currentURL = driver.getCurrentUrl();
        Assertions.assertEquals(TARGET_URL, currentURL);
    }

    @Test
    public void TestWrongPassword() {
        driver.get(TARGET_URL);

        WebElement elementUsername = driver.findElement(By.id(FIELD_USERNAME));
        WebElement elementPassword = driver.findElement(By.id(FIELD_PASSWORD));

        elementUsername.sendKeys(WRONG_USERNAME);
        elementPassword.sendKeys(WRONG_PASSWORD);
        elementPassword.sendKeys(Keys.ENTER);

        boolean isWrongCredentials = true;
        try {
            driver.findElement(By.xpath("//div[text()='"
                    + WRONG_CREDENTIALS_MSG + "']"
            ));
        } catch (InvalidSelectorException e) {
            isWrongCredentials = false;
        }

        Assertions.assertTrue(isWrongCredentials);
    }

    @AfterAll
    public static void CloseWebDriver() {
        driver.quit();
    }
}
