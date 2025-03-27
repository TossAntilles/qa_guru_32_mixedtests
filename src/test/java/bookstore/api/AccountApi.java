package bookstore.api;

import bookstore.config.WebPathConfig;
import bookstore.models.BookCollections;
import bookstore.models.LoginResponseModel;
import org.aeonbits.owner.ConfigFactory;

import static bookstore.specs.ApiSpecs.*;
import static io.restassured.RestAssured.given;

public class AccountApi {

    private static final WebPathConfig PATH = ConfigFactory.create(WebPathConfig.class, System.getProperties());

    public BookCollections getAccountBookList(LoginResponseModel loginResponse){
        BookCollections response = given()
                .spec(jsonRequest)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .when()
                .get(PATH.ACCOUNT()+loginResponse.getUserId())
                .then()
                .spec(responseCode(200))
                .extract().as(BookCollections.class);


        return response;
    }
}
