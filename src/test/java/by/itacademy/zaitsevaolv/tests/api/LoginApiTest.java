package by.itacademy.zaitsevaolv.tests.api;

import by.itacademy.zaitsevaolv.tests.base.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class LoginApiTest extends BaseApiTest {
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {1873, "voice@mail.com", "123"},
                {1873, "Dата@", "***12"},
                {1873, "leto@Gmaiл.ru", "Abc78"},
                {1873, "12345/*", "000"},
                {1873, "*/@##$%%&*", "0/* 00"},
                {1873, " voice@ mail.com ", "0/* 00"},
                {1873, "<b>data entered for the test</b>;", "Abc2025"},
                {1873, "<script>alert('test script entered into the [fieldname]')</script>", "Abc2026"},
                {1873, "❤❤❤❤❤❤❤💘", "Abc2027"},
                {1873, "♀█ ╜▓ ▒‼ à á â å æ Æ Œ", "Abc2028"},
                {1873, "molli77@@mail.ru", "   "},
                {1873, "admin' OR 1=1 --", "admin' OR 1=1 --"}
        };
    }

    @DataProvider(name = "emptyAndSpaceLoginData")
    public Object[][] emptyAndSpaceLoginData() {
        return new Object[][]{
                {1873, "", ""},
                {1873, "  ", "  "}
        };
    }

    //Тест на разные невалидные данные в поле электронная почта и пароль
    @Test(dataProvider = "invalidLoginData")
    public void testLoginWithInvalidCredentials(int centreId, String email, String password) {
        String endpoint = "/auth/login";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String requestBody = "{ \"centreId\": " + centreId + ", \"email\": \"" + email + "\", \"password\": \"" + password + "\" }";
        Response response = sendPostRequest(endpoint, requestBody, headers);
        Assert.assertEquals(response.statusCode(), 401, "Статус код должен быть 401");
        String errorMessage = response.jsonPath().getString("message");
        Assert.assertEquals(errorMessage, "web_login_email_or_password_invalid", "Неверное сообщение об ошибке");
    }

    // Тест на некорректный JSON, без фигурной скобки
    @Test
    public void testLoginWithIncorrectJson() {
        String endpoint = "/auth/login";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String requestBody = "{\"centreId\":1873,\"email\":\"molli77125@mail.best\",\"password\":\"Abc123\"";
        Response response = sendPostRequest(endpoint, requestBody, headers);
        Assert.assertEquals(response.statusCode(), 400, "Статус код должен быть 400");
        String errorMessage = response.jsonPath().getString("message");
        Assert.assertEquals(errorMessage, "api_request_malformed_body", "Неверное сообщение об ошибке");
    }

    //Тест на пустые и заполненные пробелами поля электронная почта и пароль
    @Test(dataProvider = "emptyAndSpaceLoginData")
    public void testLoginWithEmptyAndSpaceLoginData(int centreId, String email, String password) {
        String endpoint = "/auth/login";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String requestBody = "{ \"centreId\": " + centreId + ", \"email\": \"" + email + "\", \"password\": \"" + password + "\" }";
        Response response = sendPostRequest(endpoint, requestBody, headers);
        Assert.assertEquals(response.statusCode(), 400, "Статус код должен быть 400");
        String errorMessage = response.jsonPath().getString("message");
        Assert.assertEquals(errorMessage, "web_login_email_missing", "Неверное сообщение об ошибке");
    }
 // Тест на отправку данных GET вместо POST
    @Test
    public void testLoginWithGet() {
        String endpoint = "/auth/login";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        Response response = sendGetRequest(endpoint, headers);
        Assert.assertEquals(response.statusCode(), 404, "Статус код должен быть 404");
        String errorMessage = response.jsonPath().getString("message");
        Assert.assertEquals(errorMessage, "api_page_not_found", "Неверное сообщение об ошибке");
    }
}
