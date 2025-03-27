package bookstore.tests;

import bookstore.api.AccountApi;
import bookstore.api.BooksApi;
import bookstore.api.LoginApi;
import bookstore.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;


public class PurchaseListApiTest extends TestBase {

    @Test
    @DisplayName("Удаление книги, только API")
    public void removeBookFromBasketTest() {

        LoginResponseModel lR = new LoginApi().login();

        BooksApi booksApi = new BooksApi();
        AddBooksModel addBooksModel = booksApi.getBookData(lR);
        DeleteBookModel deleteBookModel = new DeleteBookModel(lR.getUserId(),
                addBooksModel.getCollectionOfIsbns().get(0).getIsbn());
        AccountApi accApi = new AccountApi();

        step("Precondition: чистка список книг ", () -> {
            booksApi.deleteAllBooks(lR);
        });

        step("Precondition: добавляем книгу в список покупок ", () -> {
            booksApi.addBook(lR, addBooksModel);
            int booksCount = accApi.getAccountBookList(lR).getBooks().length;
            Assertions.assertEquals(booksCount, 1);
        });

        step("Удаление книги из списка покупок ", () -> {
            booksApi.deleteBook(lR, deleteBookModel);
            int booksCount = accApi.getAccountBookList(lR).getBooks().length;
            Assertions.assertEquals(booksCount, 0);
        });

        step("Postcondition: повторная чистка списка покупок ", () -> {
            int booksCount = accApi.getAccountBookList(lR).getBooks().length;
            if (booksCount > 0) {
                booksApi.deleteAllBooks(lR);
            }
        });

    }
}
