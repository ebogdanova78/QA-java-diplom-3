package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private static WebDriver driver;

    By Title = By.xpath("//h1[contains(@class, 'text_type_main-large')]");
    By bunsButton = By.xpath(".//div/span[text()='Булки']");
    By saucesButton = By.xpath(".//div/span[text()='Соусы']");
    By fillingsButton = By.xpath(".//div/span[text()='Начинки']");
    By activeSection = By.className("tab_tab_type_current__2BEPc");
    By buns = By.xpath("//h2[.='Булки']");
    By sauces = By.xpath("//h2[.='Соусы']");
    By fillings = By.xpath("//h2[.='Начинки']");
    public static By personalAccount = By.linkText("Личный Кабинет");

    By login = By.xpath("//*[.='Войти в аккаунт']");

    By buttonOrder = By.xpath("//*[.='Оформить заказ']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage() {
    }

    @Step("Проверка на видимость заголовка Соберите бургер")
    public boolean checkLogoVisible() {
        return driver.findElement(Title).isDisplayed();
    }

    @Step("Выбор раздела Булки")
    public MainPage chooseBuns() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(bunsButton));
        driver.findElement(bunsButton).click();
        return this;
    }

    @Step("Выбор раздела Coусы")
    public MainPage chooseSauces() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(saucesButton));
        driver.findElement(saucesButton).click();
        return this;
    }

    @Step("Выбор раздела Начинки")
    public MainPage chooseFillings() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(fillingsButton));
        driver.findElement(fillingsButton).click();
        return this;
    }

    @Step("Проверка активности выбранной секции")
    public boolean checkActive(String section) {
        return driver.findElement(activeSection).getText().equals(section);
    }

    @Step("Кнопка Войти в аккаунт на главной странице")
    public LoginPage pressLogin() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.Modal_modal_overlay__x2ZCr")));
        wait.until(ExpectedConditions.elementToBeClickable(login));
        driver.findElement(login).click();
        return new LoginPage();
    }

    @Step("Проверка наличия кнопки Оформить заказ доступна")
    public boolean checkButtonOrder() {
        return driver.findElement(buttonOrder).isDisplayed();
    }

    @Step("Кнопка Личный кабинет на главной странице")
    public LoginPage buttonPersonalAccount() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.Modal_modal_overlay__x2ZCr")));
        wait.until(ExpectedConditions.elementToBeClickable(personalAccount));
        driver.findElement(personalAccount).click();
        return new LoginPage();
    }

    @Step("Кнопка Личный кабинет на главной странице с авторизацией")
    public PersonalAccountPage buttonPersonalAccountWithAuth() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(personalAccount));
        driver.findElement(personalAccount).click();
        return new PersonalAccountPage();
    }
}
