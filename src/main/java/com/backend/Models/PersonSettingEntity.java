package com.backend.Models;

import javax.persistence.*;

@Entity
@Table(name = "PERSON_SETTING", schema = "TEST", catalog = "")
@IdClass(PersonSettingEntityPK.class)
public class PersonSettingEntity {
    private long personPersonid;
    private long settingSettingid;

    public PersonSettingEntity() {
    }

    @Id
    @Column(name = "PERSON_PERSONID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getPersonPersonid() {
        return personPersonid;
    }

    public void setPersonPersonid(long personPersonid) {
        this.personPersonid = personPersonid;
    }

    @Id
    @Column(name = "SETTING_SETTINGID")
    public long getSettingSettingid() {
        return settingSettingid;
    }

    public void setSettingSettingid(long settingSettingid) {
        this.settingSettingid = settingSettingid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonSettingEntity that = (PersonSettingEntity) o;

        if (personPersonid != that.personPersonid) return false;
        if (settingSettingid != that.settingSettingid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (personPersonid ^ (personPersonid >>> 32));
        result = 31 * result + (int) (settingSettingid ^ (settingSettingid >>> 32));
        return result;
    }
}
