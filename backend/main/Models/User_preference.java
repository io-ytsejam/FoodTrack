package main.java.Models;

import javax.persistence.*;

@Entity
public class User_preference
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Preference preference;
}