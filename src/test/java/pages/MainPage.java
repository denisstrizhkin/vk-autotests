package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.FieldChecker;
import util.SafeWrapper;

import java.time.Duration;
import java.util.function.Function;

public class MainPage extends LoadableComponent<MainPage> {
    private final WebDriver driver;
    private final LoginPage parent;
    private final String username;
    private final String password;

    private final SafeWrapper sWrapper;

    private final By navLocator = By.className("nav-side");
    private final By userDropDownButtonLocator = By.xpath("//div[contains(@class, 'toolbar_ucard') and @role='button' and @aria-controls='user-dropdown-menu']");
    private final By userDropDownMenuLocator = By.id("user-dropdown-menu");
    private final By languageButtonLocator = By.xpath("//div[contains(@class, 'toolbar_accounts-menu')]//ul//li[position()=2]//a");
    private final By languageMenuLocator = By.id("hook_Form_PopLayerChooseNewLanguageForm");
    private final By closeLanguageMenuLocator = By.id("nohook_modal_close");
    private final By languageStrLocator = By.xpath("./div//span");

    public MainPage(WebDriver driver, LoginPage parent, String username, String password) {
        this.driver = driver;
        this.parent = parent;
        this.username = username;
        this.password = password;
        this.sWrapper = new SafeWrapper(this.driver);
    }

    public WebElement getUserDropDownButton() {
        return driver.findElement(userDropDownButtonLocator);
    }

    public WebElement getUserDropDownMenu() {
        return driver.findElement(userDropDownMenuLocator);
    }

    public WebElement getLanguageButton() {
        return driver.findElement(languageButtonLocator);
    }

    public WebElement getLanguageMenu() {
        return driver.findElement(languageMenuLocator);
    }

    public void openUserDropDown() {
        sWrapper.Click(getUserDropDownButton());
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(getUserDropDownMenu()));
    }

    public void openLanguageMenu() {
        sWrapper.Click(getLanguageButton());
        new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .until((Function<WebDriver, WebElement>) driver -> driver.findElement(languageMenuLocator));
    }

    public boolean checkLanguageMenu() {
        final var checker = new FieldChecker(driver);
        return checker.fieldDisplayed(languageMenuLocator);
    }

    public boolean checkUserDropDownMenu() {
        final var checker = new FieldChecker(driver);
        return checker.fieldDisplayed(userDropDownMenuLocator);
    }

    public String getCurrentLanguage() {
        return getLanguageButton().findElement(languageStrLocator).getText();
    }

    public void setLanguage(String lang) {
        final var langMenu = getLanguageMenu();
        final var langBtn = langMenu.findElement(By.xpath("./form//div//a[text()='" + lang + "']"));
        sWrapper.Click(langBtn);
    }
    
    public void closeLanguageMenu() {
        sWrapper.Click(driver.findElement(closeLanguageMenuLocator));
    }

    @Override
    protected void load() {
        parent.get();
        sWrapper.SendKeys(parent.getUsernameField(), username);
        sWrapper.SendKeys(parent.getPasswordField(), password);
        sWrapper.SendKeys(parent.getPasswordField(), Keys.ENTER);
    }

    @Override
    protected void isLoaded() throws Error {
        final var checker = new FieldChecker(driver);
        Assertions.assertTrue(checker.fieldDisplayed(navLocator));
    }
}
