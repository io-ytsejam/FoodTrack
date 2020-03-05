package main.java.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rating
{
    @Id
    @GeneratedValue
    @Column(name="RATING_ID", nullable=false)
    private int ratingId;

    @Column(name="VALUE", nullable=false)
    private int value;

    public Rating(int ratingId, int value) {
        this.ratingId = ratingId;
        this.value = value;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}