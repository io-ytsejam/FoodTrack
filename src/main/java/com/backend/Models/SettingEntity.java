package com.backend.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SETTING", schema = "TEST", catalog = "")
public class SettingEntity {

    @Id
    @Column(name = "SETTINGID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long settingid;

    @Basic
    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "settings")
    private Set<PersonEntity> personEntities = new HashSet<PersonEntity>();

    public void addPerson(PersonEntity personEntity) {
        this.personEntities.add(personEntity);
        personEntity.getSettings().add(this);
    }

    public void removePerson(PersonEntity personEntity) {
        this.personEntities.remove(personEntity);
        personEntity.getSettings().remove(this);
    }

    public SettingEntity() {
    }

    public Set<PersonEntity> getPersonEntities() {
        return personEntities;
    }

    public void setPersonEntities(Set<PersonEntity> personEntities) {
        this.personEntities = personEntities;
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