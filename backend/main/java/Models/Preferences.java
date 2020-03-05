package main.java.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Preferences
{
    @Id @GeneratedValue
    @Column(name="PREFERENCES_ID", nullable=false)
    private int preferencesId;

    @Column(name="CATEGORY", nullable=false)
    private String category;

    public Preferences() {
    }

    public Preferences(int preferencesId, String category) {
        this.preferencesId = preferencesId;
        this.category = category;
    }

    public int getPreferencesId() {
        return preferencesId;
    }

    public void setPreferencesId(int preferencesId) {
        this.preferencesId = preferencesId;
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