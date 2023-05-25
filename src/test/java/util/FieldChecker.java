package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FieldChecker {
    private WebDriver driver;

    public FieldChecker(WebDriver driver) {
        this.driver = driver;
    }

    public boolean fieldExists(By locator) {
        return driver.findElements(locator).size() > 0;
    }
}
