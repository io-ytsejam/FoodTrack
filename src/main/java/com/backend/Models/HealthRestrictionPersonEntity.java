package com.backend.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "HEALTHRESTRICTION_PERSON", schema = "FDTRCK", catalog = "")
public class HealthRestrictionPersonEntity {
    @Id
    @Column(name = "HEALTHRESTRICTIONPERSONID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long healthRestrictionPersonid;

    @Basic
    @Column(name = "VALUE")
    private char value;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "healthrestrictionid")
    private HealthRestrictionEntity healthRestrictionEntity;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "personid")
    private PersonEntity personEntity;

    public long getHealthRestrictionPersonid() {
        return healthRestrictionPersonid;
    }

    public void setHealthRestrictionPersonid(long healthRestrictionPersonid) {
        this.healthRestrictionPersonid = healthRestrictionPersonid;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public HealthRestrictionEntity getHealthRestrictionEntity() {
        return healthRestrictionEntity;
    }

    public void setHealthRestrictionEntity(HealthRestrictionEntity healthRestrictionEntity) {
        this.healthRestrictionEntity = healthRestrictionEntity;
    }

    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }

    public HealthRestrictionPersonEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthRestrictionPersonEntity that = (HealthRestrictionPersonEntity) o;
        return healthRestrictionPersonid == that.healthRestrictionPersonid &&
                value == that.value &&
                Objects.equals(healthRestrictionEntity, that.healthRestrictionEntity) &&
                Objects.equals(personEntity, that.personEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(healthRestrictionPersonid, value, healthRestrictionEntity, personEntity);
    }
}
