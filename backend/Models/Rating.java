package Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Rating
{
    @Id
    @GeneratedValue
    private int Id;

    private int Value;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }
}