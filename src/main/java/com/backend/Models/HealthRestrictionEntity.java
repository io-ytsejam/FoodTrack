package com.backend.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HEALTH_RESTRICTION", schema = "TEST", catalog = "")
public class HealthRestrictionEntity {
    @Id
    @Column(name = "HEALTHRESTRICTIONID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long healthrestrictionid;
    @Basic
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "healthRestrictionEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HealthRestrictionPersonEntity> healthRestrictionPersonEntities = new ArrayList<>();

    public void addHealth(HealthRestrictionPersonEntity healthRestrictionPersonEntity) {
        this.healthRestrictionPersonEntities.add(healthRestrictionPersonEntity);
        healthRestrictionPersonEntity.setHealthRestrictionEntity(this);
    }

    public List<HealthRestrictionPersonEntity> getHealthRestrictionPersonEntities() {
        return healthRestrictionPersonEntities;
    }

    public void setHealthRestrictionPersonEntities(List<HealthRestrictionPersonEntity> healthRestrictionPersonEntities) {
        this.healthRestrictionPersonEntities = healthRestrictionPersonEntities;
    }

    public HealthRestrictionEntity() {
    }

    public HealthRestrictionEntity(String name)
    {
        this.name=name;
    }

    public long getHealthrestrictionid() {
        return healthrestrictionid;
    }

    public void setHealthrestrictionid(long healthrestrictionid) {
        this.healthrestrictionid = healthrestrictionid;
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

        HealthRestrictionEntity that = (HealthRestrictionEntity) o;

        if (healthrestrictionid != that.healthrestrictionid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (healthrestrictionid ^ (healthrestrictionid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

