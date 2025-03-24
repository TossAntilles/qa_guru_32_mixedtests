package bookstore.api;

import bookstore.models.*;

import java.util.Collections;

import static bookstore.data.Path.BOOKSTORE;
import static bookstore.data.Path.BOOKSTORES;
import static bookstore.specs.ApiSpecs.*;
import static io.restassured.RestAssured.given;

public class BooksApi {

    public AddBooksModel getBookData(LoginResponseModel loginResponse) {
        BookCollections response = given()
                .spec(jsonRequest)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
            .when()
                .get(BOOKSTORES)
            .then()
                .spec(response200)
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
            .post(BOOKSTORES)
        .then()
            .spec(response201);
    }
    public void deleteBook(LoginResponseModel lr, DeleteBookModel book) {

        given()
            .spec(jsonRequest)
            .header("Authorization", "Bearer " + lr.getToken())
            .body(book)
        .when()

            .delete(BOOKSTORE)
        .then()
            .spec(response204);

    }

    public void deleteAllBooks(LoginResponseModel lr) {
        given()
            .spec(jsonRequest)
            .header("Authorization", "Bearer " + lr.getToken())
            .queryParam("UserId", lr.getUserId())
        .when()
            .delete(BOOKSTORES)
        .then()
            //status varies depending on book list
            .log().all();
    }
}
