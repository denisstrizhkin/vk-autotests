package tests;

import org.junit.jupiter.api.*;
import org.opentest4j.AssertionFailedError;
import pages.MainPage;
import util.OKInfo;

public class LoginTest extends BaseTest {
    @Test
    @DisplayName("Test authorization URL")
    public void TestURL() {
        loginPage.get();
    }

    @Test
    @DisplayName("Test login form has username and password fields")
    public void TestAuthFieldsExist() {
        loginPage.get();
        Assertions.assertTrue(loginPage.checkUsernameField());
        Assertions.assertTrue(loginPage.checkPasswordField());
    }

    @Test
    @DisplayName("Test able to authorize with correct credentials")
    public void TestCorrectPassword() {
        final var mainPage = new MainPage(driver, loginPage, OKInfo.USERNAME, OKInfo.PASSWORD);
        mainPage.get();
    }

    @Test
    @DisplayName("Test unable to authorize with wrong credentials")
    public void TestWrongPassword() {
        final var mainPage = new MainPage(driver, loginPage, OKInfo.USERNAME, OKInfo.PASSWORD + 'a');
        Assertions.assertThrows(AssertionFailedError.class, mainPage::get);
    }
}
