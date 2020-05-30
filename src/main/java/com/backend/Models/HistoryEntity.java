package com.backend.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "HISTORY_OF_RECIPES", schema = "FDTRCK", catalog = "")
public class HistoryEntity {
    @Id
    @Column(name = "HISTORY_ID")
    private long history_id;

    @ManyToMany(mappedBy = "historyEntities")
    private Set<PersonEntity> personEntities = new HashSet<PersonEntity>();

    public HistoryEntity() {
    }

    public HistoryEntity(long history_id, Set<PersonEntity> personEntities) {
        this.history_id = history_id;
        this.personEntities = personEntities;
    }

    public HistoryEntity(Long history_id) {
        this.history_id=history_id;
    }

    public long getHistory_id() {
        return history_id;
    }

    public void setHistory_id(long history_id) {
        this.history_id = history_id;
    }

    public Set<PersonEntity> getPersonEntities() {
        return personEntities;
    }

    public void setPersonEntities(Set<PersonEntity> personEntities) {
        this.personEntities = personEntities;
    }
}
