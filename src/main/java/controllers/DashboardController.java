package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.BookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import responses.GetBooksResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @RequestMapping(value = "/dashboard")
    public String getPage(HttpServletRequest request, Model model){
        return "dashboard";
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBooks(){
            SessionFactory factory = new Configuration()
                    .addAnnotatedClass(BookEntity.class)
                    .buildSessionFactory();

            Session session = factory.getCurrentSession();
            List<BookEntity> books;
            try {
                session.getTransaction().begin();
                books = session.createQuery(" from BookEntity").getResultList();
                session.getTransaction().commit();
                Gson gson = new GsonBuilder().create();
                //boolean isAdmin = user.getRole() == Role.ADMIN;
                GetBooksResponse getResponse = new GetBooksResponse(true, books);
                String json = gson.toJson(getResponse);
                session.close();
                factory.close();
                return ResponseEntity.status(HttpStatus.OK)
                        .body(json);
            }
            catch(Exception ex){
                factory.close();
                System.out.println("Dashboard servlet received GET, and exception ocurred");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("");
            }
    }
    @GetMapping(value = "/getbook/{id}")
    public String getBook(@ModelAttribute("book") BookEntity bookEntity, Model model, @PathVariable int id){
        System.out.println("GetBook received request for id: " + id);

        SessionFactory factory = new Configuration()
                .addAnnotatedClass(BookEntity.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            session.getTransaction().begin();
            BookEntity book = session.get(BookEntity.class, id);
            System.out.println(book);
            model.addAttribute("book", book);
            session.close();
            factory.close();

            return "book";
        }
        catch(Exception ex){
            return "book";
        }
    }

    @PostMapping(value = "/addbook", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addBook(HttpServletRequest request){
        Gson gson = new Gson();
        StringBuffer jb = new StringBuffer();
        String line = null;

        SessionFactory factory = new Configuration()
                .addAnnotatedClass(BookEntity.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            System.out.println("Dashboard servlet received POST");
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);

            String jsonString = jb.toString();
            BookEntity book = gson.fromJson(jsonString, BookEntity.class);
            System.out.println(book);
            session.getTransaction().begin();
            session.save(book);
            session.getTransaction().commit();
            session.close();
            factory.close();
            return ResponseEntity.noContent().build();
        }

        catch(Exception ex){
            System.out.println("Dashboard servlet received POST, exception: " + ex.getMessage());
            factory.close();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("");
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public String removeBook(@PathVariable int id){

        SessionFactory factory = new Configuration()
                .addAnnotatedClass(BookEntity.class)
                .buildSessionFactory();

        System.out.println("Dashboard servlet received DELETE");
        Session session = factory.getCurrentSession();

        try{
            session.getTransaction().begin();
            BookEntity book = session.get(BookEntity.class, id);
            session.delete(book);
            session.getTransaction().commit();
            session.close();
            factory.close();
        }
        catch(Exception ex){
            System.out.println("Dashboard servlet received DELETE, exception");
            factory.close();
            return "dashboard";
        }
        return null;
    }
}
