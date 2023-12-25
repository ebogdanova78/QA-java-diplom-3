package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import page_object.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static constants.Constants.*;

@RunWith(Parameterized.class)
public class PersonalAccountPageTests  {

    private WebDriver driver;
    private final String browser;

    public MainPage mainPage;
    public LoginPage loginPage;

    public PersonalAccountPageTests (String browser) {
        this.browser = browser;
    }
    @Parameterized.Parameters
    public static Collection<String> browsers() {
        return Arrays.asList("chrome", "firefox");
    }

    @Before
    @DisplayName("Логин под учетной записью")
    public void login() {
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        driver.get(MAINPAGE);
        mainPage = new MainPage(this.driver);
        loginPage = new LoginPage(this.driver);
        mainPage.pressLogin()
                .enterEmail(USER_EMAIL)
                .enterPassword(USER_PASSWORD)
                .pressButtonLogin();
    }

    @Test
    @DisplayName("Открытие личного кабинета")
    @Description("Проверка открытия личного кабинета и доступности кнопки Выхода")
    public void personalAccountTest() {
          PersonalAccountPage personalAccountPage = new PersonalAccountPage(this.driver);
          driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
          mainPage.buttonPersonalAccountWithAuth();
          personalAccountPage.checkButtonExit();
    }

    @Test
    @DisplayName("Переход по кнопке Конструктор")
    @Description("Проверка перехода на главную страницу после нажатия на кнопку Конструктор")
    public void openMainPageButtonConstructorTest() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(this.driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        mainPage.buttonPersonalAccountWithAuth()
                .buttonConstructor()
                .checkLogoVisible();
    }

    @Test
    @DisplayName("Переход  по нажатию на логотип")
    @Description("Проверка переход на главную страницу после нажатия на кнопку логотипа")
    public void openMainPageButtonLogoTest() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(this.driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        mainPage.buttonPersonalAccountWithAuth()
                .logo()
                .checkLogoVisible();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка переход на страницу Логина после нажатия на кнопку Выхода")
    public void openLoginPageButtonExitTest() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(this.driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        mainPage.buttonPersonalAccountWithAuth()
                .buttonExit()
                .checkVisibleLogin();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
