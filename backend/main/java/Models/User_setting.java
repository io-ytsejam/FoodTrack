package main.java.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User_setting
{
    @Id @GeneratedValue
    @Column(name="USER_SETTING_ID", nullable=false)
    private int usersettingId;

    @Column(name="VALUE", nullable=false)
    private boolean value;

    public User_setting() {
    }

    public User_setting(int usersettingId, boolean value) {
        this.usersettingId=usersettingId;
        this.value=value;
    }

    public int getUsersettingId() {
        return usersettingId;
    }

    public void setUsersettingId(int usersettingId) {
        this.usersettingId = usersettingId;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}