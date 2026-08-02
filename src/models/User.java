/**
 * A User class that stores private information
 * Includes the user ID and the password of the user
 *
 * @author Ezekiel Alvarez
 */
package models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userID;
    private String userPassword;
    private ArrayList<MediaEntry> collection;

    //Constructor
    public User(int userID, String userPassword, ArrayList<CardGame> cgs, ArrayList<TVSeries> tvs, ArrayList<Website> wbs){
        this.userID = userID;
        this.userPassword = userPassword;
        collection = new ArrayList<>();

        collection.addAll(cgs);
        collection.addAll(tvs);
        collection.addAll(wbs);
    }
    //Getters
    public int getUserID(){
        return userID;
    }

    public String getPassword(){
        return userPassword;
    }

    public ArrayList<MediaEntry> getCollection(){
        return collection;
    }

    //Setters
    public void setUserID(int userID){
        this.userID = userID;
    }

    public void setUserPassword(String password){
        this.userPassword = userPassword;
    }

    public void setCollection(ArrayList<MediaEntry> collection){
        this.collection = collection;
    }
}