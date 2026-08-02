package services;

import models.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class UserService {
    private ArrayList<User> users = new ArrayList<>();
    private HashMap<Integer, Integer> IDtoAccNum = new HashMap<>();

    public UserService(){
        IDtoAccNum.put(731, 0);
        IDtoAccNum.put(1011, 1);
        IDtoAccNum.put(1, 2);

        users.add(new User(731, "Ezekiel", 0));
        users.add(new User(1011, "Fonsi", 1));
        users.add(new User(1, "JohnTobyA.Pickavant", 2));
    }

    public int getAccNum(int userID){
        Integer ret = IDtoAccNum.get(userID);
        if (ret != null) return ret;
        else return -1;
    }
    public User login(int id, String password){
        for (User user : users){
            if (user.getUserID() == id && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    public void addUser(User user){
        IDtoAccNum.put(user.getUserID(), users.size());
        users.add(user);
    }
}


