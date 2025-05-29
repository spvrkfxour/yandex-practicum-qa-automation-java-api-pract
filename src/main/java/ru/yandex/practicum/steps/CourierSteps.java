package ru.yandex.practicum.steps;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.yandex.practicum.dto.CreateCourierRequest;

import static io.restassured.RestAssured.given;


public class CourierSteps {

    private static final Log log = LogFactory.getLog(CourierSteps.class);

    public ValidatableResponse createCourier(String login, String password, String firstName) {
        CreateCourierRequest request = new CreateCourierRequest();
        request.setLogin(login);
        request.setPassword(password);
        request.setFirstName(firstName);

        return  given() // предусловия (заголовки, параметры, тело)
                //.log().all()
                // .log().ifValidationFails()
                // .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .baseUri("https://qa-scooter.praktikum-services.ru/")
                .body(request)
                .when() // действия (GET, POST, PUT, PATCH, DELETE)
                .post("/api/v1/courier")
                .then(); // проверки (статус, тело ответа)
    }

    public ValidatableResponse loginCourier(String login, String password) {
        CreateCourierRequest request = new CreateCourierRequest();
        request.setLogin(login);
        request.setPassword(password);

        return given()
                //.log().all()
                .contentType(ContentType.JSON)
                .baseUri("https://qa-scooter.praktikum-services.ru/")
                .body(request)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }

    public ValidatableResponse deleteCourier(int id) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri("https://qa-scooter.praktikum-services.ru/")
                .pathParam("id", id)
                .when()
                .delete("/api/v1/courier/{id}")
                .then();
    }
}
