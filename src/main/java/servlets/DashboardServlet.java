package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import databases.BookDatabase;
import enums.Role;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojos.Book;
import pojos.User;
import requests.AddRequest;
import requests.DeleteRequest;
import responses.GetBooksResponse;

/**
 *
 * @author root
 */
@WebServlet(name = "DashboardServlet", urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Dashboard servlet received some request");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //oddawaj ksiazki
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try{
            System.out.println("Dashboard servlet received GET");

            Gson gson = new GsonBuilder().create();
            BookDatabase db = (BookDatabase)this.getServletContext().getAttribute("books");

            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");

            boolean isAdmin = user.getRole() == Role.ADMIN;

            GetBooksResponse getResponse = new GetBooksResponse(isAdmin, db.getBooks());
            String json = gson.toJson(getResponse);

            response.setStatus(200);
            System.out.println(json);
            out.print(json);
            out.flush();
        }
        catch(Exception ex){
            System.out.println("Dashboard servlet received GET, and exception ocurred");
        }


    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        StringBuffer jb = new StringBuffer();
        String line = null;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try{
            System.out.println("Dashboard servlet received POST");
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);

            String jsonString = jb.toString();
            Book book = gson.fromJson(jsonString, Book.class);
            book.assignId();
            System.out.println(book);

            BookDatabase db = (BookDatabase)this.getServletContext().getAttribute("books");
            db.addBook(book);
            this.getServletContext().setAttribute("books", db);

            response.setStatus(200);
            out.print(book + " added succesfully\n");
            out.flush();
        }

        catch(Exception ex){
            System.out.println("Dashboard servlet received POST, exception: " + ex.getMessage());
            response.setStatus(500);
            out.print("Adding Book Error");
            out.flush();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        StringBuffer jb = new StringBuffer();
        String line = null;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        try{
            System.out.println("Dashboard servlet received DELETE");
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);

            String jsonString = jb.toString();
            DeleteRequest delRequest = gson.fromJson(jsonString, DeleteRequest.class);
            System.out.println("Delete request index: " + delRequest.getIndex());

            BookDatabase db = (BookDatabase)this.getServletContext().getAttribute("books");
            db.removeBook(delRequest.getIndex());
            this.getServletContext().setAttribute("books", db);

            response.setStatus(200);
            out.print("Book removed succesfully\n");
            out.flush();
        }
        catch(Exception ex){
            System.out.println("Dashboard servlet received DELETE, exception");
            response.setStatus(500);
            out.print("Delete Book Error");
            out.flush();
        }
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */



    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
