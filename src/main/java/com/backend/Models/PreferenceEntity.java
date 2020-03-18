package com.backend.Models;
import javax.persistence.*;

@Entity
@Table(name = "PREFERENCE", schema = "TEST", catalog = "")
public class PreferenceEntity {
    private long preferenceid;
    private String category;

    public PreferenceEntity() {
    }

    @Id
    @Column(name = "PREFERENCEID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getPreferenceid() {
        return preferenceid;
    }

    public void setPreferenceid(long preferenceid) {
        this.preferenceid = preferenceid;
    }

    @Basic
    @Column(name = "CATEGORY")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PreferenceEntity that = (PreferenceEntity) o;

        if (preferenceid != that.preferenceid) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (preferenceid ^ (preferenceid >>> 32));
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
