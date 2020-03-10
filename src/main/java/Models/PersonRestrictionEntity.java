package main.java.Models;

import javax.persistence.*;

@Entity
@Table(name = "PERSON_RESTRICTION", schema = "TEST", catalog = "")
@IdClass(PersonRestrictionEntityPK.class)
public class PersonRestrictionEntity {
    private long healthHealthrestrictionid;
    private long personPersonid;

    public PersonRestrictionEntity() {
    }

    @Id
    @Column(name = "HEALTH_HEALTHRESTRICTIONID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getHealthHealthrestrictionid() {
        return healthHealthrestrictionid;
    }

    public void setHealthHealthrestrictionid(long healthHealthrestrictionid) {
        this.healthHealthrestrictionid = healthHealthrestrictionid;
    }

    @Id
    @Column(name = "PERSON_PERSONID")
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

        PersonRestrictionEntity that = (PersonRestrictionEntity) o;

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
