package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.FieldChecker;

public class MainPage {
    private final WebDriver driver;
    private final By navLocator = By.className("nav-side");
    private final By loginErrorLocator = By.className("login_error");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLogin() {
        final var checker = new FieldChecker(driver);
        return checker.fieldExists(navLocator);
    }

    public boolean isFail() {
        final var elementLoginError = driver.findElement(loginErrorLocator);
        return elementLoginError.getSize().height > 0;
    }
}
