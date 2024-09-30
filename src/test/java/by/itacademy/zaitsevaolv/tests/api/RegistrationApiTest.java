package by.itacademy.zaitsevaolv.tests.api;

import by.itacademy.zaitsevaolv.tests.base.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RegistrationApiTest extends BaseApiTest {

    //Тест на регистрацию уже зарегистрированного пользователя
    @Test
    public void testRegisteredUserRegistration() {
        String endpoint = "/customer?language=ru";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String requestBody = "{\"firstName\":\"Ольга\",\"lastName\":\"Зайцева\",\"email\":\"molli77@mail.ru\",\"phoneNr\":\"+375295447878\",\"newPassword\":\"Фис2025**\",\"newsmail\":false,\"password\":\"Фис2025**\",\"centreId\":1873}";
        Response response = sendPostRequest(endpoint, requestBody, headers);
        Assert.assertEquals(response.statusCode(), 400, "Статус код должен быть 400");
        String errorMessage = response.jsonPath().getString("message");
        Assert.assertEquals(errorMessage, "web_customer_email_in_use", "Неверное сообщение об ошибке");
    }

    //Тест на отправку запроса POST с пустым body
    @Test
    public void shouldReturnErrorWhenRegistrationPostRequestEmptyBody() {
        String endpoint = "/customer?language=ru";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String requestBody = "";
        Response response = sendPostRequest(endpoint, requestBody, headers);
        Assert.assertEquals(response.statusCode(), 400, "Статус код должен быть 400");
        String errorMessage = response.jsonPath().getString("message");
        Assert.assertEquals(errorMessage, "web_customer_name_invalid", "Неверное сообщение об ошибке");
    }

    //Тест на отправку запроса POST без body
    @Test
    public void shouldReturnErrorWhenRegistrationPostRequestHasNoBody() {
        String endpoint = "/customer?language=ru";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        Response response = RestAssured.given()
                .headers(headers)
                .post(endpoint);
        Assert.assertEquals(response.statusCode(), 400, "Статус код должен быть 400");
        String errorMessage = response.jsonPath().getString("message");
        Assert.assertEquals(errorMessage, "web_customer_name_invalid", "Неверное сообщение об ошибке");
    }

    //Тест на отправку POST запроса с измененными данными параметра centreID
    @Test
    public void shouldReturnErrorWhenRegistrationPostRequestHasInvalidCentreId() {
        String endpoint = "/customer?language=ru";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String requestBody = "{\"firstName\":\"Ольга\",\"lastName\":\"Зайцева\",\"email\":\"molli77@mail.ru\",\"phoneNr\":\"+375295447878\",\"newPassword\":\"Фис2025**\",\"newsmail\":false,\"password\":\"Фис2025**\",\"centreId\":1999}";
        Response response = sendPostRequest(endpoint, requestBody, headers);
        Assert.assertEquals(response.statusCode(), 400, "Статус код должен быть 400");
        String errorMessage = response.jsonPath().getString("message");
        Assert.assertEquals(errorMessage, "web_centre_id_invalid", "Неверное сообщение об ошибке");
    }

    //Тест на отправку запроса POST c пустым полем email
    @Test
    public void shouldFailRegistrationWithEmptyEmailField() {
        String endpoint = "/customer?language=ru";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String requestBody = "{\"firstName\":\"Ольга\",\"lastName\":\"Зайцева\",\"email\":\"\",\"phoneNr\":\"+375295447878\",\"newPassword\":\"29544787899****ФЫAd\",\"newsmail\":false,\"password\":\"29544787899****ФЫAd\",\"centreId\":1873}";
        Response response = sendPostRequest(endpoint, requestBody, headers);
        Assert.assertEquals(response.statusCode(), 400, "Статус код должен быть 400");
        String errorMessage = response.jsonPath().getString("message");
        Assert.assertEquals(errorMessage, "web_customer_email_invalid", "Неверное сообщение об ошибке");
    }

    //Тест на отправку запроса POST без поля email
    @Test
    public void shouldFailRegistrationWithoutEmailField() {
        String endpoint = "/customer?language=ru";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String requestBody = "{\"firstName\":\"Ольга\",\"lastName\":\"Зайцева\",\"phoneNr\":\"+375295447878\",\"newPassword\":\"29544787899****ФЫAd\",\"newsmail\":false,\"password\":\"29544787899****ФЫAd\",\"centreId\":1873}";
        Response response = sendPostRequest(endpoint, requestBody, headers);
        Assert.assertEquals(response.statusCode(), 400, "Статус код должен быть 400");
        String errorMessage = response.jsonPath().getString("message");
        Assert.assertEquals(errorMessage, "web_customer_email_invalid", "Неверное сообщение об ошибке");
    }
}
