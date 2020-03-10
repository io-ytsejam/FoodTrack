package Models;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class StepRecipeEntityPK implements Serializable {
    private long recipeRecipeid;
    private long stepStepid;

    @Column(name = "RECIPE_RECIPEID")
    @Id
    public long getRecipeRecipeid() {
        return recipeRecipeid;
    }

    public void setRecipeRecipeid(long recipeRecipeid) {
        this.recipeRecipeid = recipeRecipeid;
    }

    @Column(name = "STEP_STEPID")
    @Id
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

        StepRecipeEntityPK that = (StepRecipeEntityPK) o;

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
