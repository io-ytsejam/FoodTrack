package main.java.Models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class PersonRecipeEntityPK implements Serializable {
    private long personPersonid;
    private long recipeRecipeid;

    public PersonRecipeEntityPK() {
    }

    public PersonRecipeEntityPK(long personPersonid, long recipeRecipeid) {
        this.personPersonid = personPersonid;
        this.recipeRecipeid = recipeRecipeid;
    }

    @Column(name = "PERSON_PERSONID")
    @Id
    public long getPersonPersonid() {
        return personPersonid;
    }

    public void setPersonPersonid(long personPersonid) {
        this.personPersonid = personPersonid;
    }

    @Column(name = "RECIPE_RECIPEID")
    @Id
    public long getRecipeRecipeid() {
        return recipeRecipeid;
    }

    public void setRecipeRecipeid(long recipeRecipeid) {
        this.recipeRecipeid = recipeRecipeid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonRecipeEntityPK that = (PersonRecipeEntityPK) o;

        if (personPersonid != that.personPersonid) return false;
        if (recipeRecipeid != that.recipeRecipeid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (personPersonid ^ (personPersonid >>> 32));
        result = 31 * result + (int) (recipeRecipeid ^ (recipeRecipeid >>> 32));
        return result;
    }
}
