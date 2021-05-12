package services;


import database.UserDatabase;
import entities.MyUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserService implements UserDetailsService {
    private UserDatabase db;

    public UserService(){
        this.db = new UserDatabase();
        this.db.init();

        for(MyUser user : this.db.getUsers()){
            System.out.println(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<MyUser> users = this.db.getUsers();

        System.out.println(username);
        for(MyUser myUser : users){
            if(myUser.getUsername().equals(username)){
                System.out.println("Score");
                User user = new User(myUser.getUsername(), myUser.getPassword(), myUser.getAuthorities());
                System.out.println(user);

                return user;
            }
        }
        throw new UsernameNotFoundException("loadUserByUsername exception: username not found\n");
    }
}
