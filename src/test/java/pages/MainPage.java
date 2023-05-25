package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.FieldChecker;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final By navLocator = By.className("nav-side");
    private final By loginErrorLocator = By.className("login_error");
    private final By userDropDownButtonLocator = By.xpath("//div[contains(@class, 'toolbar_ucard') and @role='button' and @aria-controls='user-dropdown-menu']");
    private final By userDropDownMenuLocator = By.id("user-dropdown-menu");
    private final By languageButtonLocator = By.xpath("//div[contains(@class, 'toolbar_accounts-menu')]//ul//li[position()=2]//a");
    private final By languageMenuLocator = By.id("hook_Form_PopLayerChooseNewLanguageForm");
    private final By closeLanguageMenuLocator = By.id("nohook_modal_close");

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
        final var elementUserDropDownButton = getUserDropDownButton();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(elementUserDropDownButton));
        elementUserDropDownButton.click();
    }

    public boolean isUserDropDownVisible() {
        final var elementUserDropDown = getUserDropDownMenu();
        return elementUserDropDown.isDisplayed();
    }

    public void openLanguageMenu() {
        final var elementLanguageMenu = getLanguageButton();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(elementLanguageMenu));
        elementLanguageMenu.click();
    }

    public boolean isLanguageMenuVisible() {
        final var checker = new FieldChecker(driver);
        return checker.fieldExists(languageMenuLocator);
    }

    public String getCurrentLanguage() {
        final var langBtn = getLanguageButton();
        final var langSpan = langBtn.findElement(By.xpath("./div//span"));
        return langSpan.getText();
    }

    public void setLanguage(String lang) {
        final var langMenu = getLanguageMenu();
        final var langBtn = langMenu.findElement(By.xpath("./form//div//a[text()='" + lang + "']"));
        langBtn.click();
    }
    
    public void closeLanguageMenu() {
        final var element = driver.findElement(closeLanguageMenuLocator);
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}
