package main.java.Models;

import javax.persistence.*;

@Entity
@Table(name = "PERSON_PREFERENCE", schema = "TEST", catalog = "")
@IdClass(PersonPreferenceEntityPK.class)
public class PersonPreferenceEntity {
    private long personPersonid;
    private long preferencePreferenceid;

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
    @Column(name = "PREFERENCE_PREFERENCEID")
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

        PersonPreferenceEntity that = (PersonPreferenceEntity) o;

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
