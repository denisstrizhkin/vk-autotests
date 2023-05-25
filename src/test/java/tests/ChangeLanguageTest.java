package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import util.OKInfo;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ChangeLanguageTest {
    private static WebDriver driver;
    private static MainPage mainPage;

    @BeforeAll
    public static void SetupWebDriver() {
        WebDriverManager.chromedriver().setup();

        final var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(chromeOptions);
        mainPage = new LoginPage(driver).login(OKInfo.USERNAME, OKInfo.PASSWORD);
    }

    public void OpenDropDown() {
        mainPage.openUserDropDown();
        final var wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(mainPage.getUserDropDownMenu()));
    }

    public void OpenLangMenu() throws InterruptedException {
        OpenDropDown();
        mainPage.openLanguageMenu();
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    @DisplayName("Test opening user dropdown menu")
    public void TestUserDropDownMenu() {
        Assertions.assertFalse(mainPage.isUserDropDownVisible());
        OpenDropDown();
        Assertions.assertTrue(mainPage.isUserDropDownVisible());
    }

    @Test
    @DisplayName("Test opening language menu")
    public void TestUserLanguageMenu() throws InterruptedException {
        OpenLangMenu();
        Assertions.assertTrue(mainPage.isLanguageMenuVisible());
    }

    @Test
    @DisplayName("Test language switching")
    public void TestEnglishLanguage() throws InterruptedException {
        OpenDropDown();
        final var curLang = mainPage.getCurrentLanguage();
        mainPage.openLanguageMenu();
        TimeUnit.SECONDS.sleep(1);

        if (!curLang.equals("English")) {
            mainPage.setLanguage("English");
        } else {
            mainPage.closeLanguageMenu();
        }

        OpenDropDown();
        Assertions.assertEquals("English", mainPage.getCurrentLanguage());

        mainPage.openLanguageMenu();
        TimeUnit.SECONDS.sleep(1);
        mainPage.setLanguage("Русский");

        OpenDropDown();
        Assertions.assertEquals("Русский", mainPage.getCurrentLanguage());

        mainPage.openLanguageMenu();
        TimeUnit.SECONDS.sleep(1);
        mainPage.setLanguage("English");

        OpenDropDown();
        Assertions.assertEquals("English", mainPage.getCurrentLanguage());
    }

    @AfterAll
    public static void CloseWebDriver() {
        driver.quit();
    }
}
