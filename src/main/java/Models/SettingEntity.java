package main.java.Models;

import javax.persistence.*;

@Entity
@Table(name = "SETTING", schema = "TEST", catalog = "")
public class SettingEntity {
    private long settingid;
    private String name;

    public SettingEntity() {
    }

    @Id
    @Column(name = "SETTINGID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getSettingid() {
        return settingid;
    }

    public void setSettingid(long settingid) {
        this.settingid = settingid;
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

        SettingEntity that = (SettingEntity) o;

        if (settingid != that.settingid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (settingid ^ (settingid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
