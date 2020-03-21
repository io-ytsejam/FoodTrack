package com.backend.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "HEALTH_RESTRICTION", schema = "TEST", catalog = "")
public class HealthRestrictionEntity {
    @Id
    @Column(name = "HEALTHRESTRICTIONID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long healthrestrictionid;
    @Basic
    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "healthRestrictionEntities")
    private Set<PersonEntity> personEntities = new HashSet<PersonEntity>();

    public void addPerson(PersonEntity personEntity) {
        this.personEntities.add(personEntity);
        personEntity.getHealthRestrictionEntities().add(this);
    }

    public void removePerson(PersonEntity personEntity) {
        this.personEntities.remove(personEntity);
        personEntity.getHealthRestrictionEntities().remove(this);
    }

    public HealthRestrictionEntity() {
    }

    public HealthRestrictionEntity(String name)
    {
        this.name=name;
    }

    public Set<PersonEntity> getPersonEntities() {
        return personEntities;
    }

    public void setPersonEntities(Set<PersonEntity> personEntities) {
        this.personEntities = personEntities;
    }

    public long getHealthrestrictionid() {
        return healthrestrictionid;
    }

    public void setHealthrestrictionid(long healthrestrictionid) {
        this.healthrestrictionid = healthrestrictionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HealthRestrictionEntity that = (HealthRestrictionEntity) o;

        if (healthrestrictionid != that.healthrestrictionid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (healthrestrictionid ^ (healthrestrictionid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

