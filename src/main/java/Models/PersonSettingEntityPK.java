package Models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class PersonSettingEntityPK implements Serializable {
    private long personPersonid;
    private long settingSettingid;

    @Column(name = "PERSON_PERSONID")
    @Id
    public long getPersonPersonid() {
        return personPersonid;
    }

    public void setPersonPersonid(long personPersonid) {
        this.personPersonid = personPersonid;
    }

    @Column(name = "SETTING_SETTINGID")
    @Id
    public long getSettingSettingid() {
        return settingSettingid;
    }

    public void setSettingSettingid(long settingSettingid) {
        this.settingSettingid = settingSettingid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonSettingEntityPK that = (PersonSettingEntityPK) o;

        if (personPersonid != that.personPersonid) return false;
        if (settingSettingid != that.settingSettingid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (personPersonid ^ (personPersonid >>> 32));
        result = 31 * result + (int) (settingSettingid ^ (settingSettingid >>> 32));
        return result;
    }
}
