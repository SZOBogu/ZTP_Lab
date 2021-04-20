package listeners;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import databases.BookDatabase;

/**
 *
 * @author root
 */
@WebListener()
public class LibraryContextServletListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BookDatabase books = new BookDatabase();
        books.init();
        sce.getServletContext().setAttribute("books", books);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try{
            System.out.println("Context Destroyed");
        }
        catch(Exception ex){
            System.out.println("LibraryContextServletListener");
        }
    }
}