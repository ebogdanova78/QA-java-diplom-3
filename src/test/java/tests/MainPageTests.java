package tests;

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
import io.github.bonigarcia.wdm.WebDriverManager;
import page_object.MainPage;


import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static constants.Constants.MAINPAGE;

@RunWith(Parameterized.class)
public class MainPageTests {

    private WebDriver driver;
    private final String browser;
    public MainPage mainPage;

    public MainPageTests (String browser) {
        this.browser = browser;
    }
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

        driver.get(MAINPAGE);
        mainPage = new MainPage(this.driver);

    }

    @Test
    @DisplayName("Секция Булки")
    @Description("Выбор раздела Булки и проверка активности заголовока")
    public void chooseBunSection () {
        mainPage.chooseFillings(); // без этого не выбрать раздел Булки, так он стоит по умолчанию
        mainPage.checkActive("Булки");
        mainPage.chooseBuns()
                .checkBunsVisible();

    }

    @Test
    @DisplayName("Раздел Начинки")
    @Description("Выбир раздела Начинки и проверка активности заголовока")
    public void chooseFillingsSection () {
          mainPage.checkActive("Начинки");
          mainPage.chooseFillings()
                  .checkFillingsVisible();
    }

    @Test
    @DisplayName("Раздел Соусы")
    @Description("Выбир раздела Соусы и проверка активности заголовока")
    public void chooseSaucesSection () {
             mainPage.checkActive("Соусы");
             mainPage.chooseSauces()
                     .checkSaucesVisible();
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
