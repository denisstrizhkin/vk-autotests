package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import util.FieldChecker;
import util.OKInfo;

public class LoginPage extends LoadableComponent<LoginPage> {
    private final WebDriver driver;
    private final By usernameLocator = By.id("field_email");
    private final By passwordLocator = By.id("field_password");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkUsernameField() {
        final var checker = new FieldChecker(driver);
        return checker.fieldDisplayed(usernameLocator);
    }

    public boolean checkPasswordField() {
        final var checker = new FieldChecker(driver);
        return checker.fieldDisplayed(passwordLocator);
    }

    public WebElement getUsernameField() {
        return driver.findElement(usernameLocator);
    }

    public WebElement getPasswordField() {
        return driver.findElement(passwordLocator);
    }

    @Override
    protected void load() {
        driver.get(OKInfo.BASE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        Assertions.assertEquals(OKInfo.BASE_URL, driver.getCurrentUrl());
    }
}
