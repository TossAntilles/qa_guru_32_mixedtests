package bookstore.api;

import bookstore.models.BookCollections;
import bookstore.models.LoginResponseModel;

import static bookstore.data.Path.ACCOUNT;
import static bookstore.specs.ApiSpecs.*;
import static io.restassured.RestAssured.given;

public class AccountApi {

    public BookCollections getAccountBookList(LoginResponseModel loginResponse) {

        return given()
                .spec(jsonRequest)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .when()
                .get(ACCOUNT + loginResponse.getUserId())
                .then()
                .spec(responseCode(200))
                .extract().as(BookCollections.class);
    }

}
