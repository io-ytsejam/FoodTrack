package main.java.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Step {
    @Id
    @GeneratedValue
    @Column(name="STEP_ID", nullable=false)
    private int stepId;

    @Column(name="STEP_DESCRIPTION", nullable=false)
    private String stepDescription;

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
}
