package bookstore.api;

import bookstore.config.WebPathConfig;
import bookstore.models.*;
import org.aeonbits.owner.ConfigFactory;

import java.util.Collections;

import static bookstore.specs.ApiSpecs.*;
import static io.restassured.RestAssured.given;

public class BooksApi {

    private static final WebPathConfig PATH = ConfigFactory.create(WebPathConfig.class, System.getProperties());

    public AddBooksModel getBookData(LoginResponseModel loginResponse) {
        BookCollections response = given()
                .spec(jsonRequest)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .get(PATH.BOOKSTORES())
                .then()
                .spec(responseCode(200))
                .extract().as(BookCollections.class);

        AddBooksModel addBookBodyModel = new AddBooksModel();
        addBookBodyModel.setUserId(loginResponse.getUserId());
        IsbnBookModel isbnBookModel = new IsbnBookModel();

        isbnBookModel.setIsbn(response.getBooks()[0].getIsbn());
        addBookBodyModel.setCollectionOfIsbns(Collections.singletonList(isbnBookModel));

        BookListResponse bookListModelResponse = new BookListResponse();
        bookListModelResponse.setTitle(response.getBooks()[0].getTitle());

        return addBookBodyModel;
    }

    public void addBook(LoginResponseModel lr, AddBooksModel books) {

        given()
                .spec(jsonRequest)
                .header("Authorization", "Bearer " + lr.getToken())
                .body(books)
                .when()
                .post(PATH.BOOKSTORES())
                .then()
                .spec(responseCode(201));
    }

    public void deleteBook(LoginResponseModel lr, DeleteBookModel book) {

        given()
                .spec(jsonRequest)
                .header("Authorization", "Bearer " + lr.getToken())
                .body(book)
                .when()
                .delete(PATH.BOOKSTORE())
                .then()
                .spec(responseCode(204));
    }

    public void deleteAllBooks(LoginResponseModel lr) {
        given()
                .spec(jsonRequest)
                .header("Authorization", "Bearer " + lr.getToken())
                .queryParam("UserId", lr.getUserId())
                .when()
                .delete(PATH.BOOKSTORES())
                .then();
    }
}
