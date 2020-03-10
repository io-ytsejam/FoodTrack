package main.java.Models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class PersonRestrictionEntityPK implements Serializable {
    private long healthHealthrestrictionid;
    private long personPersonid;

    @Column(name = "HEALTH_HEALTHRESTRICTIONID")
    @Id
    public long getHealthHealthrestrictionid() {
        return healthHealthrestrictionid;
    }

    public void setHealthHealthrestrictionid(long healthHealthrestrictionid) {
        this.healthHealthrestrictionid = healthHealthrestrictionid;
    }

    @Column(name = "PERSON_PERSONID")
    @Id
    public long getPersonPersonid() {
        return personPersonid;
    }

    public void setPersonPersonid(long personPersonid) {
        this.personPersonid = personPersonid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonRestrictionEntityPK that = (PersonRestrictionEntityPK) o;

        if (healthHealthrestrictionid != that.healthHealthrestrictionid) return false;
        if (personPersonid != that.personPersonid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (healthHealthrestrictionid ^ (healthHealthrestrictionid >>> 32));
        result = 31 * result + (int) (personPersonid ^ (personPersonid >>> 32));
        return result;
    }
}
