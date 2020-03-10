package Models;

import javax.persistence.*;

@Entity
@Table(name = "PERSON_RECIPE", schema = "TEST", catalog = "")
@IdClass(PersonRecipeEntityPK.class)
public class PersonRecipeEntity {
    private long personPersonid;
    private long recipeRecipeid;

    public PersonRecipeEntity() {
    }

    public PersonRecipeEntity(long personPersonid, long recipeRecipeid) {
        this.personPersonid=personPersonid;
        this.recipeRecipeid=recipeRecipeid;
    }

    @Id
    @Column(name = "PERSON_PERSONID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getPersonPersonid() {
        return personPersonid;
    }

    public void setPersonPersonid(long personPersonid) {
        this.personPersonid = personPersonid;
    }

    @Id
    @Column(name = "RECIPE_RECIPEID")
    @GeneratedValue(strategy=GenerationType.AUTO)
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

        PersonRecipeEntity that = (PersonRecipeEntity) o;

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
