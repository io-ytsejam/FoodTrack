package com.backend.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PERSON_PREFERENCE", schema = "FDTRCK", catalog = "")
public class PersonPreferenceEntity {
    @Id
    @Column(name = "PERSONPREFERENCEID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long personPreferenceId;

    @Basic
    @Column(name = "VALUE")
    private char value;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "preferenceid")
    private PreferenceEntity preferenceEntity;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "personid")
    private PersonEntity personEntity;

    public PreferenceEntity getPreferenceEntity() {
        return preferenceEntity;
    }

    public void setPreferenceEntity(PreferenceEntity preferenceEntity) {
        this.preferenceEntity = preferenceEntity;
    }

    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }

    public PersonPreferenceEntity() {
    }

    public long getPersonPreferenceId() {
        return personPreferenceId;
    }

    public void setPersonPreferenceId(long personPreferenceId) {
        this.personPreferenceId = personPreferenceId;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PersonPreferenceEntity{" +
                "personPreferenceId=" + personPreferenceId +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonPreferenceEntity that = (PersonPreferenceEntity) o;
        return personPreferenceId == that.personPreferenceId &&
                value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personPreferenceId, value);
    }
}
