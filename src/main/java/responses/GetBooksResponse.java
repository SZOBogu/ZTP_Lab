package responses;

import entities.BookEntity;

import java.util.List;

/**
 *
 * @author root
 */
public class GetBooksResponse {
    private boolean isAdmin;
    private List<BookEntity> books;

    public GetBooksResponse(boolean isAdmin, List<BookEntity> books) {
        this.isAdmin = isAdmin;
        this.books = books;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}