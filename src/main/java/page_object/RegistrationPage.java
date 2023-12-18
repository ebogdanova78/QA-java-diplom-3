package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private static WebDriver driver;
    By nameField = By.xpath(".//fieldset[1]//input");
    By emailField = By.xpath(".//fieldset[2]//input");
    By passwordField = By.xpath(".//fieldset[3]//input");
    By registrationButton = By.xpath("//button[text() = 'Зарегистрироваться']");
    By error = By.className("input__error");
    By loginButton = By.linkText("Войти");;

    public RegistrationPage (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение поля Имя")
    public RegistrationPage enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    @Step("Заполнение поля Email")
    public RegistrationPage enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Заполнение поля Пароль")
    public RegistrationPage enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step("Кнопка Зарегистрироваться")
    public RegistrationPage clickRegistrationButton() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.Modal_modal_overlay__x2ZCr")));
        wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
        driver.findElement(registrationButton).click();
        return this;
    }

    @Step("Отображение ошибки")
    public String errorMessage() {
        return driver.findElement(error).getText();
    }

    @Step("Кнопка Войти на форме регистрации")
    public LoginPage buttonLogin() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.Modal_modal_overlay__x2ZCr")));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
        return new LoginPage();
    }
}
