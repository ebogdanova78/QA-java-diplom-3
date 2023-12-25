package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_object.ForgotPasswordPage;
import page_object.LoginPage;
import page_object.MainPage;
import page_object.RegistrationPage;
import org.junit.runner.RunWith;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static constants.Constants.*;

@RunWith(Parameterized.class)
public class LoginPageTests {

    private WebDriver driver;
    private final String browser;

    public LoginPageTests (String browser) {
        this.browser = browser;
    }
    public RegistrationPage registrationPage;
    public  ForgotPasswordPage forgotPasswordPage;

    public MainPage mainPage;
    public LoginPage loginPage;

    @Parameterized.Parameters
    public static Collection<String> browsers() {
        return Arrays.asList("chrome", "firefox");
    }

    @Before
    public void setUp() {

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }

        driver.get(REGISTRATIONPAGE);
        registrationPage = new RegistrationPage(this.driver);
        registrationPage.enterName(USER_NAME)
                .enterEmail(USER_EMAIL)
                .enterPassword(USER_PASSWORD)
                .clickRegistrationButton();

    }

    @Test
    @DisplayName("Вход по кнопке Войти")
    @Description("Логин через кнопку Войти в аккаунт на главной странице")
    public void loginWithMainPageTest() {
        driver.get(MAINPAGE);
        mainPage = new MainPage(this.driver);
        loginPage = new LoginPage(this.driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        mainPage.pressLogin()
                .enterEmail(USER_EMAIL)
                .enterPassword(USER_PASSWORD);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                loginPage.pressButtonLogin()
                .checkButtonOrder();
    }

    @Test
    @DisplayName("Вход по кнопке Личный кабинет")
    @Description("Логин через кнопку Личный кабинет на главной странице")
    public void loginWithMainPagePersonAccountTest() {
        driver.get(MAINPAGE);
        mainPage = new MainPage(this.driver);
        loginPage = new LoginPage(this.driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        mainPage.buttonPersonalAccount()
                .enterEmail(USER_EMAIL)
                .enterPassword(USER_PASSWORD);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                loginPage.pressButtonLogin()
                .checkButtonOrder();
    }

    @Test
    @DisplayName("Вход в форме регистрации")
    @Description("Логин через кнопку Войти на странице регистрации")
    public void loginWithRegistrationPageTest() {
        driver.get(REGISTRATIONPAGE);
        registrationPage = new RegistrationPage(this.driver);
        mainPage = new MainPage(this.driver);
        loginPage = new LoginPage(this.driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        registrationPage.buttonLogin()
                .enterEmail(USER_EMAIL)
                .enterPassword(USER_PASSWORD);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                loginPage.pressButtonLogin()
                .checkButtonOrder();
    }

    @Test
    @DisplayName("Вход в форме восстановления пароля")
    @Description("Логин через кнопку на странице восстановления пароля")
    public void loginWithForgotPageTest() {
        driver.get(FORGOTPASSWORDPAGE);
        forgotPasswordPage = new ForgotPasswordPage(this.driver);
        mainPage = new MainPage(this.driver);
        loginPage = new LoginPage(this.driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        forgotPasswordPage.buttonLogin()
                .enterEmail(USER_EMAIL)
                .enterPassword(USER_PASSWORD);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                loginPage.pressButtonLogin()
                .checkButtonOrder();
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
