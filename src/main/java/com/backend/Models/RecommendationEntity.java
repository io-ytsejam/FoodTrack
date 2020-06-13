package com.backend.Models;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RECOMMENDATIONS", schema = "FDTRCK", catalog = "")
public class RecommendationEntity {
    @Id
    @Column(name = "RECOMMENDATION_ID")
    private long recommendation_id;

    @ManyToMany(mappedBy = "recommendationEntities")
    private Set<PersonEntity> personEntities = new HashSet<PersonEntity>();

    public RecommendationEntity()
    {

    }

    public Set<PersonEntity> getPersonEntities() {
        return personEntities;
    }

    public void setPersonEntities(Set<PersonEntity> personEntities) {
        this.personEntities = personEntities;
    }

    public void addPerson(PersonEntity personEntity) {
        this.personEntities.add(personEntity);
        personEntity.getRecommendationEntities().add(this);
    }

    public void removePerson(PersonEntity personEntity) {
        this.personEntities.remove(personEntity);
        personEntity.getRecommendationEntities().remove(this);
    }

    public RecommendationEntity(long recommendation_id) {
        this.recommendation_id = recommendation_id;
    }

    public long getRecommendation_id() {
        return recommendation_id;
    }

    public void setRecommendation_id(long recommendation_id) {
        this.recommendation_id = recommendation_id;
    }
}
