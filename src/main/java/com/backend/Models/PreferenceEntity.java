package com.backend.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany(mappedBy = "preferenceEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PersonPreferenceEntity> personPreferenceEntities = new ArrayList<>();

    public void addPersonPreference(PersonPreferenceEntity personPreferenceEntity) {
        this.personPreferenceEntities.add(personPreferenceEntity);
        personPreferenceEntity.setPreferenceEntity(this);
    }

    public PreferenceEntity() {
    }

    public PreferenceEntity(String category)
    {
        this.category=category;
    }

    public List<PersonPreferenceEntity> getPersonPreferenceEntities() {
        return personPreferenceEntities;
    }

    public void setPersonPreferenceEntities(List<PersonPreferenceEntity> personPreferenceEntities) {
        this.personPreferenceEntities = personPreferenceEntities;
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

