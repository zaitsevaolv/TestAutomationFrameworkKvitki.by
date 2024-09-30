package by.itacademy.zaitsevaolv.pages.kvitkiby;

import by.itacademy.zaitsevaolv.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains (text(), 'Регистрация')]")
    public WebElement tabRegistrationPage;
    @FindBy(xpath = "//input[@name='firstName']")
    public WebElement nameFieldRegistrationPage;
    @FindBy(xpath = "//app-collapsible[contains (text(), '30 символов')]")
    public WebElement nameErrorMessageRegistrationPage;
    @FindBy(xpath = "//input[@name='lastName']")
    public WebElement surnameFieldRegistrationPage;
    @FindBy(xpath = "//app-collapsible[contains (text(), '30 символов')]")
    public WebElement surnameErrorMessageRegistrationPage;
    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailFieldRegistrationPage;
    @FindBy(xpath = "//*[@class='form-field-error ng-tns-c7-27 ng-tns-c6-28 ng-trigger ng-trigger-slideInOut ng-star-inserted']")
    private WebElement emailErrorMessageRegistrationPage;
    @FindBy(xpath = "//input[@name='phoneNr']")
    public WebElement telephoneFieldRegistrationPage;
    @FindBy(xpath = "//*[@class='form-field-error ng-tns-c7-29 ng-tns-c6-30 ng-trigger ng-trigger-slideInOut ng-star-inserted']")
    public WebElement telephoneErrorMessageRegistrationPage;
    @FindBy(xpath = "//input[@name='newPassword']")
    public WebElement passwordFieldRegistrationPage;
    @FindBy(xpath = "//*[@class='form-field-error ng-tns-c7-31 ng-tns-c6-32 ng-trigger ng-trigger-slideInOut ng-star-inserted']")
    private WebElement passwordErrorMessageRegistrationPage;
    @FindBy(xpath = "//input[@id='agreement-checkbox']")
    public WebElement checkboxAgreementRegistrationPage;
    @FindBy(xpath = "//button[@class='ng-tns-c28-9']")
    public WebElement buttonRegistrationPage;
    @FindBy(xpath = "//div[@class='info-text']")
    public WebElement popupErrorMessageRegistrationPage;
}


   /*Метод для ввода email
    public RegistrationPage enterEmailFieldRegistrationPage(String email) {
        waitElementIsVisible(emailFieldRegistrationPage);
        emailFieldRegistrationPage.clear();
        emailFieldRegistrationPage.sendKeys(email);
        return this;
    }

    // Метод для ввода name
    public RegistrationPage enterNameFieldRegistrationPage(String name) {
        waitElementIsVisible(nameFieldRegistrationPage);
        nameFieldRegistrationPage.clear();
        nameFieldRegistrationPage.sendKeys(name);
        return this;
    }

    // Метод для ввода surname
    public RegistrationPage enterSurNameFieldRegistrationPage(String surname) {
        waitElementIsVisible(surnameFieldRegistrationPage);
        surnameFieldRegistrationPage.clear();
        surnameFieldRegistrationPage.sendKeys(surname);
        return this;
    }

    // Метод для ввода telephone
    public RegistrationPage enterTelephoneFieldRegistrationPage(String telephone) {
        waitElementIsVisible(telephoneFieldRegistrationPage);
        telephoneFieldRegistrationPage.clear();
        telephoneFieldRegistrationPage.sendKeys(telephone);
        return this;
    }

    // Метод для ввода password
    public RegistrationPage enterPasswordFieldRegistrationPage(String password) {
        waitElementIsVisible(passwordFieldRegistrationPage);
        passwordFieldRegistrationPage.clear();
        passwordFieldRegistrationPage.sendKeys(password);
        return this;
    }

    //Метод для проверки неактивности кнопки
    public boolean isButtonRegistrationPageEnabled() {
    return buttonRegistrationPage.isEnabled();
    }

    // Метод для получения текста сообщения об ошибке - 30 символов для name
    public String getNameErrorMessageRegistrationPage() {
    waitElementIsVisible(nameErrorMessageRegistrationPage);
    return nameErrorMessageRegistrationPage.getText();
    }

    // Метод для получения текста сообщения об ошибке - 30 символов для surname
    public String getSurnameErrorMessageRegistrationPage() {
        waitElementIsVisible(surnameErrorMessageRegistrationPage);
        return surnameErrorMessageRegistrationPage.getText();
    }
    // Метод для получения текста всплывающего сообщения об ошибке
    public String getPopupErrorMessageRegistrationPage() {
        waitElementIsVisible(popupErrorMessageRegistrationPage);
        return popupErrorMessageRegistrationPage.getText();
    }


*/