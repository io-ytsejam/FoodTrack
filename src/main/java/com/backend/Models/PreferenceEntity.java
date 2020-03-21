package com.backend.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PREFERENCE", schema = "TEST", catalog = "")
public class PreferenceEntity {

    @Id
    @Column(name = "PREFERENCEID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long preferenceid;

    @Basic
    @Column(name = "CATEGORY")
    private String category;

    @ManyToMany(mappedBy = "preferences")
    private Set<PersonEntity> personEntities = new HashSet<>();

    public void addPerson(PersonEntity personEntity) {
        this.personEntities.add(personEntity);
        personEntity.getPreferences().add(this);
    }

    public void removePerson(PersonEntity personEntity) {
        this.personEntities.remove(personEntity);
        personEntity.getPreferences().remove(this);
    }

    public PreferenceEntity() {
    }

    public PreferenceEntity(String category)
    {
        this.category=category;
    }

    public Set<PersonEntity> getPersonEntities() {
        return personEntities;
    }

    public void setPersonEntities(Set<PersonEntity> personEntities) {
        this.personEntities = personEntities;
    }

    public long getPreferenceid() {
        return preferenceid;
    }

    public void setPreferenceid(long preferenceid) {
        this.preferenceid = preferenceid;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PreferenceEntity that = (PreferenceEntity) o;

        if (preferenceid != that.preferenceid) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (preferenceid ^ (preferenceid >>> 32));
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}

