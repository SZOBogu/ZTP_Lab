package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import responses.GetBooksResponse;
import services.DashboardService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @RequestMapping(value = "/dashboard")
    public String getPage(HttpServletRequest request, Model model){
        return "dashboard";
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBooks(){

        List<BookEntity> books = dashboardService.getAllBooks();
        Gson gson = new GsonBuilder().create();
        GetBooksResponse getResponse = new GetBooksResponse(true, books);
        String json = gson.toJson(getResponse);

        return ResponseEntity.status(HttpStatus.OK)
                .body(json);
    }
    @GetMapping(value = "/getbook/{id}")
    public String getBook(@ModelAttribute("book") BookEntity bookEntity, Model model, @PathVariable int id){
        System.out.println("GetBook received request for id: " + id);

        BookEntity book = dashboardService.getBook(id);
        System.out.println(book);
        model.addAttribute("book", book);

        return "book";
    }

    @PostMapping(value = "/addbook", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addBook(HttpServletRequest request){
        Gson gson = new Gson();
        StringBuffer jb = new StringBuffer();
        String line = null;

        try{
            System.out.println("Dashboard servlet received POST");
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);

            String jsonString = jb.toString();
            BookEntity book = gson.fromJson(jsonString, BookEntity.class);
            System.out.println(book);

            dashboardService.addBook(book);

            return ResponseEntity.noContent().build();
        }

        catch(Exception ex){
            System.out.println("Dashboard servlet received POST, exception: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("");
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public void removeBook(@PathVariable int id){
        System.out.println("Dashboard servlet received DELETE");

        dashboardService.deleteBook(id);
    }
}
