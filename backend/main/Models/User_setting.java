package main.java.Models;

import javax.persistence.*;

@Entity
public class User_setting
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Setting setting;
}