package Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Step {
    @Id
    @GeneratedValue
    @Column(name="STEP_ID", nullable=false)
    private int stepId;

    @Column(name="STEP_DESCRIPTION", nullable=false)
    private String stepDescription;

    public Step() {
    }

    public Step(int stepId, String stepDescription) {
        this.stepDescription=stepDescription;
        this.stepId=stepId;
    }

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private final List<Step_Recipe> step_recipes= new ArrayList<>();
}
