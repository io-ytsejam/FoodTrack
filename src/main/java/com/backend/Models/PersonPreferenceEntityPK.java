package com.backend.Models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class PersonPreferenceEntityPK implements Serializable {
    private long personPersonid;
    private long preferencePreferenceid;

    @Column(name = "PERSON_PERSONID")
    @Id
    public long getPersonPersonid() {
        return personPersonid;
    }

    public void setPersonPersonid(long personPersonid) {
        this.personPersonid = personPersonid;
    }

    @Column(name = "PREFERENCE_PREFERENCEID")
    @Id
    public long getPreferencePreferenceid() {
        return preferencePreferenceid;
    }

    public void setPreferencePreferenceid(long preferencePreferenceid) {
        this.preferencePreferenceid = preferencePreferenceid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonPreferenceEntityPK that = (PersonPreferenceEntityPK) o;

        if (personPersonid != that.personPersonid) return false;
        if (preferencePreferenceid != that.preferencePreferenceid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (personPersonid ^ (personPersonid >>> 32));
        result = 31 * result + (int) (preferencePreferenceid ^ (preferencePreferenceid >>> 32));
        return result;
    }
}
