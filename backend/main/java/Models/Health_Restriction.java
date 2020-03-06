package main.java.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Health_Restriction
{
    @Id
    @GeneratedValue
    @Column(name="HEALTHRESTRICTION_ID", nullable=false)
    private int healthrestrictionId;

    @Column(name="NAME", nullable=false)
    private String name;

    public Health_Restriction() {
    }

    public Health_Restriction(int healthrestrictionId, String name) {
        this.healthrestrictionId = healthrestrictionId;
        this.name = name;
    }

    public int getHealthrestrictionId() {
        return healthrestrictionId;
    }

    public void setHealthrestrictionId(int healthrestrictionId) {
        this.healthrestrictionId = healthrestrictionId;
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