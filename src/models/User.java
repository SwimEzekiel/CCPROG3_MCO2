/**
 * A User class that stores private information
 * Includes the user ID and the password of the user
 *
 * @author Ezekiel Alvarez
 */
package models;

public class User {
    private int userID;
    private String userPassword;
    private Collection collection;

    //Constructor
    public User(int userID, String userPassword, int accNum){
        this.userID = userID;
        this.userPassword = userPassword;
        collection = new Collection(accNum);
    }
    //Getters
    public int getUserID(){
        return userID;
    }

    public String getPassword(){
        return userPassword;
    }

    public Collection getCollection(){
        return collection;
    }

    //Setters
    public void setUserID(int userID){
        this.userID = userID;
    }

    public void setUserPassword(String password){
        this.userPassword = userPassword;
    }

    public void setCollection(Collection collection){
        this.collection = collection;
    }
}