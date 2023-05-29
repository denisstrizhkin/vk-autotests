package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SafeWrapper {
    private final WebDriver driver;

    public SafeWrapper(WebDriver driver) {
        this.driver = driver;
    }
    public void SendKeys(WebElement el, CharSequence cs) {
        wait(el);
        el.sendKeys(cs);
    }

    public void Click(WebElement el) {
        wait(el);
        el.click();
    }

    private void wait(WebElement el) {
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(el));
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOf(el));
    }
}
