package services;

import models.User;
import java.util.ArrayList;

public class UserService {
    private ArrayList<User> users = new ArrayList<>();

    public UserService(){
        users.add(new User(731, "Ezekiel"));
        users.add(new User(1011, "Fonsi"));
        users.add(new User(1, "JohnTobyA.Pickavant"));
    }

    public User login(int id, String password){
        for (User user : users){
            if (user.getUserID() == id && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    public void addUser(User user){
        users.add(user);
    }
}


