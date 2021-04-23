package controllers;

import database.UserDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {
    private UserDatabase users;

    public HomeController(){
        this.users = new UserDatabase();
        this.users.init();
    }

    @RequestMapping(value = "/")
//    @ResponseBody
    public String home(HttpServletRequest request, Model model){
        return "index.html";
    }

    @RequestMapping(value = "/badlogin")
   // @ResponseBody
    public String incorrectLogin(HttpServletRequest request, Model model){
        return "loginFailed.html";
    }

    @RequestMapping(value = "/error")
   // @ResponseBody
    public String error(HttpServletRequest request, Model model){
        return "otherError.html";
    }
}