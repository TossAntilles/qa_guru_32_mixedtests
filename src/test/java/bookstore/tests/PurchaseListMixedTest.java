package bookstore.tests;

import bookstore.api.AccountApi;
import bookstore.api.BooksApi;
import bookstore.api.LoginApi;
import bookstore.components.LoginAddCookies;
import bookstore.helpers.Attach;
import bookstore.models.*;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


public class PurchaseListMixedTest extends TestBase{

        @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    @DisplayName("Удаление книги, API + Web")
    public void removeBookFromProfileTable(){

        LoginResponseModel lR = new LoginApi().login();

        step("Добавление куки ", () -> {
            LoginAddCookies cookies = new LoginAddCookies();
            cookies.addCookies(lR);
        });

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
            String bookTitle = Arrays.stream(accApi.getAccountBookList(lR).getBooks()).toList().get(0).getTitle();
            //open(PROFILE);
            open("/profile");
            $(".ReactTable .rt-table").shouldHave(text(bookTitle));
        });

        step("Удаление книги из списка покупок ", () -> {
            String bookTitle = Arrays.stream(accApi.getAccountBookList(lR).getBooks()).toList().get(0).getTitle();
            $(".ReactTable .rt-table").$(".rt-tr-group").$("#delete-record-undefined").click();
            $("#closeSmallModal-ok").click();
            Selenide.dismiss();
            int booksCount = accApi.getAccountBookList(lR).getBooks().length;
            Assertions.assertEquals(booksCount, 0);
            Assertions.assertFalse($(".ReactTable .rt-table").has(text(bookTitle)));
        });

        step("Postcondition: повторная чистка списка покупок ", () -> {
            int booksCount = accApi.getAccountBookList(lR).getBooks().length;
            if (booksCount > 0) {
                booksApi.deleteAllBooks(lR);
            }
        });

    }
}

