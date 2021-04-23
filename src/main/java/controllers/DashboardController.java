package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.BookEntity;
import entities.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import requests.DeleteRequest;
import responses.GetBooksResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @RequestMapping(value = "/dashboard")
    public String getPage(HttpServletRequest request, Model model){
        return "dashboard.html";
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBooks(HttpServletRequest request, Model model){
            SessionFactory factory = new Configuration()
                    .addAnnotatedClass(BookEntity.class)
                    .addAnnotatedClass(UserEntity.class)
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
//        return ResponseEntity.noContent().build();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addBook(HttpServletRequest request, Model model){
        Gson gson = new Gson();
        StringBuffer jb = new StringBuffer();
        String line = null;

        SessionFactory factory = new Configuration()
                .addAnnotatedClass(BookEntity.class)
                .addAnnotatedClass(UserEntity.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        List<BookEntity> books;

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
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeBook(HttpServletRequest request, Model model){
        Gson gson = new Gson();
        StringBuffer jb = new StringBuffer();
        String line = null;

        SessionFactory factory = new Configuration()
                .addAnnotatedClass(BookEntity.class)
                .addAnnotatedClass(UserEntity.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();


        try{
            System.out.println("Dashboard servlet received DELETE");
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);

            String jsonString = jb.toString();
            DeleteRequest delRequest = gson.fromJson(jsonString, DeleteRequest.class);
            System.out.println("Delete request index: " + delRequest.getIndex());

            session.getTransaction().begin();
            BookEntity book = session.get(BookEntity.class, delRequest.getIndex());
            session.delete(book);
            session.getTransaction().commit();
            session.close();
            factory.close();
        }
        catch(Exception ex){
            System.out.println("Dashboard servlet received DELETE, exception");
            factory.close();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("");
        }
        return null;
    }
}
