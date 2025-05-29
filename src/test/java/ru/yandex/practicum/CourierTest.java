package ru.yandex.practicum;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import ru.yandex.practicum.steps.CourierSteps;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;


public class CourierTest {
    private final CourierSteps courierSteps = new CourierSteps();
    private String login;
    private String password;
    private String firstName;

    @Test
    public void shouldReturnOkTrueTest() {
        login = RandomStringUtils.randomAlphabetic(10);
        password = RandomStringUtils.randomAlphanumeric(10);
        firstName = RandomStringUtils.randomAlphabetic(10);

        //RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());

        courierSteps
                .createCourier(login, password, firstName)
                //.log().all()
                .statusCode(201)
                .body("ok", is(true));
    }

    @Test
    public void shouldReturnId() {
        login = RandomStringUtils.randomAlphabetic(10);
        password = RandomStringUtils.randomAlphanumeric(10);
        firstName = RandomStringUtils.randomAlphabetic(10);

        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());

        courierSteps
                .createCourier(login, password, firstName)
                .statusCode(201)
                //.log().all()
                .body("ok", is(true));

        courierSteps
                .loginCourier(login, password)
                //.log().all()
                .statusCode(200)
                .body("id", notNullValue());

    }

    @After
    public void tearDown() {
        Integer id = courierSteps.loginCourier(login, password).extract().path("id");
        if (id != null) {
            courierSteps.deleteCourier(id);
        }
    }
}
