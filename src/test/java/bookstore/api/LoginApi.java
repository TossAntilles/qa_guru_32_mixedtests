package bookstore.api;

import bookstore.data.TestData;
import bookstore.models.LoginBodyModel;
import bookstore.models.LoginResponseModel;

import static bookstore.specs.ApiSpecs.*;
import static io.restassured.RestAssured.given;

public class LoginApi {

    public LoginResponseModel login() {

        LoginBodyModel loginBodyModel = new LoginBodyModel();
        loginBodyModel.setUserName(TestData.userName);
        loginBodyModel.setPassword(TestData.password);

        return given()
                .spec(jsonRequest)
                .body(loginBodyModel)

            .when()
                .post("https://demoqa.com/Account/v1/Login")
            .then()
                .spec(response200)
                .extract().as(LoginResponseModel.class);
    }

}
