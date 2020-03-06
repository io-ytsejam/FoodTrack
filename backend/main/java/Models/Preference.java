package main.java.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Preference
{
    @Id @GeneratedValue
    @Column(name="PREFERENCE_ID", nullable=false)
    private int preferenceId;

    @Column(name="CATEGORY", nullable=false)
    private String category;

    public Preference() {
    }

    public Preference(int preferenceId, String category) {
        this.preferenceId = preferenceId;
        this.category = category;
    }

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private final List<User_preference> user_preferenceList = new ArrayList<>();
}