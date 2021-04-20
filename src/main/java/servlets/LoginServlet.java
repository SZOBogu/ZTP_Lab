package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import databases.BookDatabase;
import databases.UserDatabase;
import enums.Role;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojos.User;

/**
 *
 * @author root
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private UserDatabase users;

    public LoginServlet() {
        this.users = new UserDatabase();
        this.users.init();
    }


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

        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        StringBuffer jb = new StringBuffer();
        String line = null;

        try{
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            /// String jsonString = "{\"username\":\"maciek\", \"password\":\"123\"}";

            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);

            System.out.println("Login servlet received:" + jb);
            String jsonString = jb.toString();
            User user = this.jsonToUser(jsonString);

            if(this.checkUser(user)){
                if(user.getUsername().equals("admin")){
                    user.setRole(Role.ADMIN);
                }
                System.out.println("User:" + user.toString());
                this.saveUser(request, user);

                String encodedUsername = this.encodeUsername(user);
                response.addCookie(new Cookie("userId", encodedUsername));
                response.setStatus(200);

                out.print(jsonString);
                out.flush();
            }
            else{
                response.setStatus(401);
                out.print("login failed");
                out.flush();
            }
        }
        catch (Exception ex) {
            response.setStatus(500);
            //gson.toJson(response, response.getWriter());
            out.print(ex.getMessage());
            out.flush();
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
    public User jsonToUser(String json){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        User user = gson.fromJson(json, User.class);
        return user;
    }

    public void saveUser(HttpServletRequest request, User user){
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
    }

    private String encodeUsername(User user) {
        return Base64.getEncoder().encodeToString(user.getUsername().getBytes());
    }

    public boolean checkUser(User user){
        if(this.users.contains(user)){
            return true;
        }
        else return false;
    }
}