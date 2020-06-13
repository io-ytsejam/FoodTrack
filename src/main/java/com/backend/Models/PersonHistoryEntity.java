package com.backend.Models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PERSON_HISTORY", schema = "TEST", catalog = "")
public class PersonHistoryEntity {
    @Id
    @Column(name = "PERSON_HISTORY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long personHistory_id;

    @Column(name="RECIPE_ID")
    private long recipe_id;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_HISTORY")
    private Date date=new java.sql.Date(new java.util.Date().getTime());

    @Column(name = "IF_EXTERNAL")
    private char isExternal;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "personid")
    private PersonEntity personEntity;

    public PersonHistoryEntity() {
    }

    public long getPersonHistory_id() {
        return personHistory_id;
    }

    public void setPersonHistory_id(long personHistory_id) {
        this.personHistory_id = personHistory_id;
    }

    public long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(long recipe_id) {
        this.recipe_id = recipe_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public char getIsExternal() {
        return isExternal;
    }

    public void setIsExternal(char isExternal) {
        this.isExternal = isExternal;
    }

    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }
}
