package bookstore.api;

import bookstore.models.AddBooksModel;
import bookstore.models.BookCollections;
import bookstore.models.LoginResponseModel;

import static bookstore.data.Path.ACCOUNT;
import static bookstore.data.Path.BOOKSTORES;
import static bookstore.specs.ApiSpecs.jsonRequest;
import static bookstore.specs.ApiSpecs.response200;
import static io.restassured.RestAssured.given;

public class AccountApi {

    public BookCollections getAccountBookList(LoginResponseModel loginResponse){
        BookCollections response = given()
                .spec(jsonRequest)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .when()
                .get(ACCOUNT+loginResponse.getUserId())
                .then()
                .spec(response200)
                .extract().as(BookCollections.class);


        return response;
    }
}
