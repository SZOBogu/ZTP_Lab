package pojos;

/**
 *
 * @author root
 */
public class Book {
    private Integer id;
    private String title;
    private String author;
    private int year;
    private static int counter = 0;

    public Book(String title, String author, int year) {
        this.id = counter;
        this.title = title;
        this.author = author;
        this.year = year;
        counter++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void assignId(){
        this.id = counter;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", author=" + author + ", year=" + year + '}';
    }


}
