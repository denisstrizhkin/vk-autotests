package tests;

import org.junit.jupiter.api.*;
import pages.MainPage;
import util.OKInfo;

public class ChangeLanguageTest extends BaseTest{

    private MainPage getMainPage() {
        final var mainPage = new MainPage(driver, loginPage, OKInfo.USERNAME, OKInfo.PASSWORD);
        mainPage.get();
        return  mainPage;
    }

    @Test
    @DisplayName("Test opening user dropdown menu")
    public void TestUserDropDownMenu() {
        final var mainPage = getMainPage();
        Assertions.assertFalse(mainPage.checkUserDropDownMenu());
        mainPage.openUserDropDown();
        Assertions.assertTrue(mainPage.checkUserDropDownMenu());
    }

    @Test
    @DisplayName("Test opening language menu")
    public void TestUserLanguageMenu() {
        final var mainPage = getMainPage();
        Assertions.assertFalse(mainPage.checkLanguageMenu());
        mainPage.openUserDropDown();
        Assertions.assertFalse(mainPage.checkLanguageMenu());
        mainPage.openLanguageMenu();
        Assertions.assertTrue(mainPage.checkLanguageMenu());
    }

    @Test
    @DisplayName("Test language switching")
    public void TestEnglishLanguage() {
        final var mainPage = getMainPage();

        mainPage.openUserDropDown();
        final var curLang = mainPage.getCurrentLanguage();
        mainPage.openLanguageMenu();

        if (!curLang.equals("English")) {
            mainPage.setLanguage("English");
        } else {
            mainPage.closeLanguageMenu();
        }

        mainPage.openUserDropDown();
        Assertions.assertEquals("English", mainPage.getCurrentLanguage());

        mainPage.openLanguageMenu();
        mainPage.setLanguage("Русский");

        mainPage.openUserDropDown();
        Assertions.assertEquals("Русский", mainPage.getCurrentLanguage());

        mainPage.openLanguageMenu();
        mainPage.setLanguage("English");

        mainPage.openUserDropDown();
        Assertions.assertEquals("English", mainPage.getCurrentLanguage());
    }
}
