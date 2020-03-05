package main.java.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Health_Restrictions
{
    @Id
    @GeneratedValue
    @Column(name="HEALTHRESTRICTIONS_ID", nullable=false)
    private int healthrestrictionsId;

    @Column(name="NAME", nullable=false)
    private String name;

    public Health_Restrictions() {
    }

    public Health_Restrictions(int healthrestrictionsId, String name) {
        this.healthrestrictionsId = healthrestrictionsId;
        this.name = name;
    }

    public int getHealthrestrictionsId() {
        return healthrestrictionsId;
    }

    public void setHealthrestrictionsId(int healthrestrictionsId) {
        this.healthrestrictionsId = healthrestrictionsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private final List<User_healthRestriction> user_healthRestrictionList = new ArrayList<>();
}