package by.itacademy.zaitsevaolv.pages.kvitkiby;

import by.itacademy.zaitsevaolv.pages.base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains (text(), 'Вход')]")
    private WebElement tabLoginPage;
    @FindBy(xpath = "//input[@type='email']")
    public WebElement emailFieldLoginPage;
    @FindBy(xpath = "//input[@type='password']")
    public WebElement passwordFieldLoginPage;
    @FindBy(xpath = "//app-collapsible[contains (text(), 'почта')]")
    private WebElement emailErrorMessageLoginPage;
    @FindBy(xpath = "//app-collapsible[contains (text(), 'name@example.com')]")
    private WebElement emailErrorMessageDomainLoginPage;
    @FindBy(xpath = "//app-collapsible[contains (text(), 'Пароль')]")
    private WebElement passwordErrorMessageLoginPage;
    @FindBy(xpath = "//button[contains (text(), 'Войти')]")
    public WebElement buttonLoginPage;
    @FindBy(xpath = "//div[@class='info-text']")
    private WebElement popupErrorMessageLoginPage;


    // Метод для клика по табу "Вход"
    public LoginPage clickTabLoginPage() {
        tabLoginPage.click();
        return this;
    }

    // Метод для ввода email
    public LoginPage enterEmailFieldLoginPage(String email) {
        waitElementIsVisible(emailFieldLoginPage);
        emailFieldLoginPage.clear();
        emailFieldLoginPage.sendKeys(email);
        return this;
    }

    // Метод для ввода пароля
    public LoginPage enterPasswordFieldLoginPage(String password) {
        waitElementIsVisible(passwordFieldLoginPage);
        passwordFieldLoginPage.clear();
        passwordFieldLoginPage.sendKeys(password);
        return this;
    }

    // Метод для нажатия кнопки "Вход"
    public void clickLoginButtonLoginPage() {
        clickElementWhenClickable(buttonLoginPage);
    }

    // Метод для получения текста сообщения об ошибке для email
    public String getEmailErrorMessageLoginPage() {
        waitElementIsVisible(emailErrorMessageLoginPage);
        return emailErrorMessageLoginPage.getText();
    }

    //Метод для получения текста сообщения об ошибке для домена email
    public String getEmailErrorMessageDomainLoginPage() {
        waitElementIsVisible(emailErrorMessageDomainLoginPage);
        return emailErrorMessageDomainLoginPage.getText();
    }


    // Метод для получения текста сообщения об ошибке для пароля
    public String getPasswordErrorMessageLoginPage() {
        waitElementIsVisible(passwordErrorMessageLoginPage);
        return passwordErrorMessageLoginPage.getText();
    }

    // Метод для получения текста всплывающего сообщения об ошибке
    public String getPopupErrorMessageLoginPage() {
        waitElementIsVisible(popupErrorMessageLoginPage);
        return popupErrorMessageLoginPage.getText();
    }

    //Метод для проверки неактивности кнопки
    public boolean isButtonLoginPageEnabled() {
        return buttonLoginPage.isEnabled();
    }


}
