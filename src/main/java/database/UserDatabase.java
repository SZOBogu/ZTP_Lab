package database;

import entities.MyUser;

import java.util.ArrayList;

public class UserDatabase {
    private ArrayList<MyUser> users;

    public UserDatabase(){
        this.users = new ArrayList();
    }

    public void init(){
        MyUser admin = new MyUser("admin", "admin", "ADMIN");
        MyUser maciek = new MyUser("maciek", "123");
        MyUser przemek = new MyUser("pshemo", "apud");
        MyUser domino = new MyUser("domino", "jachas");
        this.addUser(admin);
        this.addUser(maciek);
        this.addUser(przemek);
        this.addUser(domino);
    }

    public void addUser(MyUser user){
        this.users.add(user);
    }

    public void removeUser(int index){
        this.users.remove(index);
    }

    public ArrayList<MyUser> getUsers() {
        return users;
    }

    public boolean contains(MyUser user){
        for(MyUser user1 : this.users){
            if(user1.equals(user)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("USER DATABASE\n");
        for(MyUser user : this.users){
            string.append(user.toString()).append("\n");
        }
        string.append("===============");
        return string.toString();
    }
}