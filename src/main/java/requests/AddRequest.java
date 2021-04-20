package requests;

/**
 *
 * @author root
 */
public class AddRequest {
    private boolean isAdmin;
    private String title;
    private String author;
    private int year;

    public AddRequest(boolean isAdmin, String title, String author, int year) {
        this.isAdmin = isAdmin;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
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

}