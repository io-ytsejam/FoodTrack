package main.java.Models;

import javax.persistence.*;

@Entity
@Table(name = "STEP", schema = "TEST", catalog = "")
public class StepEntity {
    private long stepid;
    private String stepdescription;

    public StepEntity() {
    }

    @Id
    @Column(name = "STEPID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getStepid() {
        return stepid;
    }

    public void setStepid(long stepid) {
        this.stepid = stepid;
    }

    @Basic
    @Column(name = "STEPDESCRIPTION")
    public String getStepdescription() {
        return stepdescription;
    }

    public void setStepdescription(String stepdescription) {
        this.stepdescription = stepdescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StepEntity that = (StepEntity) o;

        if (stepid != that.stepid) return false;
        if (stepdescription != null ? !stepdescription.equals(that.stepdescription) : that.stepdescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (stepid ^ (stepid >>> 32));
        result = 31 * result + (stepdescription != null ? stepdescription.hashCode() : 0);
        return result;
    }
}
