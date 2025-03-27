package bookstore.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static bookstore.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class ApiSpecs {

    public static RequestSpecification jsonRequest = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification responseCode(int statusCode) {
            ResponseSpecBuilder builder = new ResponseSpecBuilder();
            builder.expectStatusCode(statusCode);
            builder.log(ALL);
        return builder.build();
    }

}
