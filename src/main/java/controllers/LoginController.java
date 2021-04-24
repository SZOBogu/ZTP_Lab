//package controllers;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import database.UserDatabase;
//import entities.MyUser;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.BufferedReader;
//
//@Controller
//@RequestMapping("/login")
//public class LoginController {
//
//    private UserDatabase users;
//
//    public LoginController(){
//        this.users = new UserDatabase();
//        this.users.init();
//    }
//
//    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> processForm(HttpServletRequest request, Model model){
//        GsonBuilder builder = new GsonBuilder();
//        builder.setPrettyPrinting();
//        Gson gson = builder.create();
//        StringBuffer jb = new StringBuffer();
//        String line = null;
//        try{
//
//            BufferedReader reader = request.getReader();
//            while ((line = reader.readLine()) != null)
//                jb.append(line);
//
//            System.out.println("Login servlet received:" + jb);
//            String jsonString = jb.toString();
//            MyUser user = gson.fromJson(jsonString, MyUser.class);
//
//            if(this.users.contains(user)){
//                if(user.getUsername().equals("admin")){
//                    user.setRole("ADMIN");
//                }
//                System.out.println("User:" + user.toString());
//
//                return ResponseEntity.status(HttpStatus.OK).body(jsonString);
//            }
//            else{
//                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
//                        .body("");
//            }
//        }
//        catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("");
//        }
//    }
//}
