package main.java.Models;

import javax.persistence.*;

@Entity
public class Rating
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RATING_ID", nullable=false)
    private int ratingId;

    @Column(name="VALUE", nullable=false)
    private int value;

    public Rating() {
    }

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

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;
}