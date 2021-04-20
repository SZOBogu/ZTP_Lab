package databases;

import java.util.ArrayList;
import pojos.User;
import enums.Role;

/**
 *
 * @author root
 */
public class UserDatabase {
    private ArrayList<User> users;

    public UserDatabase(){
        this.users = new ArrayList();
    }

    public void init(){
        User admin = new User("admin", "admin", Role.ADMIN);
        User maciek = new User("maciek", "123");
        User przemek = new User("pshemo", "apud");
        User domino = new User("domino", "jachas");
        this.addUser(admin);
        this.addUser(maciek);
        this.addUser(przemek);
        this.addUser(domino);
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public void removeUser(int index){
        this.users.remove(index);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public boolean contains(User user){
        for(User user1 : this.users){
            if(user1.equals(user)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String string = "USER DATABASE\n";
        for(User user : this.users){
            string += user.toString() + "\n";
        }
        string += "===============";
        return string;
    }
}