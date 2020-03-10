package main.java.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Setting
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SETTING_ID", nullable=false)
    private int settingId;

    @Column(name="NAME", nullable=false)
    private String name;

    public Setting() {
    }

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

    @OneToMany(cascade = CascadeType.ALL)
    private final List<User_setting> user_settingList = new ArrayList<>();
}
