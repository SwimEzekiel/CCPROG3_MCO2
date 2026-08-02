package services;

import models.Collection;
import models.MediaEntry;
import models.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class UserService {
    private ArrayList<User> users = new ArrayList<>();
    private HashMap<Integer, Integer> IDtoAccNum = new HashMap<>();
    private Collection database = new Collection(); // for hardcoded data

    public UserService(){
        IDtoAccNum.put(731, 0);
        IDtoAccNum.put(1011, 1);
        IDtoAccNum.put(1, 2);

        database.setCurrent(0);
        users.add(new User(731, "Ezekiel", database.getCGCollection(), database.getTVCollection(), database.getWSCollection()));

        database.setCurrent(1);
        users.add(new User(1011, "Fonsi", database.getCGCollection(), database.getTVCollection(), database.getWSCollection()));

//        database.setCurrent(2);
//        users.add(new User(1, "JohnTobyA.Pickavant", database.getCGCollection(), database.getTVCollection(), database.getWSCollection()));
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


