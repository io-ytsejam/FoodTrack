package Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Ingredient
{
    @Id
    @GeneratedValue
    private int Id;

    private String Name;

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
}