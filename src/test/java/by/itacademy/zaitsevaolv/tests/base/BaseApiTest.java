package by.itacademy.zaitsevaolv.tests.base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import java.util.Map;

public class BaseApiTest {
    protected static final String BASE_URL = "https://store.piletilevi.ee/web-api";
    protected static final String BASE_PATH = "/customer/public";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = BASE_PATH;
    }

    // Метод для GET запросов
    public Response sendGetRequest(String endpoint, Map<String, String> headers) {
        return RestAssured
                .given()
                .headers(headers)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    // Метод для POST запросов
    public Response sendPostRequest(String endpoint, Object body, Map<String, String> headers) {
        RequestSpecification request = RestAssured.given();
        if (headers != null) {
            request.headers(headers);
        }
        return request
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }
}
