package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {

    private static WebDriver driver;
    private final By buttonLogin = By.linkText("Войти");

    public ForgotPasswordPage (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кнопка Войти на странице восстановления пароля")
    public LoginPage buttonLogin() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.Modal_modal_overlay__x2ZCr")));
        wait.until(ExpectedConditions.elementToBeClickable(buttonLogin));
        driver.findElement(buttonLogin).click();
        return new LoginPage();
    }
}
