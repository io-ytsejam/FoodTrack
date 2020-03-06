package main.java.Models;

import javax.persistence.*;

@Entity
public class Step_Recipe {

    @Id @GeneratedValue
    @Column(name="STEP_RECIPE_ID", nullable=false)
    private int steprecipeId;

    public Step_Recipe() {
    }

    public Step_Recipe(int steprecipeId)
    {
        this.steprecipeId=steprecipeId;
    }

    public int getSteprecipeId() {
        return steprecipeId;
    }

    public void setSteprecipeId(int steprecipeId) {
        this.steprecipeId = steprecipeId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    private Step step;
}


