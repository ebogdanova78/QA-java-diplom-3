package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    public static WebDriver driver;
    private final By emailField = By.xpath(".//fieldset[1]//input");
    private final By passwordField = By.xpath(".//fieldset[2]//input");
    private final By buttonLogin = By.xpath ("//button[text() = 'Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public LoginPage() {

    }

    @Step("Кнопа Войти на странице Логина")
    public MainPage pressButtonLogin() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.Modal_modal_overlay__x2ZCr")));
        wait.until(ExpectedConditions.elementToBeClickable(buttonLogin));
        driver.findElement(buttonLogin).click();
        return new MainPage();
    }

    @Step("Ввод в поле Email")
    public LoginPage enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Ввод в поле Пароль")
    public LoginPage enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step("Проверка видимости кнопки Войти на форме Логина")
    public boolean checkVisibleLogin(){
        return driver.findElement(buttonLogin).isDisplayed();
    }

}
