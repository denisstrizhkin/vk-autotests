package tests;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import util.OKInfo;

public class LoginTest {
    private static WebDriver driver;

    @BeforeAll
    public static void SetupWebDriver() {
        WebDriverManager.chromedriver().setup();

        final var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(chromeOptions);
    }

    @Test
    @DisplayName("Test authorization URL")
    public void TestURL() {
        final var page = new LoginPage(driver);
        Assertions.assertTrue(page.check());
    }

    @Test
    @DisplayName("Test login form has username field")
    public void TestUsernameFieldExists() {
        final var page = new LoginPage(driver);
        Assertions.assertTrue(page.checkUsernameField());
    }

    @Test
    @DisplayName("Test login form has password field")
    public void TestPasswordFieldExists() {
        final var page = new LoginPage(driver);
        Assertions.assertTrue(page.checkPasswordField());
    }

    @Test
    @DisplayName("Test able to authorize with correct credentials")
    public void TestCorrectPassword() {
        final var pageLogin = new LoginPage(driver);
        final var pageMain = pageLogin.login(OKInfo.USERNAME, OKInfo.PASSWORD);
        Assertions.assertTrue(pageMain.isLogin());
    }

    @Test
    @DisplayName("Test unable to authorize with wrong credentials")
    public void TestWrongPassword() {
        final var pageLogin = new LoginPage(driver);
        final var pageMain = pageLogin.login(OKInfo.USERNAME, OKInfo.PASSWORD + "aaa");
        Assertions.assertTrue(pageMain.isFail());
    }

    @AfterAll
    public static void CloseWebDriver() {
        driver.quit();
    }
}
