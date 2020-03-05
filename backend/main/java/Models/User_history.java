package main.java.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User_history
{
    @Id @GeneratedValue
    private int userhistoryId;

    public User_history(int userhistoryId) {
        this.userhistoryId=userhistoryId;
    }

    public int getUserhistoryId() {
        return userhistoryId;
    }

    public void setUserhistoryId(int userhistoryId) {
        this.userhistoryId = userhistoryId;
    }
}