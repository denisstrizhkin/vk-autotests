package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FieldChecker {
    final private WebDriver driver;

    public FieldChecker(WebDriver driver) {
        this.driver = driver;
    }

    public boolean fieldDisplayed(By locator) {
        if (driver.findElements(locator).isEmpty()) {
            return false;
        }
        return driver.findElement(locator).isDisplayed();
    }
}
