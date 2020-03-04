package Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Preferences
{
    @Id
    @GeneratedValue
    private int Id;

    private String Category;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}