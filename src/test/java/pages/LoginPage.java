package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.FieldChecker;
import util.OKInfo;
import util.PageChecker;

public class LoginPage {
    private final String url = OKInfo.BASE_URL;
    private final WebDriver driver;
    private final boolean isCheck;
    private final By usernameLocator = By.id(OKInfo.LOGIN_USERNAME_FIELD_ID);
    private final By passwordLocator = By.id(OKInfo.LOGIN_PASSWORD_FIELD_ID);

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        final var checker = new PageChecker(driver);
        isCheck = checker.pageLoadedSuccessfully(getUrl());
    }

    public String getUrl() {
        return url;
    }

    public boolean checkUsernameField() {
        final var checker = new FieldChecker(driver);
        return checker.fieldExists(usernameLocator);
    }

    public boolean checkPasswordField() {
        final var checker = new FieldChecker(driver);
        return checker.fieldExists(passwordLocator);
    }

    public boolean check() {
        return isCheck;
    }

    public MainPage login(String username, String password) {
        final var elementUsername = driver.findElement(usernameLocator);
        final var elementPassword = driver.findElement(passwordLocator);

        elementUsername.sendKeys(username);
        elementPassword.sendKeys(password);
        elementPassword.sendKeys(Keys.ENTER);

        return new MainPage(driver);
    }
}
