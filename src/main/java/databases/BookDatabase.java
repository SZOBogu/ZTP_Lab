package databases;
import java.util.ArrayList;
import pojos.Book;
/**
 *
 * @author root
 */
public class BookDatabase {
    private ArrayList<Book> books;

    public BookDatabase() {
        this.books = new ArrayList();
    }

    public void init(){
        Book book1 = new Book("Servlety w mniej niż 2 lata", "Terry Davis", 2001);
        Book book2 = new Book("Schudnij z Rudim Schuberthem", "Rudi Schuberth", 2011);
        Book book3 = new Book("Aforyzmy i rozmyślania", "Jacek Stachursky", 2018);
        Book book4 = new Book("8 godzinny trening rąk dla opornych", "Rich Piana", 2015);
        this.addBook(book1);
        this.addBook(book2);
        this.addBook(book3);
        this.addBook(book4);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book){
        this.books.add(book);
    }

    public void removeBook(int index){
        this.books.remove(index);
    }

    @Override
    public String toString() {
        String string = "BOOK DATABASE\n";
        for(Book book : this.books){
            string += book.toString() + "\n";
        }
        string += "===============";
        return string;
    }


}