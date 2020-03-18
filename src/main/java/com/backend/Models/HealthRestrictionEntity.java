package com.backend.Models;

import javax.persistence.*;

@Entity
@Table(name = "HEALTH_RESTRICTION", schema = "TEST", catalog = "")
public class HealthRestrictionEntity {
    private long healthrestrictionid;
    private String name;

    @Id
    @Column(name = "HEALTHRESTRICTIONID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getHealthrestrictionid() {
        return healthrestrictionid;
    }

    public void setHealthrestrictionid(long healthrestrictionid) {
        this.healthrestrictionid = healthrestrictionid;
    }

    @Basic
    @Column(name = "NAME")
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
