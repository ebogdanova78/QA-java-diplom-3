package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {

    public static By buttonExit = By.xpath(".//button[text()='Выход']");
    public static By buttonConstructor = By.linkText("Конструктор");
    public static By logo = By.className("AppHeader_header__logo__2D0X2");
    private static WebDriver driver;

    public PersonalAccountPage (WebDriver driver) {
        this.driver = driver;
    }
    public PersonalAccountPage() {
    }

    @Step("Проверка наличия доступности кнопки Выход в личном кабинете")
    public boolean checkButtonExit() {
        return driver.findElement(buttonExit).isEnabled();
    }

    @Step("Кнопка Выход")
    public LoginPage buttonExit() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.Modal_modal_overlay__x2ZCr")));
        wait.until(ExpectedConditions.elementToBeClickable(buttonExit));
        driver.findElement(buttonExit).click();
        return new LoginPage();
    }

    @Step("Кнопка Конструктор в Личном кабинете")
    public MainPage buttonConstructor() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.Modal_modal_overlay__x2ZCr")));
        wait.until(ExpectedConditions.elementToBeClickable(buttonConstructor));
        driver.findElement(buttonConstructor).click();
        return new MainPage();
    }

    @Step("Логотип")
    public MainPage logo() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.Modal_modal_overlay__x2ZCr")));
        wait.until(ExpectedConditions.elementToBeClickable(logo));
        driver.findElement(logo).click();
        return new MainPage();
    }

}
