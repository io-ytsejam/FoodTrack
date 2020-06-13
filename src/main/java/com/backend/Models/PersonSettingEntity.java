package com.backend.Models;

import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PERSON_SETTING", schema = "FDTRCK", catalog = "")
public class PersonSettingEntity {
    @Id
    @Column(name = "PERSON_SETTING_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long healthRestrictionPersonid;

    @Basic
    @Column(name = "VALUE")
    private char value;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "settingid")
    private SettingEntity settingEntity;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "personid")
    private PersonEntity personEntity;

    public PersonSettingEntity() {
    }

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

    public SettingEntity getSettingEntity() {
        return settingEntity;
    }

    public void setSettingEntity(SettingEntity settingEntity) {
        this.settingEntity = settingEntity;
    }

    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonSettingEntity that = (PersonSettingEntity) o;
        return healthRestrictionPersonid == that.healthRestrictionPersonid &&
                value == that.value &&
                Objects.equals(settingEntity, that.settingEntity) &&
                Objects.equals(personEntity, that.personEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(healthRestrictionPersonid, value, settingEntity, personEntity);
    }
}
