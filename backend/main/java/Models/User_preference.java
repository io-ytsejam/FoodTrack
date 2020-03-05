package main.java.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User_preference
{
    @Id @GeneratedValue
    @Column(name="USER_PREFERENCE_ID", nullable=false)
    private int userpreferenceId;

    @Column(name="VALUE", nullable=false)
    private boolean value;

    public int getUserpreferenceId() {
        return userpreferenceId;
    }

    public void setUserpreferenceId(int userpreferenceId) {
        this.userpreferenceId = userpreferenceId;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}