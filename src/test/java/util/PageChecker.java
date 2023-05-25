package util;

import org.openqa.selenium.WebDriver;

public class PageChecker {
    private final WebDriver driver;

    public PageChecker(WebDriver driver) {
        this.driver = driver;
    }

    public boolean pageLoadedSuccessfully(String url) {
        driver.get(url);
        return driver.getCurrentUrl().equals(url);
    }
}
