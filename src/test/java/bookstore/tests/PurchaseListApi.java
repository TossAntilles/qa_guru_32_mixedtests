package bookstore.tests;

import bookstore.api.BooksApi;
import bookstore.api.LoginApi;
import bookstore.components.LoginAddCookies;
import bookstore.models.AddBooksModel;
import bookstore.models.DeleteBookModel;
import bookstore.models.LoginResponseModel;
import org.junit.jupiter.api.Test;


public class PurchaseListApi extends TestBase{

    @Test
    public void removeBookFromBasketTest(){

       // log in
        LoginResponseModel lR = new LoginApi().login();
        LoginAddCookies cookies = new LoginAddCookies();
        cookies.addCookies(lR);

         //check//clear basket

        BooksApi ba = new BooksApi();
        AddBooksModel addBooksModel = ba.getBookData(lR);
        DeleteBookModel deleteBookModel = new DeleteBookModel(lR.getUserId(),
                addBooksModel.getCollectionOfIsbns().get(0).getIsbn());

        System.out.println("-----------------CLEAR LIST");
        //precondition - clear list
        ba.deleteAllBooks(lR);
        System.out.println("-----------------CLEARED");

        //add books
        System.out.println("-----------------ADD BOOK");
        ba.addBook(lR, addBooksModel);
        System.out.println("-----------------ADDED");

        //delete book
        System.out.println("-----------------DELETE BOOK");
        ba.deleteBook(lR, deleteBookModel);
        System.out.println("-----------------DELETED");


        System.out.println("-----------------POST CLEAR");
        //postcondition - clear data
        ba.deleteAllBooks(lR);
        System.out.println("-----------------CLEARED");




    }
}
