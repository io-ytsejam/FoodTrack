package Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Recipe
{
    @Id
    @GeneratedValue
    private int Id;

    private String Name;

    private String Description;

    private String Steps;

    private boolean ifExternal;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSteps() {
        return Steps;
    }

    public void setSteps(String steps) {
        Steps = steps;
    }

    public boolean isIfExternal() {
        return ifExternal;
    }

    public void setIfExternal(boolean ifExternal) {
        this.ifExternal = ifExternal;
    }
}