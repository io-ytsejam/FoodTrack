package main.java.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Setting
{
    @Id @GeneratedValue
    @Column(name="SETTING_ID", nullable=false)
    private int settingId;

    @Column(name="NAME", nullable=false)
    private String name;

    public Setting(int settingId, String name) {
        this.settingId=settingId;
        this.name=name;
    }

    public int getSettingId() {
        return settingId;
    }

    public void setSettingId(int settingId) {
        this.settingId = settingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
