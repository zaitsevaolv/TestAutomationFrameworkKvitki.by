package by.itacademy.zaitsevaolv.tests.ui;

import by.itacademy.zaitsevaolv.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationUiTest extends BaseTest {
    @DataProvider(name = "validRegistrationData")
    public Object[][] validRegistrationDataProvider() {
        return new Object[][]{
                {"Ольга", "Зайцева", "molli77@mail.ru", "295447878", "Abc123**"},
                {"1233***", "**// 454", "molli77@mail.ru", "336159569", "Abc123**"}
        };
    }

    /*Тест на появление сообщения об ошибке и красной рамки в поле nameFieldRegistrationPage когда пользователь
    вводит количество символов более 30*/
    @Test
    public void shouldDisplayErrorMessageAndRedBorderWhenNameFieldRegistrationPageIsManySymbols() {
        registrationPage.clickElementWhenClickable(registrationPage.tabRegistrationPage);
        registrationPage.enterText(registrationPage.nameFieldRegistrationPage, "2222222222222222222222222222222");
        registrationPage.leaveField(registrationPage.nameFieldRegistrationPage);
        String actualErrorMessage = registrationPage.getFieldErrorMessage(registrationPage.nameErrorMessageRegistrationPage);
        String expectedErrorMessage = "Слишком длинный Имя, максимум 30 символов";
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage, "Сообщение об ошибке неверное");
        String borderColor = registrationPage.getFieldBorderColor(registrationPage.nameFieldRegistrationPage);
        String expectedBorderColor = "rgb(253, 0, 50)";
        Assert.assertEquals(expectedBorderColor, borderColor, "Цвет рамки вокруг поля неверный");

    }

    /*Тест на появление сообщения об ошибке и красной рамки в поле surnameFieldRegistrationPage когда пользователь
    вводит количество символов более 30*/
    @Test
    public void shouldDisplayErrorMessageAndRedBorderWhenSurnameFieldRegistrationPageIsManySymbols() {
        registrationPage.clickElementWhenClickable(registrationPage.tabRegistrationPage);
        registrationPage.enterText(registrationPage.surnameFieldRegistrationPage, "333333333333333333333333333333333");
        registrationPage.leaveField(registrationPage.surnameFieldRegistrationPage);
        String actualErrorMessage = registrationPage.getFieldErrorMessage(registrationPage.surnameErrorMessageRegistrationPage);
        String expectedErrorMessage = "Слишком длинный Фамилия, максимум 30 символов";
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage, "Сообщение об ошибке неверное");
        String borderColor = registrationPage.getFieldBorderColor(registrationPage.surnameFieldRegistrationPage);
        String expectedBorderColor = "rgb(253, 0, 50)";
        Assert.assertEquals(expectedBorderColor, borderColor, "Цвет рамки вокруг поля неверный");
    }

    //Тест на возможность пройти регистрацию с ранее зарегистрированным пользователем

    @Test(dataProvider = "validRegistrationData")
    public void shouldNotRegisteredUserRegistration(String name, String surname, String email, String telephone, String password) {
        registrationPage.clickElementWhenClickable(registrationPage.tabRegistrationPage);
        registrationPage.enterText(registrationPage.nameFieldRegistrationPage, name);
        registrationPage.enterText(registrationPage.surnameFieldRegistrationPage, surname);
        registrationPage.enterText(registrationPage.emailFieldRegistrationPage, email);
        registrationPage.enterText(registrationPage.telephoneFieldRegistrationPage, telephone);
        registrationPage.enterText(registrationPage.passwordFieldRegistrationPage,password);
        registrationPage.clickElementWhenClickable(registrationPage.checkboxAgreementRegistrationPage);
        registrationPage.clickElementWhenClickable(registrationPage.buttonRegistrationPage);
        String actualErrorMessage = registrationPage.getFieldErrorMessage(registrationPage.popupErrorMessageRegistrationPage);
        String expectedErrorMessage = "Пользователь с данным адресом электронной почты уже зарегистрирован!";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Сообщение об ошибке неверное");
    }
}
