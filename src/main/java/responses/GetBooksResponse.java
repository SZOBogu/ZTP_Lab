package responses;

import databases.BookDatabase;
import java.util.ArrayList;
import pojos.Book;

/**
 *
 * @author root
 */
public class GetBooksResponse {
    private boolean isAdmin;
    private ArrayList<Book> books;

    public GetBooksResponse(boolean isAdmin, ArrayList<Book> books) {
        this.isAdmin = isAdmin;
        this.books = books;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}