package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import page_object.RegistrationPage;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static constants.Constants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class RegistrationPageTests {

    private WebDriver driver;
    private final String browser;

    public RegistrationPageTests (String browser) {
        this.browser = browser;
    }
    public RegistrationPage registrationPage;
    String expectedErrorMessage = "Некорректный пароль";

    By error = By.className("input__error");

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
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        driver.get(REGISTRATIONPAGE);
        registrationPage = new RegistrationPage(this.driver);

    }

    @Test
    @DisplayName("Регистрация пользователя с корректными данными")
    @Description("Ввод корректных данных пользователя")
    public void registrationUserTest() {
        registrationPage.enterName(VALID_NAME)
                .enterEmail(randomCorrectEmail)
                .enterPassword(randomCorrectPassword)
                .clickRegistrationButton();

    }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    @Description("Ввод некорректного пароля")
    public void registrationUserWithIncorrectPasswordTest() {
        registrationPage.enterName(VALID_NAME)
                .enterEmail(randomCorrectEmail)
                .enterPassword(randomIncorrectPassword)
                .clickRegistrationButton()
                .errorMessage();
        String actualErrorMessage = driver.findElement(error).getText();;
        assertThat(actualErrorMessage, is(expectedErrorMessage));

    }

    @After
    public void teardown() {
        driver.quit();
    }
}
