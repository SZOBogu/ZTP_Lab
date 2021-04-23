package database;

import entities.UserEntity;

import java.util.ArrayList;

public class UserDatabase {
    private ArrayList<UserEntity> users;

    public UserDatabase(){
        this.users = new ArrayList();
    }

    public void init(){
        UserEntity admin = new UserEntity();
        UserEntity maciek = new UserEntity();
        UserEntity przemek = new UserEntity();
        UserEntity domino = new UserEntity();

        admin.setUsername("admin");
        maciek.setUsername("maciek");
        przemek.setUsername("pshemo");
        domino.setUsername("domino");

        admin.setPassword("admin");
        maciek.setPassword("123");
        przemek.setPassword("apud");
        domino.setPassword("jachas");

        admin.setRole("admin");
        maciek.setRole("user");
        przemek.setRole("user");
        domino.setRole("user");

        this.addUser(admin);
        this.addUser(maciek);
        this.addUser(przemek);
        this.addUser(domino);
    }

    public void addUser(UserEntity user){
        this.users.add(user);
    }

    public void removeUser(int index){
        this.users.remove(index);
    }

    public ArrayList<UserEntity> getUsers() {
        return users;
    }

    public boolean contains(UserEntity user){
        for(UserEntity user1 : this.users){
            if(user1.equals(user)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String string = "USER DATABASE\n";
        for(UserEntity user : this.users){
            string += user.toString() + "\n";
        }
        string += "===============";
        return string;
    }
}