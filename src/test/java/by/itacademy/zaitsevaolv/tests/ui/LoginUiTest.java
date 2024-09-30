package by.itacademy.zaitsevaolv.tests.ui;

import by.itacademy.zaitsevaolv.pages.kvitkiby.LoginPage;
import by.itacademy.zaitsevaolv.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginUiTest extends BaseTest {
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginDataProvider() {
        return new Object[][]{
                {"map@mail.com", "Abc123**", "Электронная почта или пароль недействительны. Система была обновлена, и в связи с этим мы перешли на вход по электронной почте."},
                {"user@yahoo.com", "P@$$w0rd*", "Электронная почта или пароль недействительны. Система была обновлена, и в связи с этим мы перешли на вход по электронной почте."}
        };
    }

    @DataProvider(name = "invalidLoginEmailDomain")
    public Object[][] invalidLoginEmailDomainProvider() {
        return new Object[][]{
                {"molli@example.", "Пожалуйста, введите адрес электронной почты в правильном формате: name@example.com"},
                {"user@.com", "Пожалуйста, введите адрес электронной почты в правильном формате: name@example.com"}
        };
    }

    @Test
    /*Тест на появление сообщения об ошибке и красной рамки в поле EmailFieldLoginPage после того,
    как пользователь покидает пустое поле, кнопка неактивна*/
    public void shouldDisplayErrorMessageAndRedBorderWhenEmailFieldIsEmptyAndLosesFocus() {
        loginPage.enterEmailFieldLoginPage("");
        loginPage.leaveField(loginPage.emailFieldLoginPage);
        String actualErrorMessage = loginPage.getEmailErrorMessageLoginPage();
        String expectedErrorMessage = "Пожалуйста, заполните поле (Эл. почта)";
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage, "Сообщение об ошибке неверное");
        String borderColor = loginPage.getFieldBorderColor(loginPage.emailFieldLoginPage);
        String expectedBorderColor = "rgb(253, 0, 50)";
        Assert.assertEquals(expectedBorderColor, borderColor, "Цвет рамки вокруг поля неверный");
        Assert.assertFalse(loginPage.isButtonLoginPageEnabled(), "Кнопка Войти активна");
    }

    @Test
    /*Тест на появление сообщения об ошибке и красной рамки в поле passwordFieldLoginPage после того,
    как пользователь покидает пустое поле, кнопка неактивна*/
    public void shouldDisplayErrorMessageAndRedBorderWhenPasswordFieldIsEmptyAndLosesFocus() {
        loginPage.enterPasswordFieldLoginPage("");
        loginPage.leaveField(loginPage.passwordFieldLoginPage);
        String actualErrorMessage = loginPage.getPasswordErrorMessageLoginPage();
        String expectedErrorMessage = "Пожалуйста, заполните поле (Пароль)";
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage, "Сообщение об ошибке неверное");
        String borderColor = loginPage.getFieldBorderColor(loginPage.passwordFieldLoginPage);
        String expectedBorderColor = "rgb(253, 0, 50)";
        Assert.assertEquals(expectedBorderColor, borderColor, "Цвет рамки вокруг поля неверный");
        Assert.assertFalse(loginPage.isButtonLoginPageEnabled(), "Кнопка Войти активна");
    }

    //Тест на возможность войти с незарегистрированным email
    @Test(dataProvider = "invalidLoginData")
    public void shouldNotAllowLoginWithInvalidEmailAndPassword(String email, String password, String expectedErrorMessage) {
        loginPage.enterEmailFieldLoginPage(email);
        loginPage.leaveField(loginPage.emailFieldLoginPage);
        loginPage.enterPasswordFieldLoginPage(password);
        loginPage.clickLoginButtonLoginPage();

        String actualErrorMessage = loginPage.getPopupErrorMessageLoginPage();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Сообщение об ошибке неверное");
    }

    //Тест на возможность заполнения email некорректными данными домена
    @Test(dataProvider = "invalidLoginEmailDomain")
    public void shouldDisplayErrorMessageWhenInvalidDomainIsEnteredInEmailField(String email, String expectedErrorMessage) {
        loginPage.enterEmailFieldLoginPage(email);
        loginPage.leaveField(loginPage.emailFieldLoginPage);

        String actualErrorMessage = loginPage.getEmailErrorMessageDomainLoginPage();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Сообщение об ошибке неверное");
    }
}
