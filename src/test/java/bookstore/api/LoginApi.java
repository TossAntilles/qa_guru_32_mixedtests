package bookstore.api;

import bookstore.config.TestDataConfig;
import bookstore.config.WebPathConfig;
import bookstore.models.LoginBodyModel;
import bookstore.models.LoginResponseModel;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;

import static bookstore.specs.ApiSpecs.*;
import static io.restassured.RestAssured.given;

public class LoginApi {

    private static final WebPathConfig PATH = ConfigFactory.create(WebPathConfig.class, System.getProperties());
    private static final TestDataConfig USER = ConfigFactory.create(TestDataConfig.class, System.getProperties());

    @Step("Логин через API")
    public LoginResponseModel login() {

        LoginBodyModel loginBodyModel = new LoginBodyModel();
        loginBodyModel.setUserName(USER.USERNAME());
        loginBodyModel.setPassword(USER.PASSWORD());

        return given()
                .spec(jsonRequest)
                .body(loginBodyModel)

            .when()
                .post(PATH.LOGIN())
            .then()
                .spec(response200)
                .extract().as(LoginResponseModel.class);
    }

}
