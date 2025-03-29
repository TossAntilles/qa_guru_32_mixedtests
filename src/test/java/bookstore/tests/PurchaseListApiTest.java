package bookstore.tests;

import bookstore.api.AccountApi;
import bookstore.api.BooksApi;
import bookstore.api.LoginApi;
import bookstore.models.*;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;


public class PurchaseListApiTest extends TestBase {

    @Test
    @DisplayName("Удаление книги, только API")
    @Tags({@Tag("api"), @Tag("normal")})
    public void removeBookFromBasketTest() {

        LoginResponseModel lR = step("Precondition: логин ", () -> new LoginApi().login());

        BooksApi booksApi = new BooksApi();
        AddBooksModel addBooksModel = step("Precondition: получаем первую книгу из запроса ", () -> booksApi.getBookData(lR));

        DeleteBookModel deleteBookModel = new DeleteBookModel(lR.getUserId(),
                addBooksModel.getCollectionOfIsbns().get(0).getIsbn());
        AccountApi accApi = new AccountApi();

        step("Precondition: чистка список книг ", () -> booksApi.deleteAllBooks(lR));

        step("Precondition: добавляем книгу в список покупок ", () -> {
            booksApi.addBook(lR, addBooksModel);
            int booksCount = accApi.getAccountBookList(lR).getBooks().length;
            Assertions.assertEquals(1, booksCount);
        });

        step("Удаление книги из списка покупок ", () -> {
            booksApi.deleteBook(lR, deleteBookModel);
            int booksCount = accApi.getAccountBookList(lR).getBooks().length;
            Assertions.assertEquals(0, booksCount);
        });

        step("Postcondition: повторная чистка списка покупок ", () -> {
            int booksCount = accApi.getAccountBookList(lR).getBooks().length;
            if (booksCount > 0) {
                booksApi.deleteAllBooks(lR);
            }
        });

    }
}
