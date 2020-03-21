package com.backend.Models;

import javax.persistence.*;

@Entity
@Table(name = "RATING", schema = "TEST", catalog = "")
public class RatingEntity {
    @Id
    @Column(name = "RATINGID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long ratingid;

    @Basic
    @Column(name = "VALUE")
    private long value;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "personid")
    private PersonEntity person;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "recipeid")
    private RecipeEntity recipe;

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
    }

    public RatingEntity() {
    }

    public RatingEntity(long value)
    {
        this.value=value;
    }

    public long getRatingid() {
        return ratingid;
    }

    public void setRatingid(long ratingid) {
        this.ratingid = ratingid;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingEntity that = (RatingEntity) o;

        if (ratingid != that.ratingid) return false;
        if (value != that.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ratingid ^ (ratingid >>> 32));
        result = 31 * result + (int) (value ^ (value >>> 32));
        return result;
    }
}
