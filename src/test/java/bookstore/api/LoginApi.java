package bookstore.api;

import bookstore.data.TestData;
import bookstore.models.LoginBodyModel;
import bookstore.models.LoginResponseModel;
import io.qameta.allure.Step;

import static bookstore.data.Path.LOGIN;
import static bookstore.data.TestData.*;
import static bookstore.specs.ApiSpecs.*;
import static io.restassured.RestAssured.given;

public class LoginApi {

    @Step("Логин через API")
    public LoginResponseModel login() {

        LoginBodyModel loginBodyModel = new LoginBodyModel();
        loginBodyModel.setUserName(USERNAME);
        loginBodyModel.setPassword(PASSWORD);

        return given()
                .spec(jsonRequest)
                .body(loginBodyModel)

            .when()
                .post(LOGIN)
            .then()
                .spec(response200)
                .extract().as(LoginResponseModel.class);
    }

}
