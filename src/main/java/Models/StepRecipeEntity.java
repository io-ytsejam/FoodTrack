package Models;
import javax.persistence.*;

@Entity
@Table(name = "STEP_RECIPE", schema = "TEST", catalog = "")
@IdClass(StepRecipeEntityPK.class)
public class StepRecipeEntity {
    private long recipeRecipeid;
    private long stepStepid;

    public StepRecipeEntity() {
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

    @Id
    @Column(name = "STEP_STEPID")
    public long getStepStepid() {
        return stepStepid;
    }

    public void setStepStepid(long stepStepid) {
        this.stepStepid = stepStepid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StepRecipeEntity that = (StepRecipeEntity) o;

        if (recipeRecipeid != that.recipeRecipeid) return false;
        if (stepStepid != that.stepStepid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (recipeRecipeid ^ (recipeRecipeid >>> 32));
        result = 31 * result + (int) (stepStepid ^ (stepStepid >>> 32));
        return result;
    }
}
