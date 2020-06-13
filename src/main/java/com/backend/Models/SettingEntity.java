package com.backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SETTING", schema = "TEST", catalog = "")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","personSettingEntities"})
public class SettingEntity {

    @Id
    @Column(name = "SETTINGID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long settingid;

    @Basic
    @Column(name = "NAME",unique = true)
    private String name;

    @OneToMany(mappedBy = "settingEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PersonSettingEntity> personSettingEntities = new ArrayList<>();

    public void addPersonSetting(PersonSettingEntity personSettingEntity) {
        this.personSettingEntities.add(personSettingEntity);
        personSettingEntity.setSettingEntity(this);
    }

    public List<PersonSettingEntity> getPersonSettingEntities() {
        return personSettingEntities;
    }

    public void setPersonSettingEntities(List<PersonSettingEntity> personSettingEntities) {
        this.personSettingEntities = personSettingEntities;
    }

    public SettingEntity() {
    }

    public SettingEntity(String name)
    {
        this.name=name;
    }

    public long getSettingid() {
        return settingid;
    }

    public void setSettingid(long settingid) {
        this.settingid = settingid;
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

        SettingEntity that = (SettingEntity) o;

        if (settingid != that.settingid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (settingid ^ (settingid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}